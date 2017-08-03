package org.mypixel.wolfgalaxy.Achievements;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent;

public class FirstJoin implements Listener{


    @EventHandler

    public void firstJoinAchievemenrs(final PlayerLoginEvent event){

        System.out.println(",");

        Player player = event.getPlayer();

        if(!event.getPlayer().hasPlayedBefore()){

            player.sendMessage(ChatColor.DARK_GRAY + "⤜" + ChatColor.STRIKETHROUGH + "----------[ " + ChatColor.GREEN + "ACHIEVEMENT GET " + ChatColor.DARK_GRAY + "]" + ChatColor.STRIKETHROUGH + "----------⤛");
            player.sendMessage("");
            player.sendMessage(ChatColor.YELLOW + "              GET INSTALLED");
            player.sendMessage(ChatColor.GRAY + "     Join server for the first time" + ChatColor.RED + "!");
            player.sendMessage("");
            player.sendMessage(ChatColor.DARK_GRAY + "⤜" + ChatColor.STRIKETHROUGH + "---------------------------------------" + ChatColor.RESET + ChatColor.DARK_GRAY + "⤛");

        }
        else{

            System.out.println("Player isn't new");

        }




    }

}
