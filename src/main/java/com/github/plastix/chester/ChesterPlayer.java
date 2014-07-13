package com.github.plastix.chester;


import com.github.plastix.chester.gui.Menu;
import com.github.plastix.chester.gui.MenuManager;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;

/**
 * Custom player object used for easy accessing
 */
public class ChesterPlayer {
    // players bukkit name
    private String name;

    // org.bukkit Player object
    private Player bukkit;

    // player-specific menu manager
    private @Nullable MenuManager menuManager;

    /**
     * Creates a new ChesterPlayer from the bukkit Player instance
     *
     * @param bukkit the bukkit player
     */
    public ChesterPlayer(Player bukkit) {
        this.bukkit = bukkit;
        this.name = bukkit.getName();
        resetManager();
    }

    /**
     * Gets the name of the ChesterPlayer
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the bukkit instance of the player
     *
     * @return the bukkit instance
     */
    public Player getBukkit() {
        return bukkit;
    }

    /**
     * Gets the menu manager instance associated with the player
     *
     * @return the MenuManager instance
     */
    public MenuManager getMenuManager(){ return menuManager; }

    /**
     * Show a menu to the player
     *
     * @param menu Menu you wish to show
     */
    public void setActiveMenu(Menu menu){
        menuManager.setActiveMenu(menu);
        bukkit.openInventory(menuManager.getCurrentMenu().getInventory());
    }

    public void setActiveMenu(Class clazz){
        menuManager.setPreviouslyOpenedActiveMenu(clazz);
        bukkit.openInventory(menuManager.getCurrentMenu().getInventory());
    }

    /**
     * Reset the menu manager, and erases all menu history
     */
    public void resetManager(){ menuManager = new MenuManager(); }

    @Override
    public String toString() {
        return "ChesterPlayer{" +
                "name='" + name + '\'' +
                ", bukkit=" + bukkit +
                '}';
    }
}
