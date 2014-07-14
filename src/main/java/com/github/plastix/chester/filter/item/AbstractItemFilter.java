package com.github.plastix.chester.filter.item;

import com.github.plastix.chester.filter.Filter;

public abstract class AbstractItemFilter implements Filter {

    public abstract boolean query(Object thisItem, Object thatItem);

    public boolean query(Object... objects) {
        if (objects.length != 2) return false;
        return query(objects[0], objects[1]);
    }

}
