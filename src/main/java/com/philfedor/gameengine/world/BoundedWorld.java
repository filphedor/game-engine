package com.philfedor.gameengine.world;

public class BoundedWorld extends World {
    private int width;
    private int height;

    public BoundedWorld(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }
}
