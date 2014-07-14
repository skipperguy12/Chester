package com.github.plastix.chester.gui.replace.submenus;

import com.github.plastix.chester.gui.replace.ReplaceMenu;
import net.njay.Menu;
import net.njay.MenuManager;
import net.njay.annotation.MenuInventory;
import org.bukkit.inventory.Inventory;

@MenuInventory(
    slots = 27,
    name = "§3Item Filters",
    onClose = ReplaceMenu.class
)
public class ItemFilterMenu extends Menu {

    public ItemFilterMenu(MenuManager manager, Inventory inv) {
        super(manager, inv);
    }
}
