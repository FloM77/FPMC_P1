package com.gmail.fortniteclipperxd.fpmc.Blocks;

import com.gmail.fortniteclipperxd.fpmc.Blocks.EBlock;
import org.bukkit.Material;
import org.bukkit.Location;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class BlockDiamondGenerator extends EBlockTicker {
    public BlockDiamondGenerator()
    {
        super("DiamondGenerator", Material.DIAMOND_BLOCK);
        Rename("Diamond Generator");
    }

    @Override
    public void TickEvent()
    {
        super.TickEvent();
        for(Location l : Logical.keySet())
        {
            l.getWorld().dropItemNaturally(l, new ItemStack(Material.DIAMOND));
        }
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
