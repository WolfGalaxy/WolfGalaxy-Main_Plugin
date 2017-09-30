package org.mypixel.wolfgalaxy.chat.channels;

import net.dv8tion.jda.core.JDA;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.mypixel.wolfgalaxy.Main;
import org.mypixel.wolfgalaxy.Sides.SidesMain;
import org.mypixel.wolfgalaxy.chat.ChatControl;
import ru.tehkode.permissions.bukkit.PermissionsEx;

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
    public void Channels(AsyncPlayerChatEvent e) {

        String pexPrefix = PermissionsEx.getUser(e.getPlayer()).getOwnPrefix();

        String pexSuffix = PermissionsEx.getUser(e.getPlayer()).getSuffix();

        Bukkit.broadcastMessage("[DEBUG] >> " + pexPrefix + " PEXPrefix");
        Bukkit.broadcastMessage("[DEBUG] >> " + pexSuffix + " PEXSuffix");

        String prefix = translateAlternateColorCodes('&', pexPrefix);

        Bukkit.broadcastMessage("[DEBUG] >> " + prefix + " Prefix");

        String suffix = translateAlternateColorCodes('&', pexSuffix);

        Bukkit.broadcastMessage("[DEBUG] >> " + suffix + " Prefix");

        if (prefix == null) {
            prefix = translateAlternateColorCodes('&', PermissionsEx.getUser(e.getPlayer()).getPrefix());
        }

        if(suffix == null){
            suffix = "";
        }

        Player p = e.getPlayer();
        if (siths.contains(p)) {
            if (chat) {
                for (Player jedi : siths) {
                    jedi.sendMessage(prefix + DARK_RED + " Ⓢ " + p.getDisplayName() + DARK_GRAY + " » " + RED + e.getMessage());

                }
                e.setCancelled(true);
            } else {
                if (!chat) {
                    for (Player jedi : siths) {
                        p.sendMessage(ChatColor.RED + "Chat is currently disabled!");
                    }

                    e.setCancelled(true);

                }
            }
        }
        if (jedis.contains(p)) {
            if (chat) {
                for (Player jedi : jedis) {
                    jedi.sendMessage(prefix + GREEN + " Ⓙ " + p.getDisplayName() + DARK_GRAY + " » " + GREEN + e.getMessage());

                }
                e.setCancelled(true);
            } else {
                if (!chat) {
                    for (Player jedi : jedis) {
                        p.sendMessage(ChatColor.RED + "Chat is currently disabled!");
                    }

                    e.setCancelled(true);

                }
            }
        }
        if (ls.contains(p)) {
            if (chat) {
                for (Player leader : ls) {
                    leader.sendMessage(prefix + GREEN + " Ⓛ " + p.getDisplayName() + DARK_GRAY + " » " + DARK_GREEN + e.getMessage());

                }
                e.setCancelled(true);
            } else {
                if (!chat) {
                    for (Player leader : ls) {
                        p.sendMessage(ChatColor.RED + "Chat is currently disabled!");
                    }

                    e.setCancelled(true);

                }
            }
        }
        if (staff.contains(p)) {
            if (chat) {
                for (Player staff : staff) {
                    staff.sendMessage(prefix + DARK_PURPLE + " Ⓢ " + GRAY + p.getDisplayName() + DARK_GRAY + " » " + DARK_PURPLE + e.getMessage());

                }
                e.setCancelled(true);
            } else {
                if (!chat) {
                    for (Player staff : staff) {
                        p.sendMessage(ChatColor.RED + "Chat is currently disabled!");
                    }

                    e.setCancelled(true);

                }
            }
        }
        if (lowerstaff.contains(p)) {
            if (chat) {
                for (Player staff : lowerstaff) {
                    staff.sendMessage(prefix + DARK_AQUA + " ⓁⓈ " + GRAY + p.getDisplayName() + DARK_GRAY + " » " + AQUA + e.getMessage());

                }
                e.setCancelled(true);
            } else {
                if (!chat) {
                    for (Player staff : lowerstaff) {
                        p.sendMessage(ChatColor.RED + "Chat is currently disabled!");
                    }

                    e.setCancelled(true);

                }
            }
        }
        if (!siths.contains(p) && !jedis.contains(p) && !ls.contains(p) && !staff.contains(p) && !lowerstaff.contains(p)) {
            if (chat) {

                Main.getInstance().bot.getTextChannelById(Long.valueOf("347531538684182528")).sendMessage(p.getPlayerListName() + ": " + ChatColor.stripColor(e.getMessage())).queue();
                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', prefix) + YELLOW + " Ⓖ " + WHITE + p.getDisplayName() + " " + ChatColor.translateAlternateColorCodes('&', suffix) + DARK_GRAY + " » " + GRAY + e.getMessage());
                e.setCancelled(true);

            } else {
                if (!p.hasPermission("chat.bypass")) {
                    if (!chat) {
                        e.setCancelled(true);
                        p.sendMessage(ChatColor.RED + "Chat is currently disabled!");
                    }
                }
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
                    if (p.hasPermission("wg.channel.sith")) {
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
                            if (p.hasPermission("wg.channel.jedi")) {

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
                                if (Channels.lowerstaff.contains(p)) {

                                    Channels.lowerstaff.remove(p);
                                    for (Player staff : Channels.lowerstaff) {
                                        staff.sendMessage(RED + "Player %player% has left the channel".replace("%player%", p.getName()));
                                    }

                                }


                            }
                        }
                        if (args[0].equalsIgnoreCase("leadership")) {
                            if (p.hasPermission("wg.channel.leadership")) {

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
                                if (Channels.lowerstaff.contains(p)) {

                                    Channels.lowerstaff.remove(p);
                                    for (Player staff : Channels.lowerstaff) {
                                        staff.sendMessage(RED + "Player %player% has left the channel".replace("%player%", p.getName()));
                                    }

                                }


                            }
                        }
                        if (args[0].equalsIgnoreCase("staff")) {
                            if (p.hasPermission("wg.channel.staff")) {

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
                                if (Channels.lowerstaff.contains(p)) {

                                    Channels.lowerstaff.remove(p);
                                    for (Player staff : Channels.lowerstaff) {
                                        staff.sendMessage(RED + "Player %player% has left the channel".replace("%player%", p.getName()));
                                    }

                                }


                            }
                        }
                        if (args[0].equalsIgnoreCase("lowerstaff")) {
                            if (p.hasPermission("wg.channel.lowerstaff")) {

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
                        if (args[0].equalsIgnoreCase("global")) {

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
                            if (Channels.lowerstaff.contains(p)) {

                                Channels.lowerstaff.remove(p);
                                for (Player lowerstaff : Channels.lowerstaff) {

                                    lowerstaff.sendMessage(RED + "Player %player% has left the channel".replace("%player%", p.getName()));

                                }

                            }

                        }

                    }

                    if (p.hasPermission("wg.chat.moveplayer")) {
                        if (args[0].equalsIgnoreCase("add") && args[1].equalsIgnoreCase("sith")) {

                            Player toAdd = Bukkit.getPlayer(args[2]);

                            if (!Channels.siths.contains(toAdd)) {
                                Channels.siths.add(toAdd);
                                ;
                                for (Player sith : Channels.siths) {
                                    sith.sendMessage(GREEN + "Player %player% has joined the channel".replace("%player%", p.getName()));
                                }
                                if (Channels.jedis.contains(toAdd)) {
                                    Channels.jedis.remove(toAdd);
                                    for (Player jedi : Channels.jedis) {
                                        jedi.sendMessage(RED + "Player %player% has left the channel".replace("%player%", p.getName()));
                                    }
                                }
                                if (Channels.ls.contains(toAdd)) {

                                    Channels.ls.remove(toAdd);
                                    for (Player leadership : Channels.ls) {
                                        leadership.sendMessage(RED + "Player %player% has left the channel".replace("%player%", p.getName()));
                                    }

                                }
                                if (Channels.staff.contains(toAdd)) {

                                    Channels.staff.remove(toAdd);
                                    for (Player staff : Channels.staff) {
                                        staff.sendMessage(RED + "Player %player% has left the channel".replace("%player%", p.getName()));
                                    }

                                }
                                if (Channels.lowerstaff.contains(toAdd)) {

                                    Channels.lowerstaff.remove(toAdd);
                                    for (Player staff : Channels.lowerstaff) {
                                        staff.sendMessage(RED + "Player %player% has left the channel".replace("%player%", p.getName()));
                                    }

                                }
                            }


                            return true;
                        }

                    }

                    if (p.hasPermission("wg.chat.moveplayer")) {
                        if (args[0].equalsIgnoreCase("add") && args[1].equalsIgnoreCase("jedi")) {

                            Player toAdd = Bukkit.getPlayer(args[2]);

                            if (!Channels.jedis.contains(toAdd)) {
                                Channels.jedis.add(toAdd);
                                ;
                                for (Player sith : Channels.jedis) {
                                    sith.sendMessage(GREEN + "Player %player% has joined the channel".replace("%player%", p.getName()));
                                }
                                if (Channels.siths.contains(toAdd)) {
                                    Channels.siths.remove(toAdd);
                                    for (Player jedi : Channels.siths) {
                                        jedi.sendMessage(RED + "Player %player% has left the channel".replace("%player%", p.getName()));
                                    }
                                }
                                if (Channels.ls.contains(toAdd)) {

                                    Channels.ls.remove(toAdd);
                                    for (Player leadership : Channels.ls) {
                                        leadership.sendMessage(RED + "Player %player% has left the channel".replace("%player%", p.getName()));
                                    }

                                }
                                if (Channels.staff.contains(toAdd)) {

                                    Channels.staff.remove(toAdd);
                                    for (Player staff : Channels.staff) {
                                        staff.sendMessage(RED + "Player %player% has left the channel".replace("%player%", p.getName()));
                                    }

                                }
                                if (Channels.lowerstaff.contains(toAdd)) {

                                    Channels.lowerstaff.remove(toAdd);
                                    for (Player staff : Channels.lowerstaff) {
                                        staff.sendMessage(RED + "Player %player% has left the channel".replace("%player%", p.getName()));
                                    }

                                }
                            }


                            return true;
                        }

                    }
                }
            }
        }
        return true;
    }
}
