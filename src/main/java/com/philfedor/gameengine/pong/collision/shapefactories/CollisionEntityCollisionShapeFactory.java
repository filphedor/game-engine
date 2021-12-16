package com.philfedor.gameengine.pong.collision.shapefactories;

import com.philfedor.gameengine.geometry.shapes.CollisionShape;
import com.philfedor.gameengine.geometry.shapes.Polygon;
import com.philfedor.gameengine.collision.CollisionShapeFactory;
import com.philfedor.gameengine.pong.entities.CollisionEntity;
import com.philfedor.gameengine.world.Entity;

public class CollisionEntityCollisionShapeFactory implements CollisionShapeFactory {
    @Override
    public CollisionShape getCollisionShape(Entity entity) {
        CollisionEntity collisionEntity = (CollisionEntity) entity;

        return new Polygon(collisionEntity.getSimplex().getPoints());
    }
}
