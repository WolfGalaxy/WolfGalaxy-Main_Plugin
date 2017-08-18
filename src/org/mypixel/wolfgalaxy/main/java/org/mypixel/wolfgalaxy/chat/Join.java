package org.mypixel.wolfgalaxy.chat;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.mypixel.wolfgalaxy.Main;

public class Join implements Listener {

    @EventHandler

    public void onPlayerJoin(PlayerJoinEvent event) {

        Main plugin;

        Player player = event.getPlayer();

        event.setJoinMessage(ChatColor.DARK_GRAY + "Player" + ChatColor.RED + " " + player.getName() + ChatColor.DARK_GRAY + " has joined");
    }

}
