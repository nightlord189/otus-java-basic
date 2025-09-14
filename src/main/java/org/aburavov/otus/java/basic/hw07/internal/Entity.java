package org.aburavov.otus.java.basic.hw07.internal;

public abstract class Entity {
    public String getSimpleName () {
        return this.getClass().getSimpleName();
    }
}
