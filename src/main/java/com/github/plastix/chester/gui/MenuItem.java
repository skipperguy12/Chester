package com.github.plastix.chester.gui;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public @interface MenuItem {

    int slot();

    Material material();

    String name();

    String[] lore();


}
