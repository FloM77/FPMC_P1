package com.gmail.fortniteclipperxd.fpmc.Gui;

import com.gmail.fortniteclipperxd.fpmc.Gui.MenuDiamondGenerator;
import com.gmail.fortniteclipperxd.fpmc.Gui.MenuJumpStick;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class CommandMenu implements CommandExecutor {
    public static Inventory P1Menu = Bukkit.createInventory(null, 27, "Main Menu");

    static {
        P1Menu.setItem(11, new MenuJumpStick().Physical);
        P1Menu.setItem(13, new MenuDiamondGenerator().Physical);
    }
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player)
        {
           ((Player) commandSender).openInventory(P1Menu);
        }
        return false;
    }
}
