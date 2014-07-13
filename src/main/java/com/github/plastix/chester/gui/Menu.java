package com.github.plastix.chester.gui;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Menu {
    private final static ItemStack DEFAULT_EMPTY_SLOT = new ItemStack(119, 1);
    private final static Integer DEFAULT_INVENTORY_SIZE = 27;

    protected Inventory inv;
    protected final String title;
    protected final int size;
    protected final ItemStack emptySlot;
    protected MenuManager manager;

    public Menu(MenuManager manager, Inventory inv, String title, int size, ItemStack emptySlot) {
        this.manager = manager;
        this.inv = inv;
        this.title = title;
        this.size = size;
        this.emptySlot = emptySlot;
    }

    public Menu(MenuManager manager, Inventory inv, String title, int size) {
        this.manager = manager;
        this.inv = inv;
        this.title = title;
        this.size = DEFAULT_INVENTORY_SIZE;
        this.emptySlot = DEFAULT_EMPTY_SLOT;
    }

    public Menu(MenuManager manager, Inventory inv, String title) {
        this.manager = manager;
        this.inv = inv;
        this.title = title;
        this.size = DEFAULT_INVENTORY_SIZE;
        this.emptySlot = DEFAULT_EMPTY_SLOT;
    }

    public void setInventory(Inventory inv) {
        this.inv = inv;
    }

    public Inventory getInventory() {
        return this.inv;
    }

    public String getTitle() {
        return title;
    }

    public int getSize() {
        return size;
    }

    public ItemStack getEmptySlot() {
        return emptySlot;
    }
}
