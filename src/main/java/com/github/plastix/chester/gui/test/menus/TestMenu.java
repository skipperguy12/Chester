package com.github.plastix.chester.gui.test.menus;

import com.github.plastix.chester.ChesterPlayer;
import com.github.plastix.chester.gui.Menu;
import com.github.plastix.chester.gui.MenuManager;
import com.github.plastix.chester.gui.annotation.*;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

@MenuInventory(slots = 27, name = "Big Menu")
@NestedMenu({TestMenu.TestSubMenu.class})
public class TestMenu extends Menu {

    public TestMenu(MenuManager manager, Inventory inv) {
        super(manager, inv);
    }

    @MenuItem(slot = 5, item = @ItemStackAnnotation(material = Material.BEDROCK, name = "Hi."))
    public void testItem1(ChesterPlayer player) {
        player.getBukkit().sendMessage("This is a test message");
        player.setActiveMenu(new TestSubMenu(manager, null));
    }

    @MenuInventory(slots = 18, name = "Sub Menu", filler = @ItemStackAnnotation(material = Material.BED), onClose = TestMenu.class)
    @IgnoreSlots(slots = {1, 2, 3}, items = {
            @ItemStackAnnotation(material = Material.BED, name = "Test"),
            @ItemStackAnnotation(material = Material.APPLE, name = "#LeaveItToApple", lore = {"I", "LIKE", "PANTS"}),
            @ItemStackAnnotation(material = Material.GOLDEN_CARROT, name = "OllyCode", amount = 11)})
    public static class TestSubMenu extends Menu {

        public TestSubMenu(MenuManager manager, Inventory inv) {
            super(manager, inv);
        }

        @MenuItem(slot = 6, item = @ItemStackAnnotation(material = Material.DIAMOND_AXE, name = "Dax"))
        public void testSubItem1(ChesterPlayer player) {
            player.getBukkit().sendMessage("This is a test message for the sub item");
        }
    }
}
