package com.gmail.fortniteclipperxd.fpmc;

import org.bukkit.Material;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

public class CloudBoots extends EItem {
    public CloudBoots() {
        super("CloudBoots", Material.GOLD_BOOTS);
        Cooldown = 6000;
        Rename("Cloud Boots");
    }
}
