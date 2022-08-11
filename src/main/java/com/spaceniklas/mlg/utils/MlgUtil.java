package com.spaceniklas.mlg.utils;

import com.spaceniklas.mlg.Mlg;
import com.spaceniklas.mlg.commands.MlgCommand;
import com.spaceniklas.mlg.listeners.MlgListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.CommandException;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Vehicle;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class MlgUtil {

    public static void mlgPlayer(Player p, boolean repeat){

        try {
            int max = Mlg.config.getInt("height-max");
            int min = Mlg.config.getInt("height-min");
            Random rand = new Random();
            int height = rand.ints(min, max).findFirst().getAsInt();
            int index = rand.ints(0, Mlg.items.size()).findFirst().getAsInt();
            Material item = Material.getMaterial(Mlg.items.get(index));

            p.sendMessage(ChatColor.GREEN + "You were teleported " + height + " blocks up!");

            Location loc = new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY() + height, p.getLocation().getZ());

            p.teleport(loc);

            ItemStack clutchItem = new ItemStack(item);

            p.getInventory().addItem(clutchItem);

            new BukkitRunnable(){

                @Override
                public void run(){
                    if (!p.isFlying() && p.getLocation().subtract(0, 0.1, 0).getBlock().getType().isSolid()) {
                        if(Mlg.items.contains(p.getLocation().subtract(0, 0.1, 0).getBlock().getType().name())){
                            p.getLocation().subtract(0, 0.1, 0).getBlock().setType(Material.AIR);
                        }

                        Collection<Entity> el = p.getLocation().getWorld().getNearbyEntities(p.getLocation(), 2,2,2);
                        for(Entity e : el){
                            if(!(e instanceof Player)) {
                                e.remove();
                            }
                        }

                        if(!MlgListener.players.contains(p)) {
                            p.getInventory().remove(item);
                            p.getInventory().remove(Material.BUCKET);
                            p.getLocation().getBlock().setType(Material.AIR);
                            for(Block b : MlgListener.blocks){
                                b.setType(Material.AIR);
                            }
                            for(Entity e : MlgListener.entities){
                                if(!(e instanceof Player))
                                    e.remove();
                            }
                            for(Vehicle v : MlgListener.vehicles){
                                v.remove();
                            }
                            Mlg.winstreak++;
                            MlgCommand.active = false;


                            p.setHealth(20);
                            p.setSaturation(20);
                            if(Mlg.winstreak > 1)
                                p.sendMessage(ChatColor.GOLD + "You did it! (Winstreak: " + Mlg.winstreak + ")");
                            if(Mlg.winstreak == 1)
                                p.sendMessage(ChatColor.GOLD + "You did it!");
                        }else {
                            MlgListener.players.clear();
                            MlgCommand.active = false;
                            for(Block b : MlgListener.blocks){
                                b.setType(Material.AIR);
                            }
                            MlgListener.blocks.clear();
                            for(Entity e : MlgListener.entities){
                                if(!(e instanceof Player))
                                    e.remove();
                            }
                            MlgListener.entities.clear();
                            for(Vehicle v : MlgListener.vehicles){
                                v.remove();
                            }
                            MlgListener.vehicles.clear();
                            MlgCommand.loop = false;
                            p.sendMessage(ChatColor.RED + "Failed!");
                            Mlg.winstreak = 0;
                        }
                        this.cancel();
                        if(repeat == true && MlgCommand.loop == true){
                            mlgPlayer(p, true);
                            MlgCommand.active = true;
                        }
                    }
                }
            }.runTaskTimer(Mlg.getMainInstance(), 10, 10);

        }catch (CommandException x){
            Bukkit.getLogger().info(ChatColor.RED + "The maximal height has to be greater than the minimal height!");
        }



    }


    public static ArrayList<Block> getBlocks(Block start, int radius){
        ArrayList<Block> blocks = new ArrayList<Block>();
        for(double x = start.getLocation().getX() - radius; x <= start.getLocation().getX() + radius; x++){
            for(double y = start.getLocation().getY() - radius; y <= start.getLocation().getY() + radius; y++){
                for(double z = start.getLocation().getZ() - radius; z <= start.getLocation().getZ() + radius; z++){
                    Location loc = new Location(start.getWorld(), x, y, z);
                    blocks.add(loc.getBlock());
                }
            }
        }
        return blocks;
    }



}
