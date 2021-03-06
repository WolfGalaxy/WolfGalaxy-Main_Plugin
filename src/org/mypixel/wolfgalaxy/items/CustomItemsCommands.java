package org.mypixel.wolfgalaxy.items;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.mypixel.wolfgalaxy.Main;


/**
 * Created by Albus on 12/08/2017.
 */

public class CustomItemsCommands implements CommandExecutor {

    public Main plugin;

    public CustomItemsCommands(Main instance) {
        plugin = instance;
    }

    @Override

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            return true;
        }

        Player player = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("wearArmour")) {
            if (args.length == 0) {
                if (player.hasPermission("wg.items.see")) {
                    player.openInventory(plugin.inv);
                } else {
                    player.sendMessage(ChatColor.RED + "You don't have the perm required to see the hats");
                }
            } else if (args.length == 1 && args[0].equalsIgnoreCase("remove")) {
                player.getInventory().setHelmet(new ItemStack(Material.AIR));
                player.getInventory().setChestplate(new ItemStack(Material.AIR));
                player.getInventory().setLeggings(new ItemStack(Material.AIR));
                player.getInventory().setBoots(new ItemStack(Material.AIR));
            }
        }
        return true;
    }

}

