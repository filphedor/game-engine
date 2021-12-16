package com.philfedor.gameengine.geometry.shapes;

import com.philfedor.gameengine.geometry.Vector2d;

public interface CollisionShape {
    Vector2d farthestPointInDirection(Vector2d vector2d);
}
