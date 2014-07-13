package com.github.plastix.chester.gui;

import com.google.common.collect.Lists;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;
import java.util.List;

public class MenuManager {

    private @Nullable Menu currentMenu = null;
    private @Nullable Menu previousMenu = null;

    private List<Menu> menus;

    public MenuManager(){
        menus = Lists.newArrayList();
    }

    public void setActiveMenu(Menu menu){
        previousMenu = currentMenu;
        if (hasMenu(menu.getClass())){
            currentMenu = getMenu(menu.getClass());
        }else{
            currentMenu = menu;
            currentMenu.setInventory(MenuRegistry.generateFreshMenu(menu.getClass(), 27, "Test", new ItemStack(Material.BEDROCK)));
        }
        //TODO: Update player
    }

    private Menu getMenu(Class clazz){
        for (Menu m : menus){
            if (m.getClass().equals(clazz))
                return m;
        }
        return null;
    }

    private boolean hasMenu(Class clazz){
        for (Menu m : menus){
            if (clazz.equals(m.getClass()))
                return true;
        }
        return false;
    }

    public Menu getCurrentMenu(){
        return this.currentMenu;
    }

    public Menu getPreviousMenu(){
        return this.previousMenu;
    }

}
