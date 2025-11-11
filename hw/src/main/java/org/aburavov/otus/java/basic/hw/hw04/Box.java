package org.aburavov.otus.java.basic.hw.hw04;

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

    public Box(int width, int length, int height, String color) {
        this.width = width;
        this.length = length;
        this.height = height;
        this.color = color;
        this.content = null;
        this.isOpened = false;
    }

    public void printInfo() {
        System.out.println("w: " + width + ", l: " + length + ", h: " + height);
        System.out.println("color: " + color + ", is opened: " + isOpened + ", inside: " + content);
    }

    public void open() {
        if (this.isOpened) {
            System.out.println("Box is already opened");
            return;
        }
        this.isOpened = true;
    }

    public void close() {
        if (!this.isOpened) {
            System.out.println("Box is already closed");
            return;
        }
        this.isOpened = false;
    }

    public void putContent(String content) {
        if (!this.isOpened) {
            throw new IllegalStateException("Box is closed");
        }
        if (this.content != null) {
            System.out.println("Box already has content inside");
            return;
        }
        this.content = content;
    }

    public void throwOutContent() {
        if (!this.isOpened) {
            throw new IllegalStateException("Box is closed");
        }
        if (this.content == null) {
            System.out.println("Box doesn't have content inside");
            return;
        }
        this.content = null;
    }
}
