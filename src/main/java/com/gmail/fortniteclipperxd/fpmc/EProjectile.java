package com.gmail.fortniteclipperxd.fpmc;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.ProjectileHitEvent;

import java.util.ArrayList;

import static org.bukkit.Bukkit.getLogger;

public class EProjectile {
    public static ArrayList<EProjectile> All = new ArrayList<EProjectile>();
    public Entity ProjectileEntity;
    public EProjectile(Player playerFrom, EntityType projectileType, double speed, String name)
    {
        Entity projectile = playerFrom.getPlayer().getWorld().spawnEntity(playerFrom.getPlayer().getEyeLocation().add(playerFrom.getPlayer().getLocation().getDirection().multiply(2)), projectileType);
        projectile.setVelocity(playerFrom.getPlayer().getLocation().getDirection().multiply(speed));
        projectile.setCustomName(name);
        ProjectileEntity = projectile;
        All.add(this);
    }

    public void OnHit(ProjectileHitEvent e)
    {
        getLogger().info(e.getEntity().getCustomName() + " hit something.");
        All.remove(this);
    }
}
