package com.spaceniklas.mlg.menus;

import com.spaceniklas.mlg.Mlg;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.*;

public class MlgMenu {

    public static String itemname;

    public static void openMenu(Player p){
        Inventory mlginv = Bukkit.createInventory(p, 45, ChatColor.BLACK.toString() + "MLG Settings");


        //WATER BUCKET
        ItemStack totem = new ItemStack(Material.WATER_BUCKET);
        ItemMeta totemmeta = totem.getItemMeta();
        totemmeta.setLore(Arrays.asList(ChatColor.GRAY + "You can change your MLG settings here!"));
        totem.setItemMeta(totemmeta);

        mlginv.setItem(4, totem);

        int index = 0;
        List<Integer> intlist = new LinkedList<>(Arrays.asList(10,11,12,13,14,15,16,19,20,21,22,23,24,25,28,29,30,31,32,33,34,37,38,39,40,41,42,43));
        for(Object o: Mlg.items){

            String m = (String) o;

            String name1 = "";
            String name2 = "";
            String name3 = "";
            String name4 = "";
            String name5 = "";

            String string = m.replace("_", " ").toLowerCase();
            String[] pa = string.split(" ");

            int i = 1;
            for(String part : pa){
                part = part.substring(0, 1).toUpperCase() + part.substring(1);
                if(i == 1 && pa.length == 1)
                    name1 = part + "";
                if(i == 2 && pa.length == 2)
                    name2 = part + "";
                if(i == 3 && pa.length == 3)
                    name3 = part + "";
                if(i == 4 && pa.length == 4)
                    name4 = part + "";
                if(i == 5 && pa.length == 5)
                    name5 = part + "";
                if(i == 1 && pa.length > 1)
                    name1 = part + " ";
                if(i == 2 && pa.length > 2)
                    name2 = part + " ";
                if(i == 3 && pa.length > 3)
                    name3 = part + " ";
                if(i == 4 && pa.length > 4)
                    name4 = part + " ";
                if(i == 5 && pa.length > 5)
                    name5 = part + " ";
                i++;
            }


            ItemStack material = new ItemStack(Objects.requireNonNull(Material.getMaterial(m)), 1);
            ItemMeta im = material.getItemMeta();
            im.setDisplayName(ChatColor.YELLOW + name1 + name2 + name3 + name4 + name5);
            itemname = ChatColor.YELLOW + name1 + name2 + name3 + name4 + name5;
            material.setItemMeta(im);

            mlginv.setItem(intlist.get(index), material);
            index++;
        }

        //CLOSE

        ItemStack close = new ItemStack(Material.BARRIER);
        ItemMeta closemeta = close.getItemMeta();
        closemeta.setDisplayName(ChatColor.RED + "Close");
        close.setItemMeta(closemeta);

        mlginv.setItem(0, close);

        //EDIT

        ItemStack edit = new ItemStack(Material.REDSTONE);
        ItemMeta editmeta = edit.getItemMeta();
        editmeta.setDisplayName(ChatColor.YELLOW + "Edit/Add Item");
        editmeta.setLore(Arrays.asList(ChatColor.GRAY + "Click to add an item to clutch with!"));
        edit.setItemMeta(editmeta);

        mlginv.setItem(3, edit);
        mlginv.setItem(5, edit);

        //FRAME

        ItemStack frame = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);

        for(int i : new int[]{1,2,6,7,8,9,17,18,26,27,35,36,37,38,39,40,41,42,43,44}){
            mlginv.setItem(i , frame);
        }

        p.openInventory(mlginv);
    }
}
