package com.spaceniklas.mlg;

import com.spaceniklas.mlg.commands.MlgCommand;
import com.spaceniklas.mlg.commands.MlgCommandTab;
import com.spaceniklas.mlg.listeners.MenuListener;
import com.spaceniklas.mlg.listeners.MlgListener;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class Mlg extends JavaPlugin {
    public static FileConfiguration config;
    public static File clutchItemsFile;
    public static YamlConfiguration clutchItems;
    public static List<String> items = new ArrayList<>();
    private static Mlg mainInstance;
    public static int winstreak;
    public static boolean warning;

    @Override
    public void onEnable() {

        mainInstance = this;

        Bukkit.getLogger().info("[MLG] Plugin is up!");

        this.getCommand("mlg").setExecutor(new MlgCommand());
        this.getCommand("mlg").setTabCompleter(new MlgCommandTab());
        Bukkit.getPluginManager().registerEvents(new MenuListener(), this);
        Bukkit.getPluginManager().registerEvents(new MlgListener(), this);

        config = this.getConfig();
        this.saveDefaultConfig();

        clutchItemsFile = new File(getDataFolder(), "clutchItems.yml");

        if(!clutchItemsFile.exists()){
            try {
                clutchItemsFile.createNewFile();
            } catch (IOException e) {
                Bukkit.getLogger().info("[Error] Can't load clutchItems.yml file!");
                e.printStackTrace();
                return;
            }
        }

        Mlg.clutchItems = YamlConfiguration.loadConfiguration(clutchItemsFile);

        items = (List<String>) clutchItems.getList("clutch-items");
        try {
            clutchItems.save(clutchItemsFile);
        } catch (IOException e) {

        }
    }

    public void onDisable(){
        clutchItems.set("clutch-items", items);
        try {
            clutchItems.save(clutchItemsFile);
        } catch (IOException e) {

        }
    }

    public static Mlg getMainInstance(){
        return mainInstance;
    }

}
