package com.github.plastix.chester.filter.container;

import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.block.DoubleChest;

public class ChestFilter extends AbstractBlockFilter {

    @Override
    public boolean query(Object thisblock){
        //Check inventory sizes because we don't want to deal with glitched chests
        //Use getBlockInventory so we handle one portion of DoubleChest inventories at a time
        BlockState state = ((Block)thisblock).getState();
        if(state instanceof Chest || state instanceof DoubleChest) {
            return true;
        }
        return false;
    }
}
