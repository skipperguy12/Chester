package com.github.plastix.chester.command;

import com.github.plastix.chester.ChesterPlayer;
import com.github.plastix.chester.gui.replace.ReplaceMenu;
import com.sk89q.minecraft.util.commands.Command;
import com.sk89q.minecraft.util.commands.CommandContext;
import com.sk89q.minecraft.util.commands.CommandException;
import net.njay.MenuFramework;
import org.bukkit.command.CommandSender;

/**
 * Replace command for Chester
 */
public class ChesterReplaceCommand {
    @Command(aliases = {"replace", "r"}, desc = "Command to bring up replace menu")
    public static void replace(final CommandContext args, CommandSender sender) throws CommandException {
        ChesterPlayer player = ((ChesterPlayer) MenuFramework.getPlayerManager().getPlayer(CommandUtil.ensurePlayer(sender)));
        player.setActiveMenu(new ReplaceMenu(player.getMenuManager(), null));
    }
}