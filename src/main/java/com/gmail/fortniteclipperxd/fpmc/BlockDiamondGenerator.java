package com.gmail.fortniteclipperxd.fpmc;

import org.bukkit.Material;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockDiamondGenerator extends EBlock {
    public BlockDiamondGenerator()
    {
        super("DiamondGenerator", Material.DIAMOND_BLOCK);
        Rename("Diamond Generator");
    }

    @Override
    public void PlacedEvent(BlockPlaceEvent e) {
        super.PlacedEvent(e);
    }

    @Override
    public void BreakEvent(BlockBreakEvent e) {
        super.BreakEvent(e);
        e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), Physical);
    }
}
