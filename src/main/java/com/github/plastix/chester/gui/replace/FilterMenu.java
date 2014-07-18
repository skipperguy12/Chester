package com.github.plastix.chester.gui.replace;

import com.github.plastix.chester.filter.Filter;
import com.github.plastix.chester.filter.FilterFactory;
import com.github.plastix.chester.filter.FilterType;
import net.njay.Menu;
import net.njay.MenuManager;
import net.njay.utils.ItemUtils;
import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;

import java.util.*;

public abstract class FilterMenu extends Menu {

    protected Map<FilterType, Boolean> filters = new HashMap<>();

    protected FilterMenu(MenuManager manager, Inventory inv) {
        super(manager, inv);
    }

    protected void toggleItem(FilterType type, int slot, String name) {
        filters.put(type, !filters.get(type));
        ItemUtils.getNamedItemStack(this.getInventory().getItem(slot), name, Arrays.asList(filters.get(type) ? ChatColor.GREEN + "ENABLED" : ChatColor.RED + "DISABLED"));
    }

    public List<Filter> getFilters(){
        List<Filter> enabledFilters = new ArrayList<>();
        for (FilterType f : FilterType.values())
            if (filters.get(f))
                enabledFilters.add(FilterFactory.createFilter(f));
        return enabledFilters;
    }
}
