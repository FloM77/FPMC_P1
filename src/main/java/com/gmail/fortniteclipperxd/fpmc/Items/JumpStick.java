package com.gmail.fortniteclipperxd.fpmc.Items;

import com.gmail.fortniteclipperxd.fpmc.Items.EItem;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.HashMap;

public class JumpStick extends EItem {
    public JumpStick(String[] shape, HashMap<Character, ItemStack> IngMap) {
        super("JumpStick", Material.STICK, shape, IngMap);
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
