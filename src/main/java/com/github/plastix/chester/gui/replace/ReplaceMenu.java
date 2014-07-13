package com.github.plastix.chester.gui.replace;

import com.github.plastix.chester.ChesterPlayer;
import com.github.plastix.chester.gui.Menu;
import com.github.plastix.chester.gui.MenuManager;
import com.github.plastix.chester.gui.annotation.IgnoreSlots;
import com.github.plastix.chester.gui.annotation.MenuInventory;
import com.github.plastix.chester.gui.annotation.MenuItem;
import com.github.plastix.chester.gui.annotation.NestedMenu;
import com.github.plastix.chester.gui.replace.submenus.ContainerFilterMenu;
import com.github.plastix.chester.gui.replace.submenus.ItemFilterMenu;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

@MenuInventory(
    slots = 27,
    name = "Replace Menu",
    filler = Material.AIR
)
@IgnoreSlots(slots = {12, 14}, materials = {Material.PAPER, Material.PAPER})
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
        name = "Item Filters",
        material = Material.HOPPER
    )
    public void itemFilterMenu(ChesterPlayer player) {
        player.setActiveMenu(new ItemFilterMenu(manager, null));
    }

    @MenuItem(
        slot = 1,
        name = "Container Filters",
        material = Material.CHEST
    )
    public void containerFilterMenu(ChesterPlayer player) {
        player.setActiveMenu(new ContainerFilterMenu(manager, null));
    }

    @MenuItem(
        slot = 8,
        name = "Save & Apply",
        material = Material.WOOL,
        durability = 5
    )
    public void saveAndApply(ChesterPlayer player) {
        player.resetManager();
    }


}
