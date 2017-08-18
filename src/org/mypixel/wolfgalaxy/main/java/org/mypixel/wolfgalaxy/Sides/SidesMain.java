 package org.mypixel.wolfgalaxy.Sides;

        import org.bukkit.Bukkit;
        import org.bukkit.ChatColor;
        import org.bukkit.Material;
        import org.bukkit.command.Command;
        import org.bukkit.command.CommandExecutor;
        import org.bukkit.command.CommandSender;
        import org.bukkit.configuration.file.FileConfiguration;
        import org.bukkit.entity.Player;
        import org.bukkit.event.EventHandler;
        import org.bukkit.event.Listener;
        import org.bukkit.event.inventory.InventoryClickEvent;
        import org.bukkit.inventory.Inventory;
        import org.bukkit.inventory.ItemStack;
        import org.bukkit.inventory.meta.ItemMeta;
        import org.mypixel.wolfgalaxy.Main;
        import org.mypixel.wolfgalaxy.YML.YamlShit;

        import java.util.Arrays;

public class SidesMain implements Listener, CommandExecutor {

    Inventory choose;
    private FileConfiguration sides;
    private YamlShit yaml;

    public SidesMain(Main plugin) {
        this.choose = Bukkit.createInventory(null, 9, ChatColor.DARK_GRAY + "Choose your side. " + ChatColor.RED + "YOU CAN CHOOSE ONLY ONE TIME");

        choose.setItem(1, blank);
        choose.setItem(2, jedi);
    }


    private ItemStack blank = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 8);
    {
        ItemMeta bk = blank.getItemMeta();
        bk.setDisplayName("");
        blank.setItemMeta(bk);
    }
    private ItemStack jedi = new ItemStack(Material.IRON_SWORD, 1);
    {
        ItemMeta jd = jedi.getItemMeta();
        jd.setDisplayName(ChatColor.GREEN + "Jedi");
        jd.setLore(Arrays.asList(ChatColor.AQUA + "Join the bright side!"));
        jedi.setItemMeta(jd);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        if(command.getName().equalsIgnoreCase("side"))
            p.openInventory(choose);
        return false;
    }

    @EventHandler
    public void onPlayerClick(InventoryClickEvent e){

        System.out.println(choose.getName());
        System.out.println(e.getInventory().getName());

        Player p = (Player) e.getWhoClicked();
        p.sendMessage("debug");

        if(e.getInventory().getName().equals(choose.getName())){
            p.sendMessage("debug1");
            if(e.getCurrentItem().getItemMeta().getDisplayName().equals(jedi.getItemMeta().getDisplayName())){
                p.sendMessage("debug");
                sides.set("sides.players." + e.getWhoClicked().getUniqueId() + ".side", "jedi");
                yaml.saveFile(sides, "playerSides");
                e.setCancelled(true);
            }
        }

    }

}