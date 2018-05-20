package com.gmail.fortniteclipperxd.fpmc;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.bukkit.Bukkit.getLogger;
import static org.bukkit.Bukkit.getPluginManager;
import static org.bukkit.Bukkit.getServer;

public class EBlock extends EBase {
    public HashMap<Location, String> Logical;
    public static ArrayList<EBlock> All = new ArrayList<EBlock>();
    public static P1 config;

    public EBlock(String name, Material appearance)
    {
        super(name, appearance);
        All.add(this);

        Logical = (LoadState() != null ? LoadState() : new HashMap<Location, String>());
    }

    public void PlacedEvent(BlockPlaceEvent e)
    {
        Logical.put(e.getBlockPlaced().getLocation(), e.getPlayer().getPlayerListName());

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
            for(Map.Entry<Location, String> l: eb.Logical.entrySet()) {
                if (l.getKey().equals(location)) {
                    return eb;
                }
            }
        }
        return null;
    }

    public static HashMap<String, Location> SearchAllByPlayer(String playername)
    {
        HashMap<String, Location> ret = new HashMap<String, Location>();
        for(EBlock eb : All)
        {
            for(Map.Entry<Location, String> l: eb.Logical.entrySet()) {
                if (l.getValue().equals(playername)) {
                    ret.put(eb.Name, l.getKey());
                }
            }
        }
        return ret.size() == 0 ? null : ret;
    }

    public ArrayList<Location> SearchByPlayer(String playername)
    {
        ArrayList<Location> ret = new ArrayList<Location>();
        for(Map.Entry<Location, String> l: Logical.entrySet()) {
            if (l.getValue().equals(playername)) {
                ret.add(l.getKey());
            }
        }
        return ret.size() == 0 ? null : ret;
    }

    public void SaveState()
    {
        ArrayList<Location> listLocations = new ArrayList<Location>();
        ArrayList<String> listPlayers = new ArrayList<String>();
        for(Map.Entry<Location, String> l : Logical.entrySet())
        {
            listLocations.add(l.getKey());
            listPlayers.add(l.getValue());
        }
        config.getConfig().set("World.EBlockState." + Name + ".Locations", listLocations);
        config.getConfig().set("World.EBlockState." + Name + ".Players", listPlayers);
        config.saveConfig();
    }

    public HashMap<Location, String> LoadState()
    {
        if(config.getConfig().get("World.EBlockState." + Name + ".Locations") == null ||
            config.getConfig().get("World.EBlockState." + Name + ".Players") == null) return null;
        HashMap load = new HashMap<Location, String>();
        ArrayList<Location> locations = (ArrayList<Location>) config.getConfig().get("World.EBlockState." + Name + ".Locations");
        ArrayList<String> players = (ArrayList<String>) config.getConfig().get("World.EBlockState." + Name + ".Players");
        for(int i=0;i<locations.size();i++)
        {
            load.put(locations.get(i), players.get(i));
        }
        return load;
    }
}
