package com.philfedor.gameengine.pong.entities;

import com.philfedor.gameengine.geometry.Vector2d;
import com.philfedor.gameengine.world.Entity;

public class Ball extends Entity {
    private double radius;
    private Vector2d velocity;

    public Ball(String id, Vector2d location, double radius) {
        super(id, location);

        this.radius = radius;
        this.velocity = new Vector2d(15, 3);
    }

    public double getRadius() {
        return this.radius;
    }

    public Vector2d getVelocity() {
        return this.velocity;
    }

    public void setVelocity(Vector2d velocity) {
        this.velocity = velocity;
    }

    public void update() {
        this.location = this.getLocation().vectorAddition(this.velocity);
    }

    public Object clone()  throws CloneNotSupportedException {
        Ball ball = (Ball) super.clone();

        ball.radius = this.radius;

        return ball;
    }
}
