package com.github.plastix.chester.filter.container;

import com.github.plastix.chester.filter.Filter;

public abstract class AbstractContainerFilter implements Filter {

    public abstract boolean query(Object thisBlock);

    public boolean query(Object... objects) {
        if (objects.length != 2) return false;
        return query(objects[0]);
    }

}
