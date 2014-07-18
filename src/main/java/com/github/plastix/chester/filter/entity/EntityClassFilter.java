package com.github.plastix.chester.filter.entity;

import org.bukkit.inventory.InventoryHolder;

public class EntityClassFilter extends AbstractEntityFilter {

    private Class<? extends InventoryHolder> clazz;

    public EntityClassFilter(Class<? extends InventoryHolder> c) {
        this.clazz = c;
    }

    @Override
    public boolean query(Object thisEntity) {
        if (thisEntity == null) return false;
        return clazz.isAssignableFrom(thisEntity.getClass());
    }
}
