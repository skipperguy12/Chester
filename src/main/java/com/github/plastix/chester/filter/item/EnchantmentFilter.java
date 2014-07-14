package com.github.plastix.chester.filter.item;

import org.bukkit.inventory.ItemStack;

public class EnchantmentFilter extends AbstractItemFilter {

    @Override
    public boolean query(Object thisItem, Object thatItem) {
        if (thisItem instanceof ItemStack && thatItem instanceof ItemStack) {
            if (((ItemStack) thisItem).getEnchantments().equals(((ItemStack) thatItem).getEnchantments())) return true;
        }
        return false;
    }
}
