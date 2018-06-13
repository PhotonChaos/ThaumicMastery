package com.carbon.thaumicmastery;

import com.carbon.thaumicmastery.blocks.*;
import com.carbon.thaumicmastery.entities.tileentities.TileEntityDecay;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

@Mod(modid = ThaumicMastery.MODID, version = ThaumicMastery.VERSION, name = ThaumicMastery.NAME)
public class ThaumicMastery {
    public static final String MODID = "thaumicmastery";
    public static final String VERSION = "0.1";
    public static final String NAME = "Thaumic Mastery";
    public static Configuration config;

    // proxies
    private static final String clientProxyPath = "com.carbon.thaumicmastery.ClientProxy";
	private static final String serverProxyPath = "com.carbon.thaumicmastery.CommonProxy";

	//@SidedProxy(clientSide = clientProxyPath, serverSide = serverProxyPath)
	//public static CommonProxy proxy;

	//
	public static Block blockDecay;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
		// item, block, and registering
	    // config handling
	    config = new Configuration(event.getSuggestedConfigurationFile());

	    // Blocks
	    blockDecay = new BlockDecay(Material.rock, 10);
	    GameRegistry.registerBlock(blockDecay, blockDecay.getUnlocalizedName().substring(5));

    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
		// proxy, crafting recipes, tile entity, entity, GUI, Packet Register

	    // Crafting

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
}
