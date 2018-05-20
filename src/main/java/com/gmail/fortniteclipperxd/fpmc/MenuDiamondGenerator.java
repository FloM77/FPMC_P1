package com.gmail.fortniteclipperxd.fpmc;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.ArrayList;

public class MenuDiamondGenerator extends EItem {
    public MenuDiamondGenerator()
    {
        super("MenuDiamondGenerator", Material.DIAMOND_BLOCK);
        Rename("Diamond Generator Delivery");
        SetLore(new ArrayList<String>() {{ add("Get a Diamond Generator!"); }});
    }

    @Override
    public void InventoryClickEvent(InventoryClickEvent e) {
        e.getWhoClicked().getInventory().addItem(EItem.Search("DiamondGenerator").Physical);
        e.getWhoClicked().closeInventory();
        e.setCancelled(true);
    }
}
