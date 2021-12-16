package com.philfedor.gameengine.collision;

import com.philfedor.gameengine.geometry.shapes.CollisionShape;
import com.philfedor.gameengine.world.Entity;

public interface CollisionShapeFactory {
    CollisionShape getCollisionShape(Entity entity);
}
