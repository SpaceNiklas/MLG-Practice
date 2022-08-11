package com.spaceniklas.mlg.commands;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class MlgCommandTab implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        List<String> result = new ArrayList<>();

        if(args.length == 1 && sender.isOp()){
            result.add("settings");
            result.add("stop");
            result.add("start");
            result.add("help");
        }

        return result;
    }
}
