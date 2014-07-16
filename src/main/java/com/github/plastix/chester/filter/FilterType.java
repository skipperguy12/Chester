package com.github.plastix.chester.filter;

public class FilterType {

    public static enum ITEM {

        MATERIAL,
        NAME,
        LORE,
        AMOUNT,
        DURABILITY,
        ENCHANTMENT;
    }

    public static enum CONTAINER {

        CHEST,
        TRAPPED_CHEST,
        FURNACE,
        DROPPER,
        DISPENSER,
        HOPPER,
        CHEST_MINECART,
        HOPPER_MINECART;
    }
}