package com.github.plastix.chester.filter.entity;

import com.sk89q.worldedit.bukkit.entity.BukkitEntity;
import org.bukkit.inventory.InventoryHolder;

public class EntityClassFilter extends AbstractEntityFilter {

    private Class<? extends InventoryHolder> clazz;

    public EntityClassFilter(Class<? extends InventoryHolder> c) {
        this.clazz = c;
    }

    @Override
    public boolean query(Object thisEntity) {
        if (thisEntity == null) return false;
        if(thisEntity instanceof BukkitEntity) {
            BukkitEntity bukkitEntity = (BukkitEntity) thisEntity;
            return clazz.isAssignableFrom(bukkitEntity.getClass());
        }
        return false;
    }
}
