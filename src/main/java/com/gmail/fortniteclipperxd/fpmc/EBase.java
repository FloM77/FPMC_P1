package com.gmail.fortniteclipperxd.fpmc;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.bukkit.Bukkit.getLogger;
import static org.bukkit.Bukkit.getServer;

public class EBase {
    public String Name;
    public ItemStack Physical;
    public static ArrayList<EBase> All = new ArrayList<EBase>();
    public EBase(String name, Material appearance)
    {
        Name = name;
        Physical = new ItemStack(appearance);
        ItemMeta meta = Physical.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        Physical.setItemMeta(meta);
        All.add(this);
    }

    public EBase(String name, Material appearance, String[] shape, HashMap<Character, ItemStack> ingMap)
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
            EItemRecipe.setIngredient(e.getKey(), e.getValue().getType());
        }
        getServer().addRecipe(EItemRecipe);
    }

    public static void GiveTo(Player player, EBase ebase)
    {
        player.getInventory().addItem(ebase.Physical);
    }

    public void Rename(String newName)
    {
        ItemMeta meta = Physical.getItemMeta();
        meta.setDisplayName(newName);
        Physical.setItemMeta(meta);
    }

    public void SetLore(ArrayList<String> lore)
    {
        ItemMeta meta = Physical.getItemMeta();
        meta.setLore(lore);
        Physical.setItemMeta(meta);
    }

    public static EBase Search(String exactName)
    {
        for(EBase eb: All)
        {
            if(eb.Name == exactName)
            {
                return eb;
            }
        }
        return new EBase("Undefined", Material.EGG) {{ Rename("Undefined E");}};
    }

    public static boolean CompareWithItem(EBase ebase, ItemStack item)
    {
        if(item == null || ebase == null || item.getItemMeta() == null || item.getItemMeta().getDisplayName() == null) return  false;
        if(item.getItemMeta().getDisplayName().equalsIgnoreCase(ebase.Physical.getItemMeta().getDisplayName()) && item.getItemMeta().hasItemFlag(ItemFlag.HIDE_POTION_EFFECTS)) {
            return true;
        }
        return false;
    }
}
