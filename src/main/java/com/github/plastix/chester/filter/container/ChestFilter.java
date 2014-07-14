package com.github.plastix.chester.filter.container;

import org.bukkit.block.Chest;
import org.bukkit.block.DoubleChest;

public class ChestFilter extends AbstractContainerFilter {

    @Override
    public boolean query(Object thisblock){
        //Check inventory sizes because we don't want to deal with glitched chests
        //Use getBlockInventory so we handle one portion of DoubleChest inventories at a time
        if((thisblock instanceof Chest || thisblock instanceof DoubleChest) && ((Chest)thisblock).getBlockInventory().getSize() == 27) {
            return true;
        }
        return false;
    }
}
