package com.github.plastix.chester.gui.replace;

import com.github.plastix.chester.ChesterPlayer;
import com.github.plastix.chester.gui.replace.submenus.ContainerFilterMenu;
import com.github.plastix.chester.gui.replace.submenus.ItemFilterMenu;
import net.njay.Menu;
import net.njay.MenuManager;
import net.njay.annotation.*;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

/**
 * Class to represent the main replace menu.
 */
@MenuInventory(
    slots = 27,
    name = "§3Replace Menu"
)
@IgnoreSlots(
    slots = {12, 14},
    items = {
        @ItemStackAnnotation(
            material = Material.PAPER,
            name = "Item to Replace"),
        @ItemStackAnnotation(
            material = Material.PAPER,
            name = "New Item")
    }
)
@NestedMenu(
    {
        ContainerFilterMenu.class,
        ItemFilterMenu.class
    }
)
public class ReplaceMenu extends Menu {

    /**
     * Constructor for a new ReplaceMenu from a {@link net.njay.MenuManager} and a {@link org.bukkit.inventory.Inventory}
     *
     * @param manager the inventory manager the menu is assigned to.
     * @param inv the inventory for the menu to be placed in.
     */
    public ReplaceMenu(MenuManager manager, Inventory inv) {
        super(manager, inv);
    }

    /* Item Filter Menu */
    @MenuItem(
        slot = 0,
        item = @ItemStackAnnotation(
            material = Material.ITEM_FRAME,
            name = "Item Filters"
        )
    )
    public void itemFilterMenu(ChesterPlayer player) {
        player.setActiveMenu(new ItemFilterMenu(manager, null));
    }

    /* Container Filter Menu */
    @MenuItem(
        slot = 1,
        item = @ItemStackAnnotation(
            material = Material.CHEST,
            name = "Container Filters"
        )
    )
    public void containerFilterMenu(ChesterPlayer player) {
        player.setActiveMenu(new ContainerFilterMenu(manager, null));
    }

    /* Save and close */
    @MenuItem(
        slot = 8,
        item = @ItemStackAnnotation(
            material = Material.WOOL,
            name = "Save & Apply",
            durability = 5
        )
    )
    public void saveAndApply(ChesterPlayer player) {
        player.resetManager();
    }


}
