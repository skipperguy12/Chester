package com.github.plastix.chester.filter.container;

import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.block.DoubleChest;

public class TrappedChestFilter extends AbstractContainerFilter {

    @Override
    public boolean query(Object thisblock) {
        if (thisblock instanceof Chest || thisblock instanceof DoubleChest) {
            Chest chest = (Chest) thisblock;
            if (chest.getBlockInventory().getSize() == 27 && chest.getType() == Material.TRAPPED_CHEST)
                return true;
        }
        return false;
    }
}
