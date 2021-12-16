package com.philfedor.gameengine.collision;

import com.philfedor.gameengine.world.Entity;

public interface CollisionListener {
    void respond(Entity entityA, Entity entityB, CollisionInfo collisionInfo);
}
