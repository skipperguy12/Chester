package com.github.plastix.chester.gui.listener;

import com.github.plastix.chester.ChesterPlayer;
import com.github.plastix.chester.ChesterPlayerManager;
import com.github.plastix.chester.gui.MenuItem;
import com.github.plastix.chester.gui.MenuManager;
import com.github.plastix.chester.gui.MenuRegistry;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

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

}
