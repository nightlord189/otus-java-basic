package org.aburavov.otus.java.basic.hw04;

import java.security.InvalidParameterException;

public class Box {
    private final int width;
    private final int length;
    private final int height;
    private String color;
    private String content; // a thing inside the box
    private boolean isOpened;

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }

    public int getHeight() {
        return height;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isOpened() {
        return isOpened;
    }

    public Box (int width, int length, int height, String color, Boolean isOpened, String content) {
        this.width = width;
        this.length = length;
        this.height = height;
        this.color = color;
        this.content = content;
        this.isOpened = isOpened;
    }

    public void printInfo () {
        System.out.println("w: "+width+", l: "+length+", h: "+height);
        System.out.println("color: "+color+ ", is opened: "+isOpened+", inside: "+content);
    }

    public void open () {
        if (this.isOpened) {
            throw new IllegalStateException("Box is already opened");
        }
        this.isOpened = true;
    }

    public void close () {
        if (!this.isOpened) {
            throw new IllegalStateException("Box is already closed");
        }
        this.isOpened = false;
    }

    public void putContent (String content) {
        if (!this.isOpened) {
            throw new IllegalStateException("Box is closed");
        }
        if (this.content != null) {
            throw new IllegalStateException("Box already has content inside");
        }
        this.content = content;
    }

    public void throwOutContent () {
        if (!this.isOpened) {
            throw new IllegalStateException("Box is closed");
        }
        if (this.content == null) {
            throw new IllegalStateException("Box doesn't have content inside");
        }
        this.content = null;
    }
}
