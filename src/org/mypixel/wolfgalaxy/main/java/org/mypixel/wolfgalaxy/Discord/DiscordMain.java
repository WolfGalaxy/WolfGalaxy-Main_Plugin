package org.mypixel.wolfgalaxy.Discord;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.mypixel.wolfgalaxy.Main;

public class DiscordMain extends ListenerAdapter implements Listener{

    @Override
    public void onMessageReceived(MessageReceivedEvent e){

        String msg = e.getMessage().getContent();
        String user = e.getAuthor().getName();
if(!e.getAuthor().getId().equals(e.getJDA().getSelfUser().getId()) && e.getChannel().getName().equals("server")) {
    Bukkit.broadcastMessage(ChatColor.BLUE + "Discord" + ChatColor.DARK_GRAY + " | " + ChatColor.WHITE + " " + user + ": " + ChatColor.AQUA + msg);
} else return;

    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        Main.getInstance().bot.getTextChannelById(Long.valueOf("347531538684182528")).sendMessage(e.getPlayer() + "has joined the server.").queue();
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e){
        Main.getInstance().bot.getTextChannelById(Long.valueOf("347531538684182528")).sendMessage(e.getPlayer() + "has left the server.").queue();
    }

}