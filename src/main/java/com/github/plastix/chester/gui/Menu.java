package com.github.plastix.chester.gui;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Menu {
    private final static ItemStack DEFAULT_EMPTY_SLOT = new ItemStack(119, 1);

    protected Inventory inv;
    protected final ItemStack emptySlot;
    protected MenuManager manager;

    public Menu(MenuManager manager, Inventory inv, ItemStack emptySlot) {
        this.manager = manager;
        this.inv = inv;
        this.emptySlot = emptySlot;
    }

    public Menu(MenuManager manager, Inventory inv) {
        this.manager = manager;
        this.inv = inv;
        this.emptySlot = DEFAULT_EMPTY_SLOT;
    }

    public Menu(MenuManager manager, Inventory inv, Material emptySlot){
        this.manager = manager;
        this.inv = inv;
        this.emptySlot = new ItemStack(emptySlot);
    }

    public void setInventory(Inventory inv) {
        this.inv = inv;
    }

    public Inventory getInventory() {
        return this.inv;
    }

    public ItemStack getEmptySlot() {
        return emptySlot;
    }
}
