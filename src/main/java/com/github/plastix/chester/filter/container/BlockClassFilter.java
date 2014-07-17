package com.github.plastix.chester.filter.container;


import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.inventory.InventoryHolder;

public class BlockClassFilter extends AbstractContainerFilter {
    private Class<? extends InventoryHolder> clazz;
    
    public BlockClassFilter(Class<? extends InventoryHolder> c) {
        this.clazz = c;
    }
    
    @Override
    public boolean query(Object thisBlock) {
        if (thisBlock == null) return false;
        BlockState state = ((Block)thisBlock).getState();
        return clazz.isAssignableFrom(state.getClass());
    }
}
