package com.philfedor.gameengine.collisiontesting.collision;

import com.philfedor.gameengine.collision.CollisionShapeFactory;
import com.philfedor.gameengine.collisiontesting.entities.RectangleEntity;
import com.philfedor.gameengine.geometry.shapes.CollisionShape;
import com.philfedor.gameengine.geometry.shapes.Rectangle;
import com.philfedor.gameengine.world.Entity;

public class RectangleEntityCollisionShapeFactory implements CollisionShapeFactory {
    @Override
    public CollisionShape getCollisionShape(Entity entity) {
        RectangleEntity rectangleEntity = (RectangleEntity) entity;

        return new Rectangle(rectangleEntity.getLocation(), rectangleEntity.getWidth(), rectangleEntity.getHeight());
    }
}
