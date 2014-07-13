package com.github.plastix.chester;


import com.google.common.collect.Maps;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;

/**
 * Class to manage the players
 */
public class ChesterPlayerManager {
    // Mapping of Bukkit players to Chester Players
    private static HashMap<Player, ChesterPlayer> players = Maps.newHashMap();

    /**
     * Get the ChesterPlayer from player name
     *
     * @param p The player name
     * @return The ChesterPlayer
     */
    public static ChesterPlayer getPlayer(String p) {
        return getPlayer(Bukkit.getPlayerExact(p));
    }

    /**
     * Returns the ChesterPlayer from player
     *
     * @param p The player
     * @return The ChesterPlayer from Player
     */
    public static ChesterPlayer getPlayer(Player p) {
        if (players.containsKey(p)) return players.get(p);
        ChesterPlayer pl = new ChesterPlayer(p);
        players.put(p, pl);
        return pl;
    }

    /**
     * Removes the player from the list from player
     *
     * @param p The player
     */
    public static void removePlayer(Player p) {
        players.remove(p);
    }
}
