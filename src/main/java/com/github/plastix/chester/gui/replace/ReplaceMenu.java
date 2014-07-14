package com.github.plastix.chester.gui.replace;

import com.github.plastix.chester.ChesterPlayer;
import com.github.plastix.chester.gui.replace.submenus.ContainerFilterMenu;
import com.github.plastix.chester.gui.replace.submenus.ItemFilterMenu;
import net.njay.Menu;
import net.njay.MenuManager;
import net.njay.annotation.*;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

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

    public ReplaceMenu(MenuManager manager, Inventory inv) {
        super(manager, inv);
    }

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
