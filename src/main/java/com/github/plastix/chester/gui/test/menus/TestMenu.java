package com.github.plastix.chester.gui.test.menus;

import com.github.plastix.chester.ChesterPlayer;
import com.github.plastix.chester.gui.*;
import com.github.plastix.chester.gui.annotation.MenuInventory;
import com.github.plastix.chester.gui.annotation.MenuItem;
import com.github.plastix.chester.gui.annotation.NestedMenu;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

@MenuInventory(slots = 27, name = "Big Menu", filler = Material.BEDROCK)
@NestedMenu({TestMenu.TestSubMenu.class})
public class TestMenu extends Menu {

    public TestMenu(MenuManager manager, Inventory inv) {
        super(manager, inv, "Test Main Menu");
    }

    @MenuItem(slot = 5, name = "TestItem1", lore = "TestLore1", material = Material.NETHER_STAR)
    public void testItem1(ChesterPlayer player) {
        player.getBukkit().sendMessage("This is a test message");
        player.setActiveMenu(new TestSubMenu(manager, null));
    }

    @MenuInventory(slots = 18, name = "Sub Menu", filler = Material.BED, onClose = TestMenu.class)
    public static class TestSubMenu extends Menu {

        public TestSubMenu(MenuManager manager, Inventory inv) {
            super(manager, inv, "Test Sub Menu");
        }

        @MenuItem(slot = 6, name = "TestSubItem1", lore = "TestSubLore1", material = Material.NETHER_STAR)
        public void testSubItem1(ChesterPlayer player) {
            player.getBukkit().sendMessage("This is a test message for the sub item");
        }
    }
}
