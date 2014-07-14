package com.github.plastix.chester;

import com.github.plastix.chester.command.ChesterParentCommand;
import com.github.plastix.chester.gui.replace.ReplaceMenu;
import com.sk89q.bukkit.util.CommandsManagerRegistration;
import com.sk89q.minecraft.util.commands.*;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import net.njay.MenuFramework;
import net.njay.MenuRegistry;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;


/**
 * Main Bukkit class
 */
public class Chester extends JavaPlugin {
    /**
     * main instance of Chester
     */
    private static Chester instance;
    /**
     * sk89q's command framework CommandsManager
     */
    private CommandsManager<CommandSender> commands;

    /**
     * Gets the main instance of Chester
     *
     * @return main instance of chester
     */
    public static Chester get() {
        if (instance == null) instance = new Chester();
        return instance;
    }

    public void onEnable() {
        instance = this;

        setupMenus();
        setupCommands();
        registerListeners();
    }

    private void setupMenus() {
        MenuFramework.enable(new MenuRegistry(instance, ReplaceMenu.class), new ChesterPlayerManager());
    }

    public void onDisable() {
        instance = null;
    }

    /**
     * Registers Listeners used by DynamicDatabasePlugin
     */
    private void registerListeners() {

    }

    /**
     * Registers a Bukkit Listener with Bukkit's PluginManager
     *
     * @param listener Listener to register
     */
    private void registerEvents(Listener listener) {
        Bukkit.getPluginManager().registerEvents(listener, get());
    }

    /**
     * Gets a copy of the WorldEdit plugin.
     *
     * @return The WorldEditPlugin instance
     * @throws CommandException If there is no WorldEditPlugin available
     */
    public WorldEditPlugin getWorldEdit() throws CommandException {
        Plugin worldEdit = getServer().getPluginManager().getPlugin("WorldEdit");
        if (worldEdit == null) {
            throw new CommandException("WorldEdit does not appear to be installed.");
        }

        if (worldEdit instanceof WorldEditPlugin) {
            return (WorldEditPlugin) worldEdit;
        } else {
            throw new CommandException("WorldEdit detection failed (report error).");
        }
    }

    private void setupCommands() {
        this.commands = new CommandsManager<CommandSender>() {
            @Override
            public boolean hasPermission(CommandSender sender, String perm) {
                return sender instanceof ConsoleCommandSender || sender.hasPermission(perm);
            }
        };
        CommandsManagerRegistration cmdRegister = new CommandsManagerRegistration(this, this.commands);
        //Register your commands here
        cmdRegister.register(ChesterParentCommand.class);
    }

    // Passes commands from Bukkit to sk89q
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        try {
            this.commands.execute(cmd.getName(), args, sender, sender);
        } catch (CommandPermissionsException e) {
            sender.sendMessage(ChatColor.RED + "You don't have permission.");
        } catch (MissingNestedCommandException e) {
            sender.sendMessage(ChatColor.RED + e.getUsage());
        } catch (CommandUsageException e) {
            sender.sendMessage(ChatColor.RED + e.getMessage());
            sender.sendMessage(ChatColor.RED + e.getUsage());
        } catch (WrappedCommandException e) {
            if (e.getCause() instanceof NumberFormatException) {
                sender.sendMessage(ChatColor.RED + "Number expected, string received instead.");
            } else {
                sender.sendMessage(ChatColor.RED + "An error has occurred. See console.");
                e.printStackTrace();
            }
        } catch (CommandException e) {
            sender.sendMessage(ChatColor.RED + e.getMessage());
        }

        return true;
    }
}
