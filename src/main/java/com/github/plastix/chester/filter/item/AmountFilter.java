package com.github.plastix.chester.filter.item;

import org.bukkit.inventory.ItemStack;

public class AmountFilter extends AbstractItemFilter {

    @Override
    public boolean query(Object thisItem, Object thatItem) {
        if (thisItem instanceof ItemStack && thatItem instanceof ItemStack) {
            if (((ItemStack) thisItem).getAmount() == ((ItemStack) thatItem).getAmount()) return true;
        }
        return false;
    }
}
