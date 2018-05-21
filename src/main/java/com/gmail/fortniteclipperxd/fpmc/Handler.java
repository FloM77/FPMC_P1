package com.gmail.fortniteclipperxd.fpmc;

import com.gmail.fortniteclipperxd.fpmc.Blocks.EBlock;
import com.gmail.fortniteclipperxd.fpmc.Entities.EProjectile;
import com.gmail.fortniteclipperxd.fpmc.Items.CloudBoots;
import com.gmail.fortniteclipperxd.fpmc.Items.EItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.permissions.PermissionAttachment;

import java.util.HashMap;
import java.util.UUID;

import static org.bukkit.Bukkit.getLogger;

public class Handler implements Listener {
    public static P1 config;
    public static HashMap<UUID,PermissionAttachment> Permissions = new HashMap<UUID, PermissionAttachment>();
    @EventHandler
    public void OnLogin(PlayerJoinEvent e)
    {
        getLogger().info(e.getPlayer().getDisplayName() + " Logged in.");
        //EItem.GiveTo(e.getPlayer(), EItem.Search("JumpStick"));
        //EItem.GiveTo(e.getPlayer(), EItem.Search("CloudBoots"));
        //EItem.GiveTo(e.getPlayer(), EItem.Search("WheatWand"));
        PermissionAttachment attachment = e.getPlayer().addAttachment(config);

        if((Boolean) config.getConfig().get("Ticker.Allow"))
        attachment.setPermission("fpmc.ticker", true);

        Permissions.put(e.getPlayer().getUniqueId(), attachment);
    }

    @EventHandler
    public void OnEItemRightClick(PlayerInteractEvent e)
    {
        if(e.getPlayer().getInventory().getItemInMainHand() == null) return;
        for(EItem ei: EItem.All)
        {
            if(EItem.CompareWithItem(ei, e.getPlayer().getInventory().getItemInMainHand()))
            {
                if(ei.IsUseable(e.getPlayer())){
                ei.ClickEvent(e);
                }
            }
        }
    }

    @EventHandler
    public void OnEItemInventoryClick(InventoryClickEvent e)
    {
        for(EItem ei: EItem.All)
        {
            if(EItem.CompareWithItem(ei, e.getCurrentItem()))
            {
                if(ei.IsUseable((Player) e.getWhoClicked()))
                ei.InventoryClickEvent(e);
            }
        }
    }

    @EventHandler
    public void OnProjectileHit(ProjectileHitEvent e)
    {
        for(EProjectile ep : EProjectile.All)
        {
            if(ep.ProjectileEntity == e.getEntity())
            {
                ep.OnHit(e);
            }
        }
    }
    @EventHandler
    public void OnBlockPlace(BlockPlaceEvent e)
    {
        for(EBlock eb : EBlock.All)
        {
            if(eb.CompareWithItem(eb, e.getItemInHand()))
            {
                eb.PlacedEvent(e);
            }
        }
    }

    @EventHandler
    public void OnBlockBreak(BlockBreakEvent e)
    {
        EBlock logical = EBlock.SearchByLogical(e.getBlock().getLocation());
        if(logical != null)
        {
            logical.BreakEvent(e);
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void OnFall(EntityDamageEvent e)
    {
        if(e.getEntity() instanceof Player && e.getCause() == EntityDamageEvent.DamageCause.FALL)
        {
            CloudBoots cloudBoots = (CloudBoots) EItem.Search("CloudBoots");
            if(cloudBoots.IsWornByPlayer((Player)e.getEntity()) && cloudBoots.IsUseable((Player) e.getEntity()))
            {
                e.setCancelled(true);
                cloudBoots.StartCooldown((Player) e.getEntity());
            }
        }
    }
}
