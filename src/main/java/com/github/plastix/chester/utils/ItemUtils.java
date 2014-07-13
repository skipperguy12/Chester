package com.github.plastix.chester.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * Utils for modifying ItemMeta
 */
public class ItemUtils {

    /**
     * Gets an ItemStack with type type, durability durability amount amount, name name, and lore lore
     *
     * @param type   type of Material
     * @param amount amount of the Material
     * @param name   display name of the ItemStack
     * @param lore   lore of the ItemStack
     * @return ItemStack with params
     */
    public static ItemStack getNamedItemStack(Material type, short durability,  int amount, String name, List<String> lore) {
        ItemStack item = new ItemStack(type, amount);
        item.setDurability(durability);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(name);
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);

        return item;
    }

    /**
     * Gets an ItemStack with type type, amount amount, name name, and lore lore
     *
     * @param type   type of Material
     * @param amount amount of the Material
     * @param name   display name of the ItemStack
     * @param lore   lore of the ItemStack
     * @return ItemStack with params
     */
    public static ItemStack getNamedItemStack(Material type, int amount, String name, List<String> lore) {
        ItemStack item = new ItemStack(type, amount);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(name);
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);

        return item;
    }

    /**
     * Sets the name and lore of an ItemStack
     *
     * @param item ItemStack to modify
     * @param name new name of the ItemStack
     * @param lore new lore of the ItemStack
     * @return ItemStack with set name and lore
     */
    public static ItemStack getNamedItemStack(ItemStack item, String name, List<String> lore) {
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(name);
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);

        return item;
    }

    /**
     * Gets an ItemStack with type type, amount amount, and name name
     *
     * @param type   type of Material
     * @param amount amount of the Material
     * @param name   display name of the ItemStack
     * @return ItemStack with params
     */
    public static ItemStack getNamedItemStack(Material type, int amount, String name) {
        return getNamedItemStack(type, amount, name, new ArrayList<String>());
    }

    /**
     * Sets the name of an ItemStack
     *
     * @param item ItemStack to modify
     * @param name new name of the ItemStack
     * @return ItemStack set name
     */
    public static ItemStack getNamedItemStack(ItemStack item, String name) {
        return getNamedItemStack(item, name, new ArrayList<String>());
    }
}