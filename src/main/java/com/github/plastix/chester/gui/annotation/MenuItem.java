package com.github.plastix.chester.gui.annotation;

import org.bukkit.Material;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface MenuItem {

    int slot();

    Material material();

    String name();

    String[] lore() default {};

    short durability() default 0;

}
