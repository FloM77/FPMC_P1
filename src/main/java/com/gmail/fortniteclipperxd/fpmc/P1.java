package com.gmail.fortniteclipperxd.fpmc;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class P1 extends JavaPlugin {
    public static NamespacedKey key;
    @Override
    public void onEnable() {
        key = new NamespacedKey(this, this.getDescription().getName());
        this.getCommand("menu").setExecutor(new CommandMenu());
        new JumpStick("JumpStick", Material.STICK, new String[] {"#S#","#S#","#S#"}, new HashMap<Character, ItemStack>() {{
                                                                                                                                    put('#', new ItemStack(Material.AIR));
                                                                                                                                    put('S', new ItemStack(Material.STICK));    }});
        new CloudBoots("CloudBoots", Material.GOLD_BOOTS);
        new WheatWand("WheatWand", Material.BLAZE_ROD);
        getServer().getPluginManager().registerEvents(new Handler(), this);
        getLogger().info("P1 enabled.");
    }

    @Override
    public void onDisable() {
        getLogger().info("P1 disabled.");
    }
}