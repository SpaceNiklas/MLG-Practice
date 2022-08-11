package com.spaceniklas.mlg.listeners;

import com.spaceniklas.mlg.commands.MlgCommand;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Vehicle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.vehicle.VehicleCreateEvent;

import java.util.ArrayList;
import java.util.List;

public class MlgListener implements Listener {

    public static List<Block> blocks = new ArrayList<>();
    public static List<Vehicle> vehicles = new ArrayList<>();
    public static List<Entity> entities = new ArrayList<>();
    public static List<Player> players = new ArrayList<>();

    @EventHandler
    public void blockPlaceEvent(BlockPlaceEvent e){
        if(MlgCommand.active){
            blocks.add(e.getBlock());
        }
    }
    @EventHandler
    public void vehicleCreateEvent(VehicleCreateEvent e){
        if(MlgCommand.active){
            vehicles.add(e.getVehicle());
        }
    }
    @EventHandler
    public void enitySpawnEvent(EntitySpawnEvent e){
        if(MlgCommand.active){
            entities.add(e.getEntity());
        }
    }

    @EventHandler
    public void bucketEmtyEvent(PlayerBucketEmptyEvent e){
        if(MlgCommand.active){
            blocks.add(e.getBlock());
        }
    }

    @EventHandler
    public void onPlayerDeathEvent(PlayerDeathEvent e){
        if(MlgCommand.active){
            players.add(e.getEntity());
            e.setDeathMessage("");
        }
    }

}
