package com.philfedor.gameengine.graphics;

import com.philfedor.gameengine.world.Entity;

public abstract class EntityRenderer {
    protected Entity entity;

    public void updateEntity(Entity entity) {
        this.entity = entity;
    }

    public abstract void render();
}
