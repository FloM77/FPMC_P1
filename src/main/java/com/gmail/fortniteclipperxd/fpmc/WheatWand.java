package com.gmail.fortniteclipperxd.fpmc;

import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Projectile;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class WheatWand extends EItem {
    public WheatWand(String name, Material appearance) {
        super(name, appearance);
        Cooldown = 3000;
        Rename("Wheat Wand");
    }

    @Override
    public void ClickEvent(PlayerInteractEvent e) {
        super.ClickEvent(e);

        new EProjectile(e.getPlayer(), EntityType.ARROW, 1, "WheatBomb") {
            @Override
            public void OnHit(ProjectileHitEvent e) {
                super.OnHit(e);
                for(int i=-1;i<=1;i++)
                    for(int ii=-1;ii<=1;ii++) {
                        for (int iii = -1; iii <= 1; iii++) {
                            e.getHitBlock().getWorld().getBlockAt(e.getHitBlock().getLocation().add(i, ii, iii)).setType(Material.HAY_BLOCK);
                        }
                    }
                }
        };
        StartCooldown(e.getPlayer());
    }


}
