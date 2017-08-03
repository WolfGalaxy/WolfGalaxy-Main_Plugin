package org.mypixel.wolfgalaxy.chat.channels;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.mypixel.wolfgalaxy.chat.ChatControl;

import java.util.ArrayList;

import static org.bukkit.ChatColor.*;
import static org.mypixel.wolfgalaxy.chat.ChatControl.chat;

public class Channels implements Listener, CommandExecutor {

    public static ArrayList<Player> siths = new ArrayList<Player>();
    public static ArrayList<Player> jedis = new ArrayList<Player>();
    public static ArrayList<Player> ls = new ArrayList<Player>();
    public static ArrayList<Player> staff = new ArrayList<Player>();
    public static ArrayList<Player> lowerstaff = new ArrayList<Player>();

    @EventHandler
    public void channels(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        if (siths.contains(p)) {
            for (Player admin : siths) {
                admin.sendMessage(ChatColor.translateAlternateColorCodes('&',
                        DARK_RED + " Ⓢ " + p.getDisplayName() + DARK_GRAY + " » " + RED + e.getMessage()));
            }
            e.setCancelled(true);
        }
        if (jedis.contains(p)) {
            for (Player jedi : jedis) {
                jedi.sendMessage(DARK_AQUA + " Ⓙ " + p.getDisplayName() + DARK_GRAY + " » " + AQUA + e.getMessage());

            }
            e.setCancelled(true);
        }
        if (ls.contains(p)) {
            for (Player leader : ls) {
                leader.sendMessage(GREEN + " Ⓛ " + p.getDisplayName() + DARK_GRAY + " » " + RED + e.getMessage());

            }
            e.setCancelled(true);
        }
        if (staff.contains(p)) {
            for (Player staff : staff) {
                staff.sendMessage(DARK_PURPLE + " Ⓢ " + GRAY + p.getDisplayName() + DARK_GRAY + " » " + DARK_PURPLE + e.getMessage());

            }
            e.setCancelled(true);
        }
        if (lowerstaff.contains(p)) {
            for (Player staff : lowerstaff) {
                staff.sendMessage(LIGHT_PURPLE + " ⓁⓈ " + GRAY + p.getDisplayName() + DARK_GRAY + " » " + DARK_PURPLE + e.getMessage());

            }
            e.setCancelled(true);
        }
        if(!siths.contains(p) && !jedis.contains(p) && !ls.contains(p) && !staff.contains(p) && !lowerstaff.contains(p)) {
            if (chat = chat) {

                p.sendMessage(YELLOW + " Ⓖ " + WHITE + p.getDisplayName() + DARK_GRAY + " » " + GRAY + e.getMessage());

                e.setCancelled(true);

            }
        }

    }

    @EventHandler
    public void onLeft(PlayerQuitEvent e) {
        Player p = (Player) e.getPlayer();
        for (Player sith : siths) {
            sith.sendMessage(RED + "Player %player% has left the channel".replace("%player%", p.getName()));
        }
        for (Player jedi : jedis) {
            jedi.sendMessage(RED + "Player %player% has left the channel".replace("%player%", p.getName()));
        }

    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (cmd.getName().equalsIgnoreCase("channel")) {
                if (args.length == 1) {
                    if (p.hasPermission("channel.sith")) {
                        if (args[0].equalsIgnoreCase("sith")) {
                            if (!Channels.siths.contains(p)) {
                                Channels.siths.add(p);
                                ;
                                for (Player sith : Channels.siths) {
                                    sith.sendMessage(GREEN + "Player %player% has joined the channel".replace("%player%", p.getName()));
                                }
                                if (Channels.jedis.contains(p)) {
                                    Channels.jedis.remove(p);
                                    for (Player jedi : Channels.jedis) {
                                        jedi.sendMessage(RED + "Player %player% has left the channel".replace("%player%", p.getName()));
                                    }
                                }
                                if (Channels.ls.contains(p)) {

                                    Channels.ls.remove(p);
                                    for (Player leadership : Channels.ls) {
                                        leadership.sendMessage(RED + "Player %player% has left the channel".replace("%player%", p.getName()));
                                    }

                                }
                                if (Channels.staff.contains(p)) {

                                    Channels.staff.remove(p);
                                    for (Player staff : Channels.staff) {
                                        staff.sendMessage(RED + "Player %player% has left the channel".replace("%player%", p.getName()));
                                    }

                                }
                                if (Channels.lowerstaff.contains(p)) {

                                    Channels.lowerstaff.remove(p);
                                    for (Player staff : Channels.lowerstaff) {
                                        staff.sendMessage(RED + "Player %player% has left the channel".replace("%player%", p.getName()));
                                    }

                                }
                            }


                            return true;
                        }

                        if (args[0].equalsIgnoreCase("jedi")) {
                            if (p.hasPermission("channel.jedi")) {

                                if (!Channels.jedis.contains(p)) {
                                    Channels.jedis.add(p);
                                }
                                for (Player jedi : Channels.jedis) {

                                    jedi.sendMessage(GREEN + "Player %player% has joined the channel".replace("%player%", p.getName()));

                                }
                                if (Channels.siths.contains(p)) {

                                    Channels.siths.remove(p);
                                    for (Player sith : Channels.siths) {
                                        sith.sendMessage(RED + "Player %player% has left the channel".replace("%player%", p.getName()));
                                    }

                                }
                                if (Channels.ls.contains(p)) {

                                    Channels.ls.remove(p);
                                    for (Player leadership : Channels.ls) {
                                        leadership.sendMessage(RED + "Player %player% has left the channel".replace("%player%", p.getName()));
                                    }

                                }
                                if (Channels.staff.contains(p)) {

                                    Channels.staff.remove(p);
                                    for (Player staff : Channels.staff) {
                                        staff.sendMessage(RED + "Player %player% has left the channel".replace("%player%", p.getName()));
                                    }

                                }



                            }
                        }
                        if (args[0].equalsIgnoreCase("leadership")) {
                            if (p.hasPermission("channel.leadership")) {

                                if (!Channels.ls.contains(p)) {
                                    Channels.ls.add(p);
                                }
                                for (Player leadership : Channels.ls) {

                                    leadership.sendMessage(GREEN + "Player %player% has joined the channel".replace("%player%", p.getName()));

                                }
                                if (Channels.siths.contains(p)) {

                                    Channels.siths.remove(p);
                                    for (Player sith : Channels.siths) {
                                        sith.sendMessage(RED + "Player %player% has left the channel".replace("%player%", p.getName()));
                                    }

                                }
                                if (Channels.jedis.contains(p)) {

                                    Channels.jedis.remove(p);
                                    for (Player jedi : Channels.jedis) {
                                        jedi.sendMessage(RED + "Player %player% has left the channel".replace("%player%", p.getName()));
                                    }

                                }
                                if (Channels.staff.contains(p)) {

                                    Channels.staff.remove(p);
                                    for (Player staff : Channels.staff) {
                                        staff.sendMessage(RED + "Player %player% has left the channel".replace("%player%", p.getName()));
                                    }

                                }


                            }
                        }
                        if (args[0].equalsIgnoreCase("staff")) {
                            if (p.hasPermission("channel.staff")) {

                                if (!Channels.staff.contains(p)) {
                                    Channels.staff.add(p);
                                }
                                for (Player staff : Channels.staff) {

                                    staff.sendMessage(GREEN + "Player %player% has joined the channel".replace("%player%", p.getName()));

                                }
                                if (Channels.siths.contains(p)) {

                                    Channels.siths.remove(p);
                                    for (Player sith : Channels.siths) {
                                        sith.sendMessage(RED + "Player %player% has left the channel".replace("%player%", p.getName()));
                                    }

                                }
                                if (Channels.jedis.contains(p)) {

                                    Channels.jedis.remove(p);
                                    for (Player jedi : Channels.jedis) {
                                        jedi.sendMessage(RED + "Player %player% has left the channel".replace("%player%", p.getName()));
                                    }

                                }
                                if (Channels.ls.contains(p)) {

                                    Channels.ls.remove(p);
                                    for (Player leadership : Channels.ls) {
                                        leadership.sendMessage(RED + "Player %player% has left the channel".replace("%player%", p.getName()));
                                    }

                                }


                            }
                        }
                        if (args[0].equalsIgnoreCase("lowerstaff")) {
                            if (p.hasPermission("channel.lowerstaff")) {

                                if (!Channels.lowerstaff.contains(p)) {
                                    Channels.lowerstaff.add(p);
                                }
                                for (Player staff : Channels.lowerstaff) {

                                    staff.sendMessage(GREEN + "Player %player% has joined the channel".replace("%player%", p.getName()));

                                }
                                if (Channels.siths.contains(p)) {

                                    Channels.siths.remove(p);
                                    for (Player sith : Channels.siths) {
                                        sith.sendMessage(RED + "Player %player% has left the channel".replace("%player%", p.getName()));
                                    }

                                }
                                if (Channels.jedis.contains(p)) {

                                    Channels.jedis.remove(p);
                                    for (Player jedi : Channels.jedis) {
                                        jedi.sendMessage(RED + "Player %player% has left the channel".replace("%player%", p.getName()));
                                    }

                                }
                                if (Channels.ls.contains(p)) {

                                    Channels.ls.remove(p);
                                    for (Player leadership : Channels.ls) {
                                        leadership.sendMessage(RED + "Player %player% has left the channel".replace("%player%", p.getName()));
                                    }

                                }
                                if (Channels.staff.contains(p)) {

                                    Channels.staff.remove(p);
                                    for (Player staff : Channels.staff) {
                                        staff.sendMessage(RED + "Player %player% has left the channel".replace("%player%", p.getName()));
                                    }

                                }


                            }
                        }

                    }
                }
            }
        }
        return true;
    }
}
