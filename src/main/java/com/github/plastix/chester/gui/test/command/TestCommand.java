package com.github.plastix.chester.gui.test.command;

import com.github.plastix.chester.ChesterPlayer;
import com.github.plastix.chester.ChesterPlayerManager;
import com.github.plastix.chester.gui.test.menus.TestMenu;
import com.sk89q.minecraft.util.commands.Command;
import com.sk89q.minecraft.util.commands.CommandContext;
import com.sk89q.minecraft.util.commands.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

public class TestCommand {

    @Command(aliases = {"menu"}, desc = "Show test menu", usage = "", min = 0, max = 0)
    public static void test(CommandContext args, CommandSender sender) throws CommandException {
        ChesterPlayer player = ChesterPlayerManager.getPlayer((Player)sender);
        player.setActiveMenu(new TestMenu(player.getMenuManager(), null));
    }
}
