package com.philfedor.gameengine.pong.collision.listeners;

import com.philfedor.gameengine.collision.CollisionInfo;
import com.philfedor.gameengine.collision.CollisionListener;
import com.philfedor.gameengine.geometry.Vector2d;
import com.philfedor.gameengine.pong.entities.Ball;
import com.philfedor.gameengine.world.Entity;

public class BounceListener implements CollisionListener {

    @Override
    public void respond(Entity entityA, Entity entityB, CollisionInfo collisionInfo) {
        Vector2d normal = null;
        Ball ball = null;
        Entity other = null;

        if (entityA instanceof Ball) {
            ball = (Ball) entityA;
            other = entityB;
            normal = collisionInfo.getNormal();
        } else if (entityB instanceof Ball) {
            ball = (Ball) entityB;
            normal = collisionInfo.getNormal().scalarMultiply(-1);
            other = entityA;
        }

        //only bounce if they are moving towards one another
        if (ball != null) {
            Vector2d locDiff = other.getLocation().vectorSubtraction(ball.getLocation());

            if (locDiff.dotProduct(ball.getVelocity()) != 0) {
                double velocityInDir = normal.getIdentity().dotProduct(ball.getVelocity());
                Vector2d delta = normal.getIdentity().scalarMultiply(-2 * velocityInDir);
                ball.setVelocity(ball.getVelocity().vectorAddition(delta));
            }
        }
    }
}
