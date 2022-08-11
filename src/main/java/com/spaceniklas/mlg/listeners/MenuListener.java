package com.spaceniklas.mlg.listeners;

import com.spaceniklas.mlg.Mlg;
import com.spaceniklas.mlg.menus.MlgMenu;
import com.spaceniklas.mlg.menus.AddMenu;
import com.spaceniklas.mlg.menus.DecisionMenu;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Arrays;
import java.util.List;

public class MenuListener implements Listener {
    public static Material mlgitem;
    public static String name;

    @EventHandler
    public void onSettingInvCLick(InventoryClickEvent e){
        if(ChatColor.translateAlternateColorCodes('&', e.getView().getTitle()).equals(ChatColor.BLACK.toString() + "MLG Settings") && e.getCurrentItem() != null){

            Player p = (Player) e.getWhoClicked();
            if(e.getRawSlot() >9 && e.getRawSlot() < 44){
                e.setCancelled(true);
                p.playSound(p.getLocation(), Sound.UI_BUTTON_CLICK, 1 ,1);
                mlgitem = e.getCurrentItem().getType();
                if(e.getCurrentItem().getItemMeta().getDisplayName() != null) {
                    name = e.getCurrentItem().getItemMeta().getDisplayName();
                }
                DecisionMenu.openDecisisonMenu(p);
            }else if(e.getRawSlot() == 0){
                p.closeInventory();
            } else if(e.getRawSlot() == 3 || e.getRawSlot() == 5){
                AddMenu.openAddMenu(p);
            }
            p.playSound(p.getLocation(), Sound.UI_BUTTON_CLICK, 1 ,1);

            e.setCancelled(true);

        }

    }

    @EventHandler
    public void onDecisionInvClick(InventoryClickEvent e){
        try {
            if (ChatColor.translateAlternateColorCodes('&', e.getView().getTitle()).equals(ChatColor.BLACK.toString() + "Are you sure? ") && e.getCurrentItem() != null) {

                Player p = (Player) e.getWhoClicked();

                if (e.getRawSlot() == 20) {
                    Mlg.items.remove(mlgitem.name());
                    e.getWhoClicked().sendMessage(ChatColor.GREEN + "Item was removed successfully!");
                    MlgMenu.openMenu(p);
                } else if (e.getRawSlot() == 0) {
                    p.closeInventory();
                } else if (e.getRawSlot() == 24) {
                    MlgMenu.openMenu(p);
                }
                p.playSound(p.getLocation(), Sound.UI_BUTTON_CLICK, 1, 1);
                e.setCancelled(true);

            }
        }catch (IllegalArgumentException x){

        }

    }
    @EventHandler
    public void onAddInvClick(InventoryClickEvent e){

        Player p = (Player) e.getWhoClicked();

        try {
            if (ChatColor.translateAlternateColorCodes('&', e.getView().getTitle()).equals(ChatColor.BLACK.toString() + "Add an item!") && e.getCurrentItem() != null) {
                List<Integer> intis = Arrays.asList(0,1, 2, 3,4, 5, 6, 7, 8, 9,10,11,12,19,21,14,15,16,22,23,25, 17, 18, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35);
                if(intis.contains(e.getRawSlot())){
                    e.setCancelled(true);
                }
                if(e.getRawSlot() == 20){
                    if(e.getClickedInventory().getItem(13) != null){
                        e.setCancelled(true);
                        Mlg.items.add(e.getClickedInventory().getItem(13).getType().name());
                        e.getWhoClicked().sendMessage(ChatColor.GREEN + "Successfully added your item!");
                        MlgMenu.openMenu((Player) e.getWhoClicked());
                        p.playNote(p.getLocation(), Instrument.PLING, Note.natural(1, Note.Tone.C));
                    }else {
                        e.setCancelled(true);
                        p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
                        e.getWhoClicked().sendMessage(ChatColor.RED + "You did not specify an item! Please put your item of choice in the slot above the arrow pointing up!");
                    }
                } else if(e.getRawSlot() == 24){
                    p.playSound(p.getLocation(), Sound.UI_BUTTON_CLICK, 1, 1);
                    MlgMenu.openMenu(p);
                }
            }
        }catch (IllegalArgumentException x){

        }

    }
}
