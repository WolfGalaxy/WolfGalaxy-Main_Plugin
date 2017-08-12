package org.mypixel.wolfgalaxy.commands;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;

public class AFK implements CommandExecutor, Listener {

    public static ArrayList<Player> afk = new ArrayList<Player>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;

        if (label.equalsIgnoreCase("wgbrb")) {

            Bukkit.broadcastMessage(player.getDisplayName() + ChatColor.DARK_GRAY + " is now " + ChatColor.RED + "AFK" + ChatColor.DARK_GRAY + " because of: " + ChatColor.GREEN + StringUtils.join(args, " "));
            AFK.afk.add(player);

        }

        return false;
    }

    @EventHandler

    public void afkRemove(AsyncPlayerChatEvent e) {

        Player p = e.getPlayer();

        if (AFK.afk.contains(p)) {

            Bukkit.broadcastMessage(ChatColor.GREEN + p.getDisplayName() + ChatColor.DARK_GRAY + " has came back!");
            AFK.afk.remove(p);

        }

    }

    @EventHandler

    public void afkRemove2(PlayerMoveEvent e) {

        Player p = e.getPlayer();

        Location to = e.getTo();
        Location from = e.getFrom();
        if (afk.contains(p)) {
            if ((from.getBlockX() != to.getBlockX()) || (from.getBlockY() != to.getBlockY()) || (from.getBlockZ() != to.getBlockZ())) {
                Bukkit.broadcastMessage(ChatColor.GREEN + p.getDisplayName() + ChatColor.DARK_GRAY + " has came back!");
                AFK.afk.remove(p);

            }
        }

    }

}
