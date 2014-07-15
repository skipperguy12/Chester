package com.github.plastix.chester.gui.replace;

import com.github.plastix.chester.Chester;
import com.github.plastix.chester.ChesterPlayer;
import com.github.plastix.chester.filter.Filter;
import com.github.plastix.chester.gui.replace.submenus.ContainerFilterMenu;
import com.github.plastix.chester.gui.replace.submenus.ItemFilterMenu;
import com.github.plastix.chester.operations.ContainerReplaceOperation;
import com.google.common.collect.Lists;
import com.sk89q.minecraft.util.commands.ChatColor;
import com.sk89q.minecraft.util.commands.CommandException;
import com.sk89q.worldedit.bukkit.selections.Selection;
import net.njay.Menu;
import net.njay.MenuManager;
import net.njay.annotation.*;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

import java.util.List;

/**
 * Class to represent the main replace menu.
 */
@MenuInventory(
    slots = 27,
    name = "§5Replace Menu"
)
@IgnoreSlots(
    slots = {12, 14},
    items = {
        @ItemStackAnnotation(
            material = Material.STAINED_GLASS_PANE,
            durability = 14,
            name = "§r§lItem to Replace"),
        @ItemStackAnnotation(
            material = Material.STAINED_GLASS_PANE,
            durability = 5,
            name = "§r§lNew Item")
    }
)
@NestedMenu(
    {
        ContainerFilterMenu.class,
        ItemFilterMenu.class
    }
)
public class ReplaceMenu extends Menu {

    /**
     * Constructor for a new ReplaceMenu from a {@link net.njay.MenuManager} and a {@link org.bukkit.inventory.Inventory}
     *
     * @param manager the inventory manager the menu is assigned to.
     * @param inv     the inventory for the menu to be placed in.
     */
    public ReplaceMenu(MenuManager manager, Inventory inv) {
        super(manager, inv);
    }

    /* Item Filter Menu */
    @MenuItem(
        slot = 0,
        item = @ItemStackAnnotation(
            material = Material.HOPPER,
            name = "§b§lItem Filters"
        )
    )
    public void itemFilterMenu(ChesterPlayer player) {
        player.setActiveMenu(new ItemFilterMenu(manager, null));
    }

    /* Container Filter Menu */
    @MenuItem(
        slot = 1,
        item = @ItemStackAnnotation(
            material = Material.CHEST,
            name = "§6§lContainer Filters"
        )
    )
    public void containerFilterMenu(ChesterPlayer player) {
        player.setActiveMenu(new ContainerFilterMenu(manager, null));
    }

    /* Save and close */
    @MenuItem(
        slot = 8,
        item = @ItemStackAnnotation(
            material = Material.WOOL,
            name = "§a§lSave & Apply",
            durability = 5
        )
    )
    public void saveAndApply(ChesterPlayer player) {
        try {
            Selection selection = Chester.get().getWorldEdit().getSelection(player.getBukkit());
            if (selection == null) {
                player.getBukkit().closeInventory();
                player.getBukkit().sendMessage(ChatColor.RED + "You must make a WorldEdit Selection first");
                player.getBukkit().sendMessage(ChatColor.DARK_PURPLE + "Your work has" + ChatColor.GOLD + " NOT" + ChatColor.DARK_PURPLE + " been lost");
                player.getBukkit().sendMessage(ChatColor.DARK_PURPLE + "You can reopen your GUI after making the WorldEdit selection by using the following command: " + ChatColor.GOLD + "/chester replace");
                return;
            }

            List<Filter> filters = Lists.newArrayList();

            if (player.getMenuManager().hasMenu(ItemFilterMenu.class))
                filters.addAll(((ItemFilterMenu) player.getMenuManager().getMenu(ItemFilterMenu.class)).getFilters());

            if (player.getMenuManager().hasMenu(ContainerFilterMenu.class))
                filters.addAll(((ContainerFilterMenu) player.getMenuManager().getMenu(ContainerFilterMenu.class)).getFilters());

            ContainerReplaceOperation operation = new ContainerReplaceOperation(getInventory(), filters.toArray(new Filter[filters.size()]));
            operation.execute(selection);

            player.resetManager();
            player.getBukkit().closeInventory();

        } catch (CommandException e) {
            e.printStackTrace();
        }
    }


}
