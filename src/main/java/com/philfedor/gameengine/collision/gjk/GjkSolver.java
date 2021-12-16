package com.philfedor.gameengine.collision.gjk;

import com.philfedor.gameengine.collision.minkowski.MinkowskiSolver;
import com.philfedor.gameengine.geometry.shapes.CollisionShape;
import com.philfedor.gameengine.geometry.Vector2d;

public class GjkSolver {
    private  double sensitivity;

    public GjkSolver(double sensitivity) {
        this.sensitivity = sensitivity;
    }

    public GjkSimplex doCollide(Vector2d locationA, CollisionShape collisionShapeA, Vector2d locationB, CollisionShape collisionShapeB) {
        MinkowskiSolver minkowskiSolver = new MinkowskiSolver();
        Vector2d initialVector = locationA.vectorSubtraction(locationB);

        Vector2d pointA = minkowskiSolver.getDifference(initialVector, collisionShapeA, collisionShapeB);
        Vector2d pointB = minkowskiSolver.getDifference(initialVector.scalarMultiply(-1), collisionShapeA, collisionShapeB);

        Vector2d origin = new Vector2d(0, 0);

        Vector2d v1 = pointB.vectorSubtraction(pointA).perp();
        Vector2d v2 = v1.scalarMultiply(-1);
        Vector2d bO = origin.vectorSubtraction(pointB);

        Vector2d d;

        if (v1.dotProduct(bO) > v2.dotProduct(bO)) {
            d = v1;
        } else {
            d = v2;
        }

        GjkSimplex gjkSimplex = new GjkSimplex(this.sensitivity);
        gjkSimplex.addPoint(pointA);
        gjkSimplex.addPoint(pointB);

        Vector2d newPoint = minkowskiSolver.getDifference(d, collisionShapeA, collisionShapeB);
        gjkSimplex.addPoint(newPoint);

        while (true) {
            if (!gjkSimplex.isProgressMade()) {
                break;
            }

            if (gjkSimplex.containsOrigin()) {
                return gjkSimplex;
            }

            d = gjkSimplex.update();

            newPoint = minkowskiSolver.getDifference(d, collisionShapeA, collisionShapeB);

            if (newPoint.dotProduct(d) < 0) {
                break;
            }

            gjkSimplex.addPoint(newPoint);
        }

        return null;
    }
}
