package com.github.plastix.chester.gui;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MenuRegistry {

    private static Map<Class<? extends Menu>, List<Method>> loadedMenus = Maps.newHashMap();

    public static void addMenu(Class<? extends Menu> clazz){
        List<Method> methods = Lists.newArrayList();
        for (Method m : clazz.getDeclaredMethods()) {
            if (m.isAnnotationPresent(MenuItem.class)){
                methods.add(m);
            }
        }
        loadedMenus.put(clazz, methods);
    }

    public static Map<Class<? extends Menu>, List<Method>> getLoadedMenus(){
        return loadedMenus;
    }

    public static Inventory generateFreshMenu(Class clazz, int size, String name, ItemStack filler){
        Inventory inv = Bukkit.createInventory(null, size, name);
        for (int i = 0; i < inv.getSize(); i++) inv.setItem(i, filler);
        for (Method m : loadedMenus.get(clazz)){
            MenuItem menuItem = m.getAnnotation(MenuItem.class);
            ItemStack item = new ItemStack(menuItem.material());
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(menuItem.name());
            meta.setLore(Arrays.asList(menuItem.lore()));
            item.setItemMeta(meta);
            inv.setItem(menuItem.slot(), item);
        }
        return inv;
    }

}
