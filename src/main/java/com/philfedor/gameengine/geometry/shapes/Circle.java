package com.philfedor.gameengine.geometry.shapes;

import com.philfedor.gameengine.geometry.Vector2d;

public class Circle implements CollisionShape {
    private Vector2d location;
    private double radius;

    public Circle(Vector2d location, double radius) {
        this.location = location;
        this.radius = radius;
    }

    public Vector2d getLocation() {
        return this.location;
    }

    public double getRadius() {
        return this.radius;
    }

    @Override
    public Vector2d farthestPointInDirection(Vector2d vector2d) {
        Vector2d outsidePoint = vector2d.getIdentity().scalarMultiply(this.radius);

        return this.location.vectorAddition(outsidePoint);
    }
}
