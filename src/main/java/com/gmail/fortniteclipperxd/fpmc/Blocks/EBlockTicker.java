package com.gmail.fortniteclipperxd.fpmc.Blocks;

import com.gmail.fortniteclipperxd.fpmc.Handler;
import org.bukkit.Material;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.security.Permission;

import static org.bukkit.Bukkit.getLogger;

public class EBlockTicker extends EBlock {
    int Cooldown = 100;

    public EBlockTicker(String name, Material appearance)
    {
        super(name, appearance);
        Rename("Default Ticker");
        TickEvent();
    }

    @Override
    public void PlacedEvent(BlockPlaceEvent e)
    {
        if(e.getPlayer().hasPermission("fpmc.ticker")) {
        super.PlacedEvent(e);
        if(NumberPlaced==1) TickEvent();
        }
        else
        {
            e.setCancelled(true);
        }
    }

    public void TickEvent()
    {
        getLogger().info(Name + " ticked.");
        new BukkitRunnable() {
            public void run() {
                if(NumberPlaced>0)
                TickEvent();
            }
        }.runTaskLater(config, Cooldown);
    }
}
