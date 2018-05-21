package com.gmail.fortniteclipperxd.fpmc.Gui;

import com.gmail.fortniteclipperxd.fpmc.Items.EItem;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.ArrayList;

public class MenuJumpStick extends EItem {
    public MenuJumpStick()
    {
        super("MenuJumpStick", Material.STICK);
        Rename("Jump Stick Delivery");
        SetLore(new ArrayList<String>() {{ add("Get a Jump Stick!"); }});
    }

    @Override
    public void InventoryClickEvent(InventoryClickEvent e) {
        e.getWhoClicked().getInventory().addItem(EItem.Search("JumpStick").Physical);
        e.getWhoClicked().closeInventory();
        e.setCancelled(true);
    }
}
