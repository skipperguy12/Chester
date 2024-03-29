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
 * Class to represent the item containerFilters menu.
 */
@MenuInventory(
    slots = 9,
    name = "§5Item Filters",
    onClose = ReplaceMenu.class
)
public class ItemFilterMenu extends Menu {

    Map<FilterType.ITEM, Boolean> filters = new HashMap<>();

    /**
     * Constructor for a new ItemFilterMenu from a {@link net.njay.MenuManager} and a {@link org.bukkit.inventory.Inventory}
     *
     * @param manager the inventory manager the menu is assigned to.
     * @param inv     the inventory for the menu to be placed in.
     */
    public ItemFilterMenu(MenuManager manager, Inventory inv) {
        super(manager, inv);
        //Add all item filters to the filters map
        for (FilterType.ITEM f : FilterType.ITEM.values())
            filters.put(f, false);
        //Enable chests by default
        filters.put(FilterType.ITEM.MATERIAL, true);
    }

    @MenuItem(
        slot = 0,
        item = @ItemStackAnnotation(
            material = Material.HOPPER,
            name = "§b§lMaterials",
            lore = "§aENABLED"
        )
    )
    public void materialFilter(ChesterPlayer player) {
        toggleItem(FilterType.ITEM.MATERIAL, 0, "§b§lMaterials");
    }

    @MenuItem(
        slot = 1,
        item = @ItemStackAnnotation(
            material = Material.HOPPER,
            name = "§b§lNames",
            lore = "§cDISABLED"
        )
    )
    public void nameFilter(ChesterPlayer player) {
        toggleItem(FilterType.ITEM.NAME, 1, "§b§lNames");
    }

    @MenuItem(
        slot = 2,
        item = @ItemStackAnnotation(
            material = Material.HOPPER,
            name = "§b§lLore",
            lore = "§cDISABLED"
        )
    )
    public void loreFilter(ChesterPlayer player) {
        toggleItem(FilterType.ITEM.LORE, 2, "§b§lLore");
    }

    @MenuItem(
        slot = 3,
        item = @ItemStackAnnotation(
            material = Material.HOPPER,
            name = "§b§lAmount",
            lore = "§cDISABLED"
        )
    )
    public void amountFilter(ChesterPlayer player) {
        toggleItem(FilterType.ITEM.AMOUNT, 3, "§b§lAmount");
    }

    @MenuItem(
        slot = 4,
        item = @ItemStackAnnotation(
            material = Material.HOPPER,
            name = "§b§lDurability",
            lore = "§cDISABLED"
        )
    )
    public void durabilityFilter(ChesterPlayer player) {
        toggleItem(FilterType.ITEM.DURABILITY, 4, "§b§lDurability");
    }

    @MenuItem(
        slot = 5,
        item = @ItemStackAnnotation(
            material = Material.HOPPER,
            name = "§b§lEnchantments",
            lore = "§cDISABLED"
        )
    )
    public void enchantmentFilter(ChesterPlayer player) {
        toggleItem(FilterType.ITEM.ENCHANTMENT, 5, "§b§lEnchantments");
    }


    private void toggleItem(FilterType.ITEM type, int slot, String name){
        filters.put(type, !filters.get(type));
        ItemUtils.getNamedItemStack(this.getInventory().getItem(slot), name, Arrays.asList(filters.get(type) ? ChatColor.GREEN + "ENABLED" : ChatColor.RED + "DISABLED"));
    }

    public List<FilterType.ITEM> getFilters() {
        List<FilterType.ITEM> enabledFilters = new ArrayList<>();
        for (FilterType.ITEM f : FilterType.ITEM.values())
            if (filters.get(f))
                enabledFilters.add(f);
        return enabledFilters;
    }
}
