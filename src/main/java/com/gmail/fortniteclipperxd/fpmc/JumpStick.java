package com.gmail.fortniteclipperxd.fpmc;

import org.bukkit.Material;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.util.Vector;

import java.util.HashMap;

import static org.bukkit.Bukkit.getLogger;

public class JumpStick extends EItem {
    public JumpStick(String name, Material appearance, String[] shape, HashMap<Character, ItemStack> IngMap) {
        super(name, appearance, shape, IngMap);
        Cooldown = 3000;
        Rename("Jump Stick");
        InitRecipe();
    }

    @Override
    public void ClickEvent(PlayerInteractEvent e) {
        super.ClickEvent(e);
        if(e.getPlayer().isOnGround())
        {
        e.getPlayer().setVelocity(new Vector(
                e.getPlayer().getVelocity().getX(),
                e.getPlayer().getVelocity().getY() + 1,
                e.getPlayer().getVelocity().getZ()));
        StartCooldown(e.getPlayer());
        }
    }


}
