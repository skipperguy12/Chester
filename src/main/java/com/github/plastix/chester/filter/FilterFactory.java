package com.github.plastix.chester.filter;

import com.github.plastix.chester.filter.item.*;
import org.bukkit.block.Dispenser;
import org.bukkit.block.Dropper;
import org.bukkit.block.Furnace;

import com.github.plastix.chester.filter.container.BlockClassFilter;
import com.github.plastix.chester.filter.container.ChestFilter;
import com.github.plastix.chester.filter.container.TrappedChestFilter;
import org.bukkit.block.Hopper;
import org.bukkit.entity.minecart.HopperMinecart;
import org.bukkit.entity.minecart.PoweredMinecart;
import org.bukkit.entity.minecart.StorageMinecart;

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
        HOPPER,
        CHEST_MINECART,
        FURNACE_MINECART,
        HOPPER_MINECART,

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
            case HOPPER:
                return new BlockClassFilter(Hopper.class);
            case CHEST_MINECART:
                return new BlockClassFilter(StorageMinecart.class);
            case FURNACE_MINECART:
                return new BlockClassFilter(PoweredMinecart.class);
            case HOPPER_MINECART:
                return new BlockClassFilter(HopperMinecart.class);
            default:
                return null;
        }
    }

}
