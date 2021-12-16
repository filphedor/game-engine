package com.philfedor.gameengine.collisiontesting.entities;

import com.philfedor.gameengine.geometry.Vector2d;
import com.philfedor.gameengine.world.Entity;

public class CircleEntity extends Entity {
    private double radius;

    public CircleEntity(String id, Vector2d location, double radius) {
        super(id, location);

        this.radius = radius;
    }

    public double getRadius() {
        return this.radius;
    }

    public Object clone()  throws CloneNotSupportedException {
        CircleEntity circle = (CircleEntity) super.clone();

        circle.radius = this.radius;

        return circle;
    }
}
