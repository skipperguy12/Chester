package com.github.plastix.chester.gui.replace.submenus;

import com.github.plastix.chester.gui.Menu;
import com.github.plastix.chester.gui.MenuManager;
import com.github.plastix.chester.gui.annotation.MenuInventory;
import com.github.plastix.chester.gui.replace.ReplaceMenu;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

@MenuInventory(
    slots = 27,
    name = "Container Filters",
    filler = Material.AIR,
    onClose = ReplaceMenu.class)
public class ContainerFilterMenu extends Menu {

    public ContainerFilterMenu(MenuManager manager, Inventory inv) {
        super(manager, inv);
    }
}