package org.mypixel.wolfgalaxy.Sides;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class Sides implements Listener, CommandExecutor {

    public static ArrayList<Player> siths = new ArrayList<Player>();
    public static ArrayList<Player> jedis = new ArrayList<Player>();
    public static ArrayList<Player> greys = new ArrayList<Player>();

    Inventory SideChoose;
    {

        SideChoose = Bukkit.createInventory(null, 9, ChatColor.YELLOW + "Choose your side");

    }


    ItemStack jedi = new ItemStack(Material.IRON_SWORD,1);
    {

        ItemMeta jd = jedi.getItemMeta();
        jd.setDisplayName(ChatColor.AQUA + "Jedi");
        jedi.setItemMeta(jd);

    }

    ItemStack sith = new ItemStack(Material.STONE_SWORD,1);
    {

        ItemMeta sth = sith.getItemMeta();
        sth.setDisplayName(ChatColor.DARK_RED + "Sith");
        sith.setItemMeta(sth);

    }
    ItemStack blank = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 8);
    {

        ItemMeta bk = blank.getItemMeta();
        bk.setDisplayName(" ");
        blank.setItemMeta(bk);

    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        return false;
    }
}
