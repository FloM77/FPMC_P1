package com.gmail.fortniteclipperxd.fpmc;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.bukkit.Bukkit.getLogger;
import static org.bukkit.Bukkit.getServer;

public class EItem{
    public String Name;
    public ItemStack Physical;
    public int Cooldown = 2000;
    public int Durability = 100;
    public ArrayList<Player> BlockedFromUse = new ArrayList<Player>();
    public static ArrayList<EItem> All = new ArrayList<EItem>();

    public EItem(String name, Material appearance)
    {
        Name = name;
        Physical = new ItemStack(appearance);
        All.add(this);
    }

    public EItem(String name, Material appearance, String[] shape, HashMap<Character, ItemStack> ingMap)
    {
        this(name, appearance);
        Shape = shape;
        IngMap = ingMap;
    }

    String[] Shape;
    HashMap<Character, ItemStack> IngMap;

    void InitRecipe()
    {
        ShapedRecipe EItemRecipe = new ShapedRecipe(P1.key, Physical);
        EItemRecipe.shape(Shape);

        for(Map.Entry<Character, ItemStack> e: IngMap.entrySet())
        {
            getLogger().info("RECIPE: " + e.getKey() + " : " + e.getValue());
            EItemRecipe.setIngredient(e.getKey(), e.getValue().getType());
        }
        getServer().addRecipe(EItemRecipe);
    }

    public static void GiveTo(Player player, EItem eitem)
    {
        player.getInventory().addItem(eitem.Physical);
    }

    public void Rename(String newName)
    {
        ItemMeta meta = Physical.getItemMeta();
        meta.setDisplayName(newName);
        Physical.setItemMeta(meta);
    }

    public static EItem Search(String exactName)
    {
        for(EItem ei: All)
        {
            if(ei.Name == exactName)
            {
                return ei;
            }
        }
        return new EItem("Undefined", Material.EGG) {{ Rename("Undefined EItem");}};
    }

    public void ClickEvent(PlayerInteractEvent e) {
        getLogger().info( Name + " used by " + e.getPlayer().getDisplayName());
    }

    public void InventoryClickEvent(InventoryClickEvent e)
    {
    }

    public void SetLore(ArrayList<String> lore)
    {
        ItemMeta meta = Physical.getItemMeta();
        meta.setLore(lore);
        Physical.setItemMeta(meta);
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

    public static boolean CompareWithItem(EItem eitem, ItemStack item)
    {
        if(item == null || eitem == null || item.getItemMeta() == null || item.getItemMeta().getDisplayName() == null) return  false;
        getLogger().info(eitem + " : " + item);
        if(item.getItemMeta().getDisplayName().equalsIgnoreCase(eitem.Physical.getItemMeta().getDisplayName())) {
        return true;
        }
        return false;
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
