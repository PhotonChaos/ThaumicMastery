package com.carbon.thaumicmastery;

import com.carbon.thaumicmastery.blocks.*;
import com.carbon.thaumicmastery.entities.tileentities.TileEntityDecay;
import com.thaumcraft.ThaumcraftApi;
import com.thaumcraft.ThaumcraftApiHelper;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import org.apache.commons.logging.impl.Jdk13LumberjackLogger;
import org.apache.logging.log4j.Level;

@Mod(modid = ThaumicMastery.MODID, version = ThaumicMastery.VERSION, name = ThaumicMastery.NAME)
public class ThaumicMastery {
    public static final String MODID = "thaumicmastery";
    public static final String VERSION = "0.1";
    public static final String NAME = "Thaumic Mastery";
    public static Configuration config;

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
