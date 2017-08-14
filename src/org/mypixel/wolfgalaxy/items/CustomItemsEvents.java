package org.mypixel.wolfgalaxy.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.mypixel.wolfgalaxy.Main;

/**
 * Created by DELL on 14/08/2017.
 */
public class CustomItemsEvents implements Listener {

    public Main plugin;

    public CustomItemsEvents(Main instance) {
        plugin = instance;
    }
    @EventHandler

    public void onInventoryClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        ItemStack itemClicked = event.getCurrentItem();
        Inventory inv = event.getInventory();
        ItemMeta meta = itemClicked.getItemMeta();

        if (inv.getName().equals(ChatColor.AQUA + "Custom Armour")) {
            if (!(itemClicked == null || itemClicked.getType().equals(Material.AIR))) {
                for (String string : plugin.config.getConfigurationSection("Items").getKeys(false)) {
                    if (ChatColor.translateAlternateColorCodes('&',
                            plugin.config.getString("Items." + string + ".Name")).equals(meta.getDisplayName())
                            && player.hasPermission("wg.items.wear." + string)) {

                        if (plugin.config.getString("Items." + string + ".Type").equalsIgnoreCase("Helmet")) {
                            player.getInventory().setHelmet(itemClicked);
                        } else if (plugin.config.getString("Items." + string + ".Type").equalsIgnoreCase("Chestplate")) {
                            player.getInventory().setChestplate(itemClicked);
                        } else if (plugin.config.getString("Items." + string + ".Type").equalsIgnoreCase("Leggings")) {
                            player.getInventory().setLeggings(itemClicked);
                        } else if (plugin.config.getString("Items." + string + ".Type").equalsIgnoreCase("Boots")) {
                            player.getInventory().setBoots(itemClicked);
                        }
                    }
                }
            }
        } else if (inv == player.getInventory()) {
            for (String string : plugin.config.getConfigurationSection("Items").getKeys(false)) {
                if (ChatColor.translateAlternateColorCodes('&',
                        plugin.config.getString("Items." + string + ".Name")).equals(meta.getDisplayName())) {
                    event.setCancelled(true);
                }
            }
        }
    }

}
