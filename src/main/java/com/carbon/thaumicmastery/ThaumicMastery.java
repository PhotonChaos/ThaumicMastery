package com.carbon.thaumicmastery;

import com.carbon.thaumicmastery.blocks.*;
import com.carbon.thaumicmastery.entities.tileentities.TileEntityDecay;
import com.carbon.thaumicmastery.entities.tileentities.TileEntityMirrorDimension;
import com.carbon.thaumicmastery.eventhandlers.KeyInputHandler;
import com.carbon.thaumicmastery.keybinds.Keybinds;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

@Mod(modid = ThaumicMastery.MODID, version = ThaumicMastery.VERSION, name = ThaumicMastery.NAME)
public class ThaumicMastery {
    public static final String MODID = "thaumicmastery";
    public static final String VERSION = "0.1";
    public static final String NAME = "Thaumic Mastery";
    public static Configuration config;

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
		// item, block, and registering
	    // config handling
	    config = new Configuration(event.getSuggestedConfigurationFile());

	    // Items
	    // Spawn Eggs
	    //registerEntityEgg(EntityMirrorDimension.class, 0xd3fff1, 0x42f471);

	    // Blocks
	    blockDecay = new BlockDecay(Material.rock);
	    GameRegistry.registerBlock(blockDecay, blockDecay.getUnlocalizedName().substring(5));

	    blockMirrorDim = new BlockMirrorDimension();
	    GameRegistry.registerBlock(blockMirrorDim, blockMirrorDim.getUnlocalizedName().substring(5));

	    // Keybinds
	    Keybinds.register();
	    MinecraftForge.EVENT_BUS.register(KeyInputHandler.class);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
		// proxy, crafting recipes, tile entity, entity, GUI, Packet Register

	    // Crafting
	    // Entities

	    // Tile Entities
	    GameRegistry.registerTileEntity(TileEntityMirrorDimension.class, "MirrorDimension");
        proxy.registerRendering();
    }

    @EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		//
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
