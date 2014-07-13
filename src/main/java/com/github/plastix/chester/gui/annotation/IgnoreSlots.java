package com.github.plastix.chester.gui.annotation;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface IgnoreSlots {

    int[] slots();

    ItemStackAnnotation[] items();
}
