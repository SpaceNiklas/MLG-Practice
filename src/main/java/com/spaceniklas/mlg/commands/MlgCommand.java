package com.spaceniklas.mlg.commands;

import com.spaceniklas.mlg.Mlg;
import com.spaceniklas.mlg.menus.MlgMenu;
import com.spaceniklas.mlg.utils.MlgUtil;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MlgCommand implements CommandExecutor {

    public static boolean loop;
    public static boolean active;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        try {
            if (args.length == 1 && args[0].equalsIgnoreCase("settings") && sender instanceof Player && sender.hasPermission("mlg.settings")) {
                Player p = (Player) sender;
                MlgMenu.openMenu(p);
            } else if (args.length == 0 && sender instanceof Player && sender.hasPermission("mlg.mlg")) {
                if(!(Mlg.items.size() == 0)) {
                    MlgUtil.mlgPlayer((Player) sender, false);
                    active = true;
                }else{
                    sender.sendMessage(ChatColor.RED + "You have to add items to you list of clutch items! You can do that by doing /mlg settings.");
                }
            } else if (args.length == 1 && args[0].equalsIgnoreCase("start") && sender instanceof Player && sender.hasPermission("mlg.start")) {
                if(!(Mlg.items.size() == 0)) {
                    if(Mlg.warning = Boolean.parseBoolean(null)) {
                        sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "[Warning] " + ChatColor.RESET + ChatColor.RED + "This mode is in " + ChatColor.RESET + "" + ChatColor.DARK_RED + "BETA" + ChatColor.RED + ", if you experience any bug feel free to report them on our Discord Server (Link in config.yml)!");
                        Mlg.warning = false;
                    }
                    MlgUtil.mlgPlayer((Player) sender, true);
                    active = true;
                    loop = true;
                }else {
                    sender.sendMessage(ChatColor.RED + "You have to add items to you list of clutch items! You can do that by doing /mlg settings.");
                }
            } else if (args.length == 1 && args[0].equalsIgnoreCase("stop") && sender instanceof Player && sender.hasPermission("mlg.stop")) {
                loop = false;
                active = false;
            } else if (args.length == 1 && args[0].equalsIgnoreCase("help") && sender instanceof Player && sender.hasPermission("mlg.help")) {
                sender.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "MLG Practice Commands:");
                sender.sendMessage(ChatColor.GRAY + "/mlg - Teleports you a random amount of blocks up (set in config.yml) and gives you a random item.");
                sender.sendMessage(ChatColor.GRAY + "/mlg settings - Opens a menu for you to edit the items you clutch with.");
                sender.sendMessage(ChatColor.GRAY + "/mlg start - You get put in a loop and can clutch over and over again.");
                sender.sendMessage(ChatColor.GRAY + "/mlg stop - Stops the loop.");
                sender.sendMessage(ChatColor.GRAY + "/mlg help - Shows you a list of available commands.");
            }else{
                sender.sendMessage(ChatColor.RED + "Command not recognised! Do /mlg help to see a full list of all commands!");
            }

        }catch (CommandException x){
            sender.sendMessage(ChatColor.RED + "You have to add items to you list of clutch items! You can do that by doing /mlg settings.");
        }
        return false;
    }
}
