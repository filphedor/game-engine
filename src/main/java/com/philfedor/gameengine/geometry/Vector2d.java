package com.philfedor.gameengine.geometry;

public class Vector2d implements Cloneable {
    private double x;
    private double y;

    public Vector2d(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getMagnitude() {
        return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
    }

    public Vector2d getIdentity() {
        double magnitude = this.getMagnitude();

        return new Vector2d(this.x / magnitude, this.y / magnitude);
    }

    public Vector2d vectorAddition(Vector2d vector2d) {
        return new Vector2d(this.x + vector2d.x, this.y + vector2d.y);
    }

    public Vector2d vectorSubtraction(Vector2d vector2d) {
        return new Vector2d(this.x - vector2d.x, this.y - vector2d.y);
    }

    public double dotProduct(Vector2d vector2d) {
        return this.x * vector2d.x + this.y * vector2d.y;
    }

    public Vector2d perp() {
        return new Vector2d(this.y, this.x * -1);
    }

    public Vector2d scalarMultiply(double mult) {
        return new Vector2d(this.x * mult, this.y * mult);
    }

    public Object clone() throws CloneNotSupportedException {
        Vector2d vector2d = (Vector2d) super.clone();

        vector2d.x = this.x;
        vector2d.y = this.y;

        return vector2d;
    }
}
