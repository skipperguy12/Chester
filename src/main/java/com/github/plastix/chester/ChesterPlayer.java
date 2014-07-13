package com.github.plastix.chester;


import org.bukkit.entity.Player;

/**
 * Custom player object used for easy accessing
 */
public class ChesterPlayer {
    // players bukkit name
    private String name;

    // org.bukkit Player object
    private Player bukkit;

    /**
     * Creates a new ChesterPlayer from the bukkit Player instance
     *
     * @param bukkit the bukkit player
     */
    public ChesterPlayer(Player bukkit) {
        this.bukkit = bukkit;
        this.name = bukkit.getName();
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

    @Override
    public String toString() {
        return "ChesterPlayer{" +
                "name='" + name + '\'' +
                ", bukkit=" + bukkit +
                '}';
    }
}
