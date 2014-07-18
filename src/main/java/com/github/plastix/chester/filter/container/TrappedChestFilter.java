package com.github.plastix.chester.filter.container;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.block.DoubleChest;

public class TrappedChestFilter extends AbstractBlockFilter {

    @Override
    public boolean query(Object thisblock) {
        BlockState state = ((Block)thisblock).getState();
        if (state instanceof Chest || state instanceof DoubleChest) {
            if (state.getType() == Material.TRAPPED_CHEST)
                return true;
        }
        return false;
    }
}
