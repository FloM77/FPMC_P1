package com.gmail.fortniteclipperxd.fpmc;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

import static org.bukkit.Bukkit.getLogger;
import static org.bukkit.Bukkit.getPluginManager;

public class EBlock extends EBase {
    public ArrayList<Location> Logical = new ArrayList<Location>();
    public static ArrayList<EBlock> All = new ArrayList<EBlock>();
    public static P1 config;

    public EBlock(String name, Material appearance)
    {
        super(name, appearance);
        All.add(this);

        LoadState();
    }

    public void PlacedEvent(BlockPlaceEvent e)
    {
        Logical.add(e.getBlockPlaced().getLocation());

        SaveState();
    }

    public void BreakEvent(BlockBreakEvent e)
    {
        e.getBlock().setType(Material.AIR);
        Logical.remove(e.getBlock().getLocation());

        SaveState();
    }

    public static EBlock SearchByLogical(Location location)
    {
        for(EBlock eb : All)
        {
            for(Location l: eb.Logical) {
                if (l.equals(location)) {
                    return eb;
                }
            }
        }
        return null;
    }

    public void SaveState()
    {
        config.getConfig().set("World.EBlockState." + Name, Logical);
        config.saveConfig();
    }

    public void LoadState()
    {
        if(config.getConfig().get("World.EBlockState." + Name) != null)
        Logical = (ArrayList<Location>) config.getConfig().get("World.EBlockState." + Name);
    }
}
