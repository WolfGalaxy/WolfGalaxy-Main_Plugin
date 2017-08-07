package org.mypixel.wolfgalaxy;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.java.JavaPlugin;
import org.mypixel.wolfgalaxy.Achievements.FirstJoin;
import org.mypixel.wolfgalaxy.chat.ChatControl;
import org.mypixel.wolfgalaxy.chat.Join;
import org.mypixel.wolfgalaxy.chat.Leave;
import org.mypixel.wolfgalaxy.chat.channels.Channels;
import org.mypixel.wolfgalaxy.commands.AFK;
import org.mypixel.wolfgalaxy.events.TNT;

import java.util.HashMap;
import java.util.UUID;

import static org.mypixel.wolfgalaxy.chat.ChatControl.chat;

public class Main extends JavaPlugin implements Listener{

    public static Main plugin;
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public HashMap<UUID, PermissionAttachment> playerPermissions = new HashMap<>();



    @Override

    // Plugin enable

    public void onEnable() {

        chat = true;

        System.out.println(ChatColor.YELLOW + "[-------------------------]");
        System.out.println(ChatColor.YELLOW + "[  WolfGalaxy Main Plugin ]");
        System.out.println(ChatColor.YELLOW + "[        Enabled          ]");
        System.out.println(ChatColor.YELLOW + "[-------------------------]");

        registerCommands();

        registerEvents();
    }

    // Plugin disable

    public void onDisable() {

        System.out.println(ChatColor.YELLOW + "[-------------------------]");
        System.out.println(ChatColor.YELLOW + "[  WolfGalaxy Main Plugin ]");
        System.out.println(ChatColor.YELLOW + "[       Disabled          ]");
        System.out.println(ChatColor.YELLOW + "[-------------------------]");

    }

    public void registerCommands() {

        getCommand("chat").setExecutor(new ChatControl());
        getCommand("channel").setExecutor(new Channels());
        getCommand("wgbrb").setExecutor(new AFK());

    }

    public void registerEvents(){

        Bukkit.getPluginManager().registerEvents(new ChatControl(), this);
        Bukkit.getPluginManager().registerEvents(new Join(), this);
        Bukkit.getPluginManager().registerEvents(new Leave(), this);
        Bukkit.getPluginManager().registerEvents(new TNT(), this);
        Bukkit.getPluginManager().registerEvents(new Channels(), this);
        Bukkit.getPluginManager().registerEvents(new FirstJoin(), this);
        getServer().getPluginManager().registerEvents(this, this);
        Bukkit.getPluginManager().registerEvents(new AFK(), this);

    }
}
