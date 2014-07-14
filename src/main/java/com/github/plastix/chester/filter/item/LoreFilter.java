package com.github.plastix.chester.filter.item;

import org.bukkit.inventory.ItemStack;

public class LoreFilter extends AbstractItemFilter {

    @Override
    public boolean query(Object thisItem, Object thatItem) {
        if (thisItem instanceof ItemStack && thatItem instanceof ItemStack) {
            if (((ItemStack)thisItem).getItemMeta().getLore().equals(((ItemStack)thatItem).getItemMeta().getLore())) return true;
        }
        return false;
    }
}
