package com.gmail.fortniteclipperxd.fpmc;

import org.bukkit.Material;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

public class CloudBoots extends EItem {
    public CloudBoots(String name, Material appearance) {
        super(name, appearance);
        Cooldown = 6000;
        Rename("Cloud Boots");
    }
}
