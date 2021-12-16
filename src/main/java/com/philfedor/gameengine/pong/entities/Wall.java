package com.philfedor.gameengine.pong.entities;

import com.philfedor.gameengine.geometry.Vector2d;
import com.philfedor.gameengine.world.Entity;

public class Wall extends Entity {
    private double width;
    private double height;

    public Wall(String id, Vector2d location, double width, double height) {
        super(id, location);

        this.width = width;
        this.height = height;
    }

    @Override
    public void update() {
        super.update();
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public Object clone()  throws CloneNotSupportedException {
        Wall wall = (Wall) super.clone();

        wall.width = this.width;
        wall.height = this.height;

        return wall;
    }
}
