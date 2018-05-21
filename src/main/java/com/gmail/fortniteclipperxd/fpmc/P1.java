package com.gmail.fortniteclipperxd.fpmc;

import com.gmail.fortniteclipperxd.fpmc.Blocks.BlockDiamondGenerator;
import com.gmail.fortniteclipperxd.fpmc.Blocks.EBlock;
import com.gmail.fortniteclipperxd.fpmc.Gui.CommandMenu;
import com.gmail.fortniteclipperxd.fpmc.Items.CloudBoots;
import com.gmail.fortniteclipperxd.fpmc.Items.JumpStick;
import com.gmail.fortniteclipperxd.fpmc.Items.WheatWand;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class P1 extends JavaPlugin {
    public static NamespacedKey key;
    @Override
    public void onEnable() {
        key = new NamespacedKey(this, this.getDescription().getName());

        this.saveDefaultConfig();
        EBlock.config = this;
        Handler.config = this;

        this.getCommand("menu").setExecutor(new CommandMenu());
        new JumpStick(new String[] {"#S#","#S#","#S#"}, new HashMap<Character, ItemStack>()
        {{
            put('#', new ItemStack(Material.AIR));
            put('S', new ItemStack(Material.STICK));
        }});
        new CloudBoots();
        new WheatWand();
        new BlockDiamondGenerator();

        getServer().getPluginManager().registerEvents(new Handler(), this);
        getLogger().info("P1 enabled.");
    }

    @Override
    public void onDisable() {
        getLogger().info("P1 disabled.");
    }
}
