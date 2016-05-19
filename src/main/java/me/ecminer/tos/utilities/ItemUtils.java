package me.ecminer.tos.utilities;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemUtils {

    public static ItemStack setItemMeta(ItemStack item, String displayName, String... lore) {
        return setItemMeta(item, displayName, Arrays.asList(lore));
    }

    public static ItemStack setItemMeta(ItemStack item, String displayName, List<String> lore) {
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayName);
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack setDisplayName(ItemStack item, String displayName) {
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayName);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack setLore(ItemStack item, String... lore) {
        return setLore(item, Arrays.asList(lore));
    }

    public static ItemStack setLore(ItemStack item, List<String> lore) {
        ItemMeta meta = item.getItemMeta();
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public static String getDisplayName(ItemStack item) {
        return item.hasItemMeta() ? item.getItemMeta().getDisplayName() : null;
    }

    public static List<String> getLore(ItemStack item) {
        return item.hasItemMeta() ? item.getItemMeta().getLore() : new ArrayList<>();
    }

}
