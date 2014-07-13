package com.github.plastix.chester.gui;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

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
