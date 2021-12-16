package com.philfedor.gameengine.world;

import java.util.ArrayList;

public abstract class WorldListener {
    private int priority;

    public WorldListener(int priority) {
        this.priority = priority;
    }

    public abstract void update(ArrayList<Entity> entities);
}
