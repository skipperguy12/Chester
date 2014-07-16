package com.github.plastix.chester.gui.replace.submenus;

import com.github.plastix.chester.ChesterPlayer;
import com.github.plastix.chester.filter.FilterType;
import com.github.plastix.chester.gui.replace.ReplaceMenu;
import net.njay.Menu;
import net.njay.MenuManager;
import net.njay.annotation.ItemStackAnnotation;
import net.njay.annotation.MenuInventory;
import net.njay.annotation.MenuItem;
import net.njay.utils.ItemUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

import java.util.*;

/**
 * Class to represent the container containerFilters menu.
 */
@MenuInventory(
    slots = 9,
    name = "§5Container Filters",
    onClose = ReplaceMenu.class
)
public class ContainerFilterMenu extends Menu {

    Map<FilterType.CONTAINER, Boolean> filters = new HashMap<>();

    /**
     * Constructor for a new ContainerFilter Menu from a {@link net.njay.MenuManager} and a {@link org.bukkit.inventory.Inventory}
     *
     * @param manager the inventory manager the menu is assigned to.
     * @param inv     the inventory for the menu to be placed in.
     */
    public ContainerFilterMenu(MenuManager manager, Inventory inv) {
        super(manager, inv);
        //Add all container filters to the filters map
        for (FilterType.CONTAINER f : FilterType.CONTAINER.values())
            filters.put(f, false);
        //Enable chests by default
        filters.put(FilterType.CONTAINER.CHEST, true);
    }

    @MenuItem(
        slot = 0,
        item = @ItemStackAnnotation(
            material = Material.CHEST,
            name = "§b§lChests",
            lore = "§aENABLED"
        )
    )
    public void chestFilter(ChesterPlayer player) {
        toggleItem(FilterType.CONTAINER.CHEST, 0, "§b§lChests");
    }

    @MenuItem(
        slot = 1,
        item = @ItemStackAnnotation(
            material = Material.TRAPPED_CHEST,
            name = "§b§lTrapped Chests",
            lore = "§cDISABLED"
        )
    )
    public void trappedChestFilter(ChesterPlayer player) {
        toggleItem(FilterType.CONTAINER.TRAPPED_CHEST, 1, "§b§lTrapped Chests");
    }

    @MenuItem(
        slot = 2,
        item = @ItemStackAnnotation(
            material = Material.FURNACE,
            name = "§b§lFurnaces",
            lore = "§cDISABLED"
        )
    )
    public void furnaceFilter(ChesterPlayer player) {
        toggleItem(FilterType.CONTAINER.FURNACE, 2, "§b§lFurnaces");

    }

    @MenuItem(
        slot = 3,
        item = @ItemStackAnnotation(
            material = Material.DROPPER,
            name = "§b§lDroppers",
            lore = "§cDISABLED"
        )
    )
    public void dropperFilter(ChesterPlayer player) {
        toggleItem(FilterType.CONTAINER.DROPPER, 3, "§b§lDroppers");

    }

    @MenuItem(
        slot = 4,
        item = @ItemStackAnnotation(
            material = Material.DISPENSER,
            name = "§b§lDispensers",
            lore = "§cDISABLED"
        )
    )
    public void dispenserFilter(ChesterPlayer player) {
        toggleItem(FilterType.CONTAINER.DISPENSER, 4, "§b§lDispensers");

    }

    @MenuItem(
        slot = 5,
        item = @ItemStackAnnotation(
            material = Material.HOPPER,
            name = "§b§lHoppers",
            lore = "§cDISABLED"
        )
    )
    public void hopperFilter(ChesterPlayer player) {
        toggleItem(FilterType.CONTAINER.HOPPER, 5, "§b§lHoppers");

    }

    @MenuItem(
        slot = 6,
        item = @ItemStackAnnotation(
            material = Material.STORAGE_MINECART,
            name = "§b§lChest Minecarts",
            lore = "§cDISABLED"
        )
    )
    public void storageMinecartFilter(ChesterPlayer player) {
        toggleItem(FilterType.CONTAINER.CHEST_MINECART, 6, "§b§lChest Minecarts");

    }

    @MenuItem(
        slot = 7,
        item = @ItemStackAnnotation(
            material = Material.HOPPER_MINECART,
            name = "§b§lHopper Minecarts",
            lore = "§cDISABLED"
        )
    )
    public void hopperMinecartFilter(ChesterPlayer player) {
        toggleItem(FilterType.CONTAINER.HOPPER_MINECART, 7, "§b§lHopper Minecarts");

    }

    private void toggleItem(FilterType.CONTAINER type, int slot, String name) {
        filters.put(type, !filters.get(type));
        ItemUtils.getNamedItemStack(this.getInventory().getItem(slot), name, Arrays.asList(filters.get(type) ? ChatColor.GREEN + "ENABLED" : ChatColor.RED + "DISABLED"));
    }

    public List<FilterType.CONTAINER> getFilters() {
        List<FilterType.CONTAINER> enabledFilters = new ArrayList<>();
        for (FilterType.CONTAINER f : FilterType.CONTAINER.values())
            if (filters.get(f))
                enabledFilters.add(f);
        return enabledFilters;
    }
}