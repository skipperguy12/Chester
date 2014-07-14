package com.github.plastix.chester.filter.container;


public class BlockClassFilter extends AbstractContainerFilter {
    private Class<?> clazz;
    
    public BlockClassFilter(Class<?> c) {
        this.clazz = c;
    }
    
    @Override
    public boolean query(Object thisBlock) {
        if (thisBlock == null) return false;
        return thisBlock.getClass().equals(this.clazz);
    }
}
