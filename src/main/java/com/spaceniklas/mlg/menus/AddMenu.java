package com.spaceniklas.mlg.menus;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class AddMenu {
        public static void openAddMenu(Player p) {
            try {
                Inventory addinv = Bukkit.createInventory(p, 36, ChatColor.BLACK.toString() + "Add an item!");

                //SKULL
                ItemStack redstone = new ItemStack(Material.REDSTONE);
                ItemMeta redmeta = redstone.getItemMeta();
                redmeta.setDisplayName(ChatColor.YELLOW + "Edit/Add item");
                redstone.setItemMeta(redmeta);

                addinv.setItem(4, redstone);

                //YES
                ItemStack yes = new ItemStack(Material.GREEN_WOOL);
                ItemMeta yesmeta = yes.getItemMeta();
                yesmeta.setDisplayName(ChatColor.GREEN + "Add");
                yes.setItemMeta(yesmeta);

                addinv.setItem(20, yes);

                //ARROW-UP
                ItemStack arrowu = new ItemStack(Material.PLAYER_HEAD);
                SkullMeta arrowumeta = (SkullMeta) arrowu.getItemMeta();
                arrowumeta.setDisplayName(ChatColor.GRAY + "Put your item into the slot above!");
                arrowumeta.setOwningPlayer(Bukkit.getOfflinePlayer("MHF_ArrowUp"));
                arrowu.setItemMeta(arrowumeta);

                addinv.setItem(22, arrowu);


                //NO
                ItemStack no = new ItemStack(Material.RED_WOOL);
                ItemMeta nometa = no.getItemMeta();
                nometa.setDisplayName(ChatColor.RED + "Cancel");
                no.setItemMeta(nometa);

                addinv.setItem(24, no);

                //CLOSE

                ItemStack close = new ItemStack(Material.BARRIER);
                ItemMeta closemeta = close.getItemMeta();
                closemeta.setDisplayName(ChatColor.RED + "Close");
                close.setItemMeta(closemeta);

                addinv.setItem(0, close);

                //FRAME

                ItemStack frame = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);

                for (int i : new int[]{1, 2, 3, 5, 6, 7, 8, 9,10,11,12,19,21,14,15,16,23,25, 17, 18, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35}) {
                    addinv.setItem(i, frame);
                }

                p.openInventory(addinv);
            } catch (IllegalArgumentException x) {

            }
        }
}
