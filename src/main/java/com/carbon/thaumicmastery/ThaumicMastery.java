package com.carbon.thaumicmastery;

import com.carbon.thaumicmastery.common.blocks.BlockDecay;
import com.carbon.thaumicmastery.common.blocks.BlockMirrorDimension;
import com.carbon.thaumicmastery.common.entities.tileentities.TileEntityDecay;
import com.carbon.thaumicmastery.common.entities.tileentities.TileEntityMirrorDimension;
import com.carbon.thaumicmastery.common.eventhandlers.MainEventHandler;
import com.carbon.thaumicmastery.common.helpers.ThaumcraftHelper;
import com.carbon.thaumicmastery.common.items.ModItems;
import com.carbon.thaumicmastery.common.keybinds.Keybinds;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

@Mod(modid = ThaumicMastery.MODID, version = ThaumicMastery.VERSION, name = ThaumicMastery.NAME)
public class ThaumicMastery {
    public static final String MODID = "thaumicmastery";
    public static final String VERSION = "0.1";
    public static final String NAME = "Thaumic Mastery";

    public static ResourceLocation logo = new ResourceLocation(ThaumicMastery.MODID, "textures/research/category/thaumicmastery.png");


	@Mod.Instance("ThaumicMastery")
    public static ThaumicMastery instance;

    public static CreativeTabs tab = new CreativeTabs("ThaumicMastery") {
	    @Override
	    public Item getTabIconItem() {
		    return ModItems.mirrordim_item;
	    }
    };

    public static Configuration config;

    public static boolean isSpellActive = false;

    static int startEntityId = 300;

    // proxies
    private static final String clientProxyPath = "com.carbon.thaumicmastery.ClientProxy";
	private static final String serverProxyPath = "com.carbon.thaumicmastery.CommonProxy";

	@SidedProxy(clientSide = clientProxyPath, serverSide = serverProxyPath)
	public static CommonProxy proxy;

	// blocks
	public static Block blockDecay;
	public static Block blockMirrorDim;

	@EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	instance = this;

		// item, block, and registering
	    // config handling
	    config = new Configuration(event.getSuggestedConfigurationFile());

	    // Items
	    ModItems.init();

	    // Blocks
	    blockDecay = new BlockDecay(Material.rock);
	    GameRegistry.registerBlock(blockDecay, blockDecay.getUnlocalizedName().substring(5));

	    blockMirrorDim = new BlockMirrorDimension();
	    GameRegistry.registerBlock(blockMirrorDim, blockMirrorDim.getUnlocalizedName().substring(5));

	    // Mod helpers
	    ThaumcraftHelper.preInit();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
		// proxy, crafting recipes, tile entity, entity, GUI, Packet Register

	    // Crafting
	    // Entities

	    // Tile Entities
	    GameRegistry.registerTileEntity(TileEntityMirrorDimension.class, "MirrorDimension");
	    GameRegistry.registerTileEntity(TileEntityDecay.class, "decay");
        proxy.registerRendering();

        // Events
	    Keybinds.register();
	    MainEventHandler events = new MainEventHandler();

	    MinecraftForge.EVENT_BUS.register(events);
	    FMLCommonHandler.instance().bus().register(events);

        // TC
	    ThaumcraftHelper.init();
    }

    @EventHandler
	public void postInit(FMLPostInitializationEvent event) {
	    ThaumcraftHelper.postInit();
    }

    public static void syncConfig() {
    	try {
    		config.load();
		    Property isDecayEnabled = config.get(Configuration.CATEGORY_GENERAL, "decayEnabled", "true", "Determined whether the decay will spread");
		    TileEntityDecay.doDecay = isDecayEnabled.getBoolean();
	    } catch (Exception e) {
		    System.err.println("There was an error reading the Thaumic Mastery config.");
	    } finally {
    		if(config.hasChanged()) config.save();
	    }
	}

	@SuppressWarnings("unchecked")
	public static void registerEntityEgg(Class<? extends Entity> entity, int primary, int secondary) {
    	int id = getUniqueEntityId();
		EntityList.IDtoClassMapping.put(id, entity);
		EntityList.entityEggs.put(id, new EntityList.EntityEggInfo(id, primary, secondary));
	}

	public static int getUniqueEntityId() {
    	do {
    		startEntityId++;
	    } while(EntityList.getStringFromID(startEntityId) != null);

    	return startEntityId;
	}
}
