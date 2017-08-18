 package org.mypixel.wolfgalaxy;

        import net.dv8tion.jda.core.AccountType;
        import net.dv8tion.jda.core.JDA;
        import net.dv8tion.jda.core.JDABuilder;
        import net.dv8tion.jda.core.entities.MessageChannel;
        import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
        import net.dv8tion.jda.core.exceptions.RateLimitedException;
        import net.dv8tion.jda.core.hooks.ListenerAdapter;
        import org.bukkit.Bukkit;
        import org.bukkit.ChatColor;
        import org.bukkit.Material;
        import org.bukkit.configuration.file.FileConfiguration;
        import org.bukkit.entity.Player;
        import org.bukkit.event.EventHandler;
        import org.bukkit.event.player.AsyncPlayerChatEvent;
        import org.bukkit.inventory.Inventory;
        import org.bukkit.inventory.ItemStack;
        import org.bukkit.inventory.meta.ItemMeta;
        import org.bukkit.permissions.PermissionAttachment;
        import org.bukkit.plugin.java.JavaPlugin;
        import org.mypixel.wolfgalaxy.Achievements.FirstJoin;
        import org.mypixel.wolfgalaxy.Discord.DiscordMain;
        import org.mypixel.wolfgalaxy.chat.ChatControl;
        import org.mypixel.wolfgalaxy.chat.Join;
        import org.mypixel.wolfgalaxy.chat.Leave;
        import org.mypixel.wolfgalaxy.chat.channels.Channels;
        import org.mypixel.wolfgalaxy.commands.AFK;
        import org.mypixel.wolfgalaxy.events.TNT;
        import org.mypixel.wolfgalaxy.items.CustomItemsCommands;
        import org.mypixel.wolfgalaxy.items.CustomItemsEvents;

        import javax.security.auth.login.LoginException;
        import java.util.HashMap;
        import java.util.UUID;

        import static org.mypixel.wolfgalaxy.chat.ChatControl.chat;

public class Main extends JavaPlugin{

    public static Main plugin;
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public HashMap<UUID, PermissionAttachment> playerPermissions = new HashMap<>();
    public FileConfiguration config;
    public Inventory inv;
    public String BotToken = "MzQ3NTMxNjk2OTkzNzMwNTYw.DHchqw.zrHkAnIV_jwmP9InJsdlnOPlHgU";
    public JDA bot;
    private static Main instance;

    public JDA getBot() {
        return bot;
    }

    public static Main getInstance() {return instance;}

    @Override

    // Plugin enable

    public void onEnable() {

        config = getConfig();

        getConfig();
        saveDefaultConfig();

        chat = true;
        createInv(config.getInt("Slots"), ChatColor.AQUA + "Custom Armour");

        System.out.println(ChatColor.YELLOW + "[-------------------------]");
        System.out.println(ChatColor.YELLOW + "[  WolfGalaxy Main Plugin ]");
        System.out.println(ChatColor.YELLOW + "[        Enabled          ]");
        System.out.println(ChatColor.YELLOW + "[-------------------------]");

        registerCommands();
        registerEvents();
        InitializeDiscord("MzQ3NTMxNjk2OTkzNzMwNTYw.DHchqw.zrHkAnIV_jwmP9InJsdlnOPlHgU");
        bot.addEventListener(new DiscordMain());

        instance = this;

    }

    // Plugin disable

    public void onDisable() {

        System.out.println(ChatColor.YELLOW + "[-------------------------]");
        System.out.println(ChatColor.YELLOW + "[  WolfGalaxy Main Plugin ]");
        System.out.println(ChatColor.YELLOW + "[       Disabled          ]");
        System.out.println(ChatColor.YELLOW + "[-------------------------]");

        bot.getTextChannelById(Long.valueOf("347531538684182528")).sendMessage("Server is now offline").queue();

    }

    public void registerCommands() {

        getCommand("chat").setExecutor(new ChatControl());
        getCommand("channel").setExecutor(new Channels());
        getCommand("wgbrb").setExecutor(new AFK());
        getCommand("wearArmour").setExecutor(new CustomItemsCommands(this));
    }

    public void registerEvents(){

        Bukkit.getPluginManager().registerEvents(new ChatControl(), this);
        Bukkit.getPluginManager().registerEvents(new Join(), this);
        Bukkit.getPluginManager().registerEvents(new Leave(), this);
        Bukkit.getPluginManager().registerEvents(new TNT(), this);
        Bukkit.getPluginManager().registerEvents(new Channels(), this);
        Bukkit.getPluginManager().registerEvents(new FirstJoin(), this);
        Bukkit.getPluginManager().registerEvents(new AFK(), this);
        Bukkit.getPluginManager().registerEvents(new CustomItemsEvents(this), this);
        Bukkit.getPluginManager().registerEvents(new DiscordMain(), this);

    }


    public void createInv(int slot, String name) {
        inv = Bukkit.createInventory(null, slot, name);
        for (String string : config.getConfigurationSection("Items").getKeys(false)) {

            short durability = (short) config.getInt("Items." + string + ".Durability");
            String ItemName = config.getString("Items." + string + ".ItemName");
            ItemStack is = new ItemStack(Material.getMaterial(ItemName), 1);
            ItemMeta m = is.getItemMeta();
            m.setDisplayName(ChatColor.translateAlternateColorCodes('&',config.getString("Items." + string + ".Name")));
            is.setItemMeta(m);
            if (!(ItemName.startsWith("WOODEN_") || ItemName.startsWith("DIAMOND_") || ItemName.startsWith("GOLDEN_") || ItemName.startsWith("STONE_"))) {
                is.setDurability(durability);
            }
            inv.setItem(config.getInt("Items." + string + ".Slot"), is);

        }
    }

    public void InitializeDiscord(String token){

        try {
            bot = new JDABuilder(AccountType.BOT).setToken(token).buildBlocking();
        } catch (LoginException e) {
            e.printStackTrace();
        } catch (RateLimitedException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        bot.getTextChannelById(Long.valueOf("347531538684182528")).sendMessage("Server is now online").queue();

    }

}
