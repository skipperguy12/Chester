package com.github.plastix.chester.filter;

import com.github.plastix.chester.filter.item.*;
import org.bukkit.block.Dispenser;
import org.bukkit.block.Dropper;
import org.bukkit.block.Furnace;

import com.github.plastix.chester.filter.container.BlockClassFilter;
import com.github.plastix.chester.filter.container.ChestFilter;
import com.github.plastix.chester.filter.container.TrappedChestFilter;

public class FilterFactory {

    public enum FilterType {
        MATERIAL,
        NAME,
        LORE,
        AMOUNT,
        DURABILITY,
        ENCHANTMENT,
        CHEST,
        TRAPPED_CHEST,
        FURNACE,
        DROPPER,
        DISPENSER,

    }

    public static Filter createFilter(FilterType type) {
        switch(type){
            case MATERIAL:
                return new MaterialFilter();
            case NAME:
                return new NameFilter();
            case LORE:
                return new LoreFilter();
            case AMOUNT:
                return new AmountFilter();
            case DURABILITY:
                return new DurabilityFilter();
            case ENCHANTMENT:
                return new EnchantmentFilter();
            case CHEST:
                return new ChestFilter();
            case TRAPPED_CHEST:
                return new TrappedChestFilter();
            case FURNACE:
                return new BlockClassFilter(Furnace.class);
            case DROPPER:
                return new BlockClassFilter(Dropper.class);
            case DISPENSER:
                return new BlockClassFilter(Dispenser.class);
            default:
                return null;
        }
    }

}
