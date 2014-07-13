package com.github.plastix.chester.gui;

import org.bukkit.inventory.Inventory;

public class Menu {
    protected Inventory inv;
    protected MenuManager manager;

    public Menu(MenuManager manager, Inventory inv) {
        this.manager = manager;
        this.inv = inv;
    }

    public void setInventory(Inventory inv) {
        this.inv = inv;
    }

    public Inventory getInventory() {
        return this.inv;
    }
}
