package com.github.plastix.chester.gui.replace.submenus;

import com.github.plastix.chester.gui.Menu;
import com.github.plastix.chester.gui.MenuManager;
import com.github.plastix.chester.gui.annotation.MenuInventory;
import com.github.plastix.chester.gui.replace.ReplaceMenu;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

@MenuInventory(
    slots = 27,
    name = "Item Filters",
    filler = Material.AIR,
    onClose = ReplaceMenu.class)
public class ItemFilterMenu extends Menu {

    public ItemFilterMenu(MenuManager manager, Inventory inv) {
        super(manager, inv);
    }
}
