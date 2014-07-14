package com.github.plastix.chester.gui.replace.submenus;

import com.github.plastix.chester.gui.replace.ReplaceMenu;
import net.njay.Menu;
import net.njay.MenuManager;
import net.njay.annotation.MenuInventory;
import org.bukkit.inventory.Inventory;

/**
 * Class to represent the container filters menu.
 */
@MenuInventory(
    slots = 27,
    name = "§3Container Filters",
    onClose = ReplaceMenu.class
)
public class ContainerFilterMenu extends Menu {

    /**
     * Constructor for a new ContainerFilter Menu from a {@link net.njay.MenuManager} and a {@link org.bukkit.inventory.Inventory}
     *
     * @param manager the inventory manager the menu is assigned to.
     * @param inv the inventory for the menu to be placed in.
     */
    public ContainerFilterMenu(MenuManager manager, Inventory inv) {
        super(manager, inv);
    }
}