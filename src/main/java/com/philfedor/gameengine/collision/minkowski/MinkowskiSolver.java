package com.philfedor.gameengine.collision.minkowski;

import com.philfedor.gameengine.geometry.Vector2d;
import com.philfedor.gameengine.geometry.shapes.CollisionShape;

public class MinkowskiSolver {
    public Vector2d getDifference(Vector2d vector, CollisionShape collisionShapeA, CollisionShape collisionShapeB) {
        return collisionShapeA.farthestPointInDirection(vector).vectorSubtraction(collisionShapeB.farthestPointInDirection(vector.scalarMultiply(-1)));
    }
}
