package com.github.plastix.chester.command;

import com.sk89q.minecraft.util.commands.Command;
import com.sk89q.minecraft.util.commands.CommandContext;
import com.sk89q.minecraft.util.commands.CommandException;
import com.sk89q.minecraft.util.commands.NestedCommand;
import org.bukkit.command.CommandSender;

/**
 * Parent/Main command for Chester
 */
public class ChesterParentCommand {
    @Command(aliases = {"chester", "c"}, desc = "All Chester commands", min = 0, max = -1)
    @NestedCommand(ChesterReplaceCommand.class)
    public static void chester(final CommandContext args, CommandSender sender) throws CommandException {
    }
}