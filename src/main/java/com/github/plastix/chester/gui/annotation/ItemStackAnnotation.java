package com.github.plastix.chester.gui.annotation;

import org.bukkit.Material;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ItemStackAnnotation {

    Material material();

    int amount() default 1;

    String name() default "";

    String[] lore() default {};

    short durability() default 0;

}
