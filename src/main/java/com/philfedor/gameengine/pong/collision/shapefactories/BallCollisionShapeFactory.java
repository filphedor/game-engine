package com.philfedor.gameengine.pong.collision.shapefactories;

import com.philfedor.gameengine.collision.CollisionShapeFactory;
import com.philfedor.gameengine.geometry.shapes.Circle;
import com.philfedor.gameengine.geometry.shapes.CollisionShape;
import com.philfedor.gameengine.pong.entities.Ball;
import com.philfedor.gameengine.world.Entity;

public class BallCollisionShapeFactory implements CollisionShapeFactory {
    @Override
    public CollisionShape getCollisionShape(Entity entity) {
        Ball ball = (Ball) entity;

        return new Circle(ball.getLocation(), ball.getRadius());
    }
}
