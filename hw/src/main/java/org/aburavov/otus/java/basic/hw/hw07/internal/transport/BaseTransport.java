package org.aburavov.otus.java.basic.hw.hw07.internal.transport;

import org.aburavov.otus.java.basic.hw.hw07.internal.Entity;
import org.aburavov.otus.java.basic.hw.hw07.internal.Human;
import org.aburavov.otus.java.basic.hw.hw07.internal.ITransport;

public abstract class BaseTransport extends Entity implements ITransport {
    protected Human driver;

    @Override
    public void sit(Human driver) {
        this.driver = driver;
    }

    public void getOff() {
        this.driver = null;
    }
}
