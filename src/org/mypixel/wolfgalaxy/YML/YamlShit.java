package org.mypixel.wolfgalaxy.YML;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.mypixel.wolfgalaxy.Main;

import java.io.File;
import java.io.IOException;

public class YamlShit {
    private Main plugin;
    public YamlShit(Main main){
        this.plugin = main;
    }

    public void createFiles(String fileName) {
        File file = new File(plugin.getDataFolder(), fileName + ".yml");
        if (!file.exists()) {
            plugin.saveResource(fileName + ".yml", false);
        }
        getFile(fileName);
    }

    public void saveFile(FileConfiguration config, String fileName) {
        File file = new File(plugin.getDataFolder(), fileName + ".yml");
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FileConfiguration getFile(String fileName) {
        File file = new File(plugin.getDataFolder(), fileName + ".yml");
        FileConfiguration fileConfig = new YamlConfiguration();
        try {
            fileConfig.load(file);
            return fileConfig;
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
        return null;
    }

}