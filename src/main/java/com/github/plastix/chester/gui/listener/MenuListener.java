package com.github.plastix.chester.gui.listener;

import com.github.plastix.chester.Chester;
import com.github.plastix.chester.ChesterPlayer;
import com.github.plastix.chester.ChesterPlayerManager;
import com.github.plastix.chester.gui.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MenuListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e){
        ChesterPlayer player = ChesterPlayerManager.getPlayer((Player) e.getWhoClicked());
        MenuManager manager = player.getMenuManager();
        if (manager.getCurrentMenu() != null && manager.getCurrentMenu().getInventory().getName().equals(e.getInventory().getName())){
            e.setCancelled(true);
            for (Method m : MenuRegistry.getLoadedMenus().get(manager.getCurrentMenu().getClass())){
                MenuItem menuItem = m.getAnnotation(MenuItem.class);
                if (e.getSlot() == menuItem.slot())
                    try {
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
    public void onClose(InventoryCloseEvent e){
        ChesterPlayer player = ChesterPlayerManager.getPlayer((Player) e.getPlayer());
        if (player.getMenuManager().getCurrentMenu() == null) return;
        if (!player.getMenuManager().getCurrentMenu().getInventory().getName().equals(e.getInventory().getName())) return;
        MenuInventory menuInventory = player.getMenuManager().getCurrentMenu().getClass().getAnnotation(MenuInventory.class);
        if (menuInventory == null || menuInventory.onClose() == null) return;
        if (menuInventory.onClose() != Menu.class)
                new MenuOpener(player, menuInventory.onClose());
    }

    public static class MenuOpener extends BukkitRunnable{

        private ChesterPlayer player;
        private Class menuClass;

        public MenuOpener(ChesterPlayer player, Class menuClass){
            this.player = player;
            this.menuClass = menuClass;
            player.getBukkit().sendMessage(menuClass.toString());
            runTaskLater(Chester.get(), 5);
        }

        @Override
        public void run() {
            player.setActiveMenu(menuClass);
        }
    }
}
