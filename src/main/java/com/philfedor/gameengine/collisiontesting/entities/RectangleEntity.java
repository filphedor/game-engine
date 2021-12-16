package com.philfedor.gameengine.collisiontesting.entities;

import com.philfedor.gameengine.geometry.Vector2d;
import com.philfedor.gameengine.world.Entity;

public class RectangleEntity extends Entity {
    private double width;
    private double height;

    public RectangleEntity(String id, Vector2d location, double width, double height) {
        super(id, location);

        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public Object clone()  throws CloneNotSupportedException {
        RectangleEntity rectangle = (RectangleEntity) super.clone();

        rectangle.width = this.width;
        rectangle.height = this.height;

        return rectangle;
    }
}
