package com.github.plastix.chester.operations;

import com.sk89q.worldedit.bukkit.selections.Selection;

public interface BlockOperation {
    abstract void execute(Selection region);
}
