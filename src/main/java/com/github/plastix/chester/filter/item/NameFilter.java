package com.github.plastix.chester.filter.item;

import org.bukkit.inventory.ItemStack;

public class NameFilter extends AbstractItemFilter {

    @Override
    public boolean query(Object thisItem, Object thatItem) {
        if (thisItem instanceof ItemStack && thatItem instanceof ItemStack) {
            if (((ItemStack)thisItem).getItemMeta().getDisplayName().equals(((ItemStack)thatItem).getItemMeta().getDisplayName())) return true;
        }
        return false;
    }
}
