package com.philfedor.gameengine.collisiontesting.collision;

import com.philfedor.gameengine.collision.CollisionShapeFactory;
import com.philfedor.gameengine.collisiontesting.entities.CircleEntity;
import com.philfedor.gameengine.geometry.shapes.Circle;
import com.philfedor.gameengine.geometry.shapes.CollisionShape;
import com.philfedor.gameengine.world.Entity;

public class CircleEntityCollisionShapeFactory implements CollisionShapeFactory {
    @Override
    public CollisionShape getCollisionShape(Entity entity) {
        CircleEntity circleEntity = (CircleEntity) entity;

        return new Circle(circleEntity.getLocation(), circleEntity.getRadius());
    }
}
