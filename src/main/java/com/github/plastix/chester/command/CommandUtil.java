package com.github.plastix.chester.command;

import com.sk89q.minecraft.util.commands.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Command utility for all commands
 */
public class CommandUtil {
    /**
     * Checks if sender is an instance of a Player
     *
     * @param sender CommandSender from command
     * @return Player
     * @throws CommandException thrown if the sender is not a Player
     */
    public static Player ensurePlayer(CommandSender sender) throws CommandException {
        if (!(sender instanceof Player)) throw new CommandException("Only players may use this command!");
        return (Player) sender;
    }
}