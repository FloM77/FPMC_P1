package com.gmail.fortniteclipperxd.fpmc;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginAwareness;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.bukkit.Bukkit.getLogger;
import static org.bukkit.Bukkit.getServer;

public class EItem extends EBase {
    public int Cooldown = 2000;
    public int Durability = 100;
    public ArrayList<Player> BlockedFromUse = new ArrayList<Player>();
    public static ArrayList<EItem> All = new ArrayList<EItem>();

    public EItem(String name, Material appearance)
    {
        super(name, appearance);
        All.add(this);
    }

    public EItem(String name, Material appearance, String[] shape, HashMap<Character, ItemStack> ingMap)
    {
        super(name, appearance, shape, ingMap);
        All.add(this);
    }

    public void ClickEvent(PlayerInteractEvent e) {
        getLogger().info( Name + " used by " + e.getPlayer().getDisplayName());
    }

    public void InventoryClickEvent(InventoryClickEvent e)
    {
    }

    public void StartCooldown(final Player p)
    {
        BlockedFromUse.add(p);
        new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(Cooldown);
                }catch(Exception ex){}
                BlockedFromUse.remove(p);
                p.getPlayer().sendMessage(ChatColor.GREEN + Name + " is off cooldown.");
            }
        }).start();
    }

    public Boolean IsUseable(Player p)
    {
        if(BlockedFromUse.contains(p)) return  false;
        return true;
    }

    public Boolean IsWornByPlayer(Player player)
    {
        if( CompareWithItem(this,player.getInventory().getHelmet()) ||
            CompareWithItem(this,player.getInventory().getChestplate()) ||
            CompareWithItem(this,player.getInventory().getLeggings()) ||
            CompareWithItem(this,player.getInventory().getBoots()) )
        {
            return true;
        }
        return false;
    }
}
