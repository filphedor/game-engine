package com.philfedor.gameengine.collision;

import com.philfedor.gameengine.geometry.Vector2d;

public class CollisionInfo {
    private Vector2d normal;
    private double depth;

    public CollisionInfo(Vector2d normal, double depth) {
        this.normal = normal;
        this.depth = depth;
    }

    public Vector2d getNormal() {
        return this.normal;
    }

    public double getDepth() {
        return this.depth;
    }
}
