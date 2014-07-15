package com.github.plastix.chester.filter.item;

import org.bukkit.inventory.ItemStack;

public class MaterialFilter extends AbstractItemFilter {

    @Override
    public boolean query(Object thisItem, Object thatItem) {
        if (thisItem instanceof ItemStack && thatItem instanceof ItemStack) {
            if (((ItemStack) thisItem).getType() == ((ItemStack) thatItem).getType()) return true;
        }
        return false;
    }
}
