package com.github.plastix.chester.gui;

import com.github.plastix.chester.gui.annotation.IgnoreSlots;
import com.github.plastix.chester.gui.annotation.MenuInventory;
import com.github.plastix.chester.gui.annotation.MenuItem;
import com.github.plastix.chester.gui.annotation.NestedMenu;
import com.github.plastix.chester.utils.ItemUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MenuRegistry {

    private static Map<Class<? extends Menu>, List<Method>> loadedMenus = Maps.newHashMap();

    public static void addMenu(Class<? extends Menu> clazz) {
        List<Method> methods = Lists.newArrayList();
        for (Method m : clazz.getDeclaredMethods()) {
            if (m.isAnnotationPresent(MenuItem.class)) {
                methods.add(m);
            }
        }

        // If the class to add has a nested annotation
        if (clazz.isAnnotationPresent(NestedMenu.class)) {
            Annotation annotation = clazz.getAnnotation(NestedMenu.class);
            NestedMenu nestedAnnotation = (NestedMenu) annotation;
            // iterate the array of classes and register them as well
            for (Class clazz0 : nestedAnnotation.value()) {
                addMenu(clazz0);
            }
        }
        loadedMenus.put(clazz, methods);
    }

    public static Map<Class<? extends Menu>, List<Method>> getLoadedMenus() {
        return loadedMenus;
    }

    public static Inventory generateFreshMenu(Class clazz) {
        MenuInventory menuInv = (MenuInventory) clazz.getAnnotation(MenuInventory.class);
        Inventory inv = Bukkit.createInventory(null, menuInv.slots(), menuInv.name());
        for (int i = 0; i < inv.getSize(); i++)
            inv.setItem(i, ItemUtils.getNamedItemStack(menuInv.filler(), 1, ChatColor.RESET + "You cannot click here!"));
        for (Method m : loadedMenus.get(clazz)) {
            MenuItem menuItem = m.getAnnotation(MenuItem.class);
            inv.setItem(menuItem.slot(), ItemUtils.getNamedItemStack(menuItem.material(), menuItem.amount(), menuItem.name(), Arrays.asList(menuItem.lore())));
        }
        if (clazz.isAnnotationPresent(IgnoreSlots.class)){
            IgnoreSlots ignoreSlots = (IgnoreSlots) clazz.getAnnotation(IgnoreSlots.class);
            if (ignoreSlots.slots().length == ignoreSlots.materials().length) {
                for (int i = 0; i < ignoreSlots.slots().length; i++) {
                    inv.setItem(ignoreSlots.slots()[i], new ItemStack(ignoreSlots.materials()[i]));
                }
            }
        }
        return inv;
    }

}
