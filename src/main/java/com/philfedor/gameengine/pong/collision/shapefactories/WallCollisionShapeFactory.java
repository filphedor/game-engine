package com.philfedor.gameengine.pong.collision.shapefactories;

import com.philfedor.gameengine.geometry.shapes.CollisionShape;
import com.philfedor.gameengine.geometry.shapes.Rectangle;
import com.philfedor.gameengine.collision.CollisionShapeFactory;
import com.philfedor.gameengine.pong.entities.Wall;
import com.philfedor.gameengine.world.Entity;

public class WallCollisionShapeFactory implements CollisionShapeFactory {
    @Override
    public CollisionShape getCollisionShape(Entity entity) {
        Wall wall = (Wall) entity;

        return new Rectangle(wall.getLocation(), wall.getWidth(), wall.getHeight());
    }
}
