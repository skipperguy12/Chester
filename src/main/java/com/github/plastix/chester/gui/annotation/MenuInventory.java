package com.github.plastix.chester.gui.annotation;

import com.github.plastix.chester.gui.Menu;
import org.bukkit.Material;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface MenuInventory {

    int slots();

    String name() default "Menu";

    Class<? extends Menu> onClose() default Menu.class;

    Material filler() default Material.ENDER_PORTAL;

}
