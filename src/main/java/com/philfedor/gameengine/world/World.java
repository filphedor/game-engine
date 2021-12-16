package com.philfedor.gameengine.world;

import java.util.ArrayList;

public class World {
    private ArrayList<Entity> entities;
    private ArrayList<WorldListener> worldListeners;

    public World() {
        this.entities = new ArrayList<>();
        this.worldListeners = new ArrayList<>();
    }

    public ArrayList<Entity> getEntities() {
        return this.entities;
    }

    public void addEntity(Entity entity) {
        this.entities.add(entity);
    }

    public void addListener(WorldListener worldListener) {
        this.worldListeners.add(worldListener);
    }

    public void update() {
        for (Entity entity : this.entities) {
                entity.update();
        }

        for (WorldListener worldListener : worldListeners) {
            worldListener.update(this.entities);
        }
    }
}
