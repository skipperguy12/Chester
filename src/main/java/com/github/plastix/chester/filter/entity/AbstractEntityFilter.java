package com.github.plastix.chester.filter.entity;

import com.github.plastix.chester.filter.Filter;

public abstract class AbstractEntityFilter implements Filter {

    public abstract boolean query(Object thisEntity);

    public boolean query(Object... objects) {
        if (objects.length != 2) return false;
        return query(objects[0]);
    }

}
