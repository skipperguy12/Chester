package com.github.plastix.chester.gui.listener;

import com.github.plastix.chester.Chester;
import com.github.plastix.chester.ChesterPlayer;
import com.github.plastix.chester.ChesterPlayerManager;
import com.github.plastix.chester.gui.Menu;
import com.github.plastix.chester.gui.MenuManager;
import com.github.plastix.chester.gui.MenuRegistry;
import com.github.plastix.chester.gui.annotation.IgnoreSlots;
import com.github.plastix.chester.gui.annotation.MenuInventory;
import com.github.plastix.chester.gui.annotation.MenuItem;
import com.google.common.collect.Lists;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MenuListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        ChesterPlayer player = ChesterPlayerManager.getPlayer((Player) e.getWhoClicked());
        MenuManager manager = player.getMenuManager();
        boolean cancel = true;
        if (manager.getCurrentMenu() != null && manager.getCurrentMenu().getInventory().getName().equals(e.getInventory().getName())) {
            MenuInventory menuInventory = player.getMenuManager().getCurrentMenu().getClass().getAnnotation(MenuInventory.class);
            if (e.getRawSlot() < e.getInventory().getSize()){
                if (player.getMenuManager().getCurrentMenu().getClass().isAnnotationPresent(IgnoreSlots.class)){
                    IgnoreSlots ignoreSlots = player.getMenuManager().getCurrentMenu().getClass().getAnnotation(IgnoreSlots.class);
                    for (int i : ignoreSlots.slots())
                        if (i == e.getRawSlot())
                            cancel = false;
                }
            }else
                cancel = false;
            if(e.getClick() == ClickType.SHIFT_LEFT || e.getClick() == ClickType.SHIFT_RIGHT || e.getClick() == ClickType.DOUBLE_CLICK)
                cancel = true;
            e.setCancelled(cancel);
            for (Method m : MenuRegistry.getLoadedMenus().get(manager.getCurrentMenu().getClass())) {
                MenuItem menuItem = m.getAnnotation(MenuItem.class);
                if (e.getRawSlot() == menuItem.slot()) try {
                    m.invoke(manager.getCurrentMenu(), player);
                    return;
                } catch (IllegalAccessException e1) {
                    e1.printStackTrace();
                } catch (InvocationTargetException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        ChesterPlayer player = ChesterPlayerManager.getPlayer((Player) e.getPlayer());
        if (player.getMenuManager().getCurrentMenu() == null) return;
        if (!player.getMenuManager().getCurrentMenu().getInventory().getName().equals(e.getInventory().getName()))
            return;
        MenuInventory menuInventory = player.getMenuManager().getCurrentMenu().getClass().getAnnotation(MenuInventory.class);
        if (menuInventory == null || menuInventory.onClose() == null) return;
        if (menuInventory.onClose() != Menu.class) new MenuOpener(player, menuInventory.onClose());
    }

    public static class MenuOpener extends BukkitRunnable {

        private ChesterPlayer player;
        private Class menuClass;

        public MenuOpener(ChesterPlayer player, Class menuClass) {
            this.player = player;
            this.menuClass = menuClass;
            runTaskLater(Chester.get(), 1);
        }

        @Override
        public void run() {
            player.setActiveMenu(menuClass);
        }
    }
}
