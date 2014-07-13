package com.github.plastix.chester.gui;

import org.bukkit.Material;

public @interface MenuItem {

    int slot();

    Material material();

    String name();

    String[] lore();


}
