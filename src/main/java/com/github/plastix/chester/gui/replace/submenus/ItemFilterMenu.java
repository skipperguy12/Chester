package com.github.plastix.chester.gui.replace.submenus;

import com.github.plastix.chester.filter.Filter;
import com.github.plastix.chester.filter.FilterFactory;
import com.github.plastix.chester.gui.FilterMenu;
import com.github.plastix.chester.gui.replace.ReplaceMenu;
import net.njay.Menu;
import net.njay.MenuManager;
import net.njay.annotation.MenuInventory;
import org.bukkit.inventory.Inventory;

import java.util.Arrays;
import java.util.List;

/**
 * Class to represent the item containerFilters menu.
 */
@MenuInventory(
    slots = 27,
    name = "§5Item Filters",
    onClose = ReplaceMenu.class
)
public class ItemFilterMenu extends Menu implements FilterMenu {

    /**
     * Constructor for a new ItemFilterMenu from a {@link net.njay.MenuManager} and a {@link org.bukkit.inventory.Inventory}
     *
     * @param manager the inventory manager the menu is assigned to.
     * @param inv     the inventory for the menu to be placed in.
     */
    public ItemFilterMenu(MenuManager manager, Inventory inv) {
        super(manager, inv);
    }

    @Override
    public List<Filter> getFilters() {
        return Arrays.asList(FilterFactory.createFilter(FilterFactory.FilterType.MATERIAL)); //TODO: Fetch from actual GUI, this is a dummy list
    }
}
