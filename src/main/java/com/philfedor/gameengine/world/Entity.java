package com.philfedor.gameengine.world;

import com.philfedor.gameengine.geometry.Vector2d;

public class Entity implements Cloneable {
    protected String id;
    protected Vector2d location;
    protected double rotation;

    public Entity(String id, Vector2d location) {
        this.id = id;
        this.location = location;
    }

    public String getId() {
        return this.id;
    }

    public Vector2d getLocation() {
        return this.location;
    }

    public double getRotation() {
        return this.rotation;
    }

    public void update() {

    };

    public Object clone() throws CloneNotSupportedException {
        Entity entity = (Entity) super.clone();

        entity.id = this.id;
        entity.location = (Vector2d) this.location.clone();

        return entity;
    }
}
