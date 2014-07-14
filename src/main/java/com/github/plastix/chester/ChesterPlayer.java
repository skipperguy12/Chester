package com.github.plastix.chester;

import net.njay.player.MenuPlayer;
import org.bukkit.entity.Player;

/**
 * Custom player object used for easy accessing
 */
public class ChesterPlayer extends MenuPlayer {
    /**
     * Creates a new ChesterPlayer from the bukkit Player instance
     *
     * @param bukkit the bukkit player
     */
    public ChesterPlayer(Player bukkit) {
        super(bukkit);
        resetManager();
    }


    @Override
    public String toString() {
        return "ChesterPlayer{" +
            "name='" + bukkit.getName() + '\'' +
            ", bukkit=" + bukkit +
            '}';
    }
}
