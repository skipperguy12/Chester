package com.github.plastix.chester.filter.item;

import org.bukkit.inventory.ItemStack;

public class DurabilityFilter extends AbstractItemFilter {

    @Override
    public boolean query(Object thisItem, Object thatItem) {
        if (thisItem instanceof ItemStack && thatItem instanceof ItemStack) {
            if (((ItemStack) thisItem).getDurability() == ((ItemStack) thatItem).getDurability()) return true;
        }
        return false;
    }
}
