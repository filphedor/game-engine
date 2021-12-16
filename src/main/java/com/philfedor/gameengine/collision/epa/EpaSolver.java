package com.philfedor.gameengine.collision.epa;

import com.philfedor.gameengine.collision.CollisionInfo;
import com.philfedor.gameengine.collision.minkowski.MinkowskiSolver;
import com.philfedor.gameengine.geometry.Vector2d;
import com.philfedor.gameengine.geometry.shapes.CollisionShape;

import java.util.ArrayList;

public class EpaSolver {
    private double sensitivity;

    public EpaSolver(double sensitivity) {
        this.sensitivity = sensitivity;
    }

    public CollisionInfo getCollisionInfo(CollisionShape collisionShapeA, CollisionShape collisionShapeB, ArrayList<Vector2d> initialSimplex) {
        MinkowskiSolver minkowskiSolver = new MinkowskiSolver();
        EpaSimplex epaSimplex = new EpaSimplex(initialSimplex);

        while (true) {
            Vector2d normal = epaSimplex.getNormal();
            double distance = epaSimplex.getClosestDistance();

            Vector2d nextPoint = minkowskiSolver.getDifference(normal, collisionShapeA, collisionShapeB);

            if ((normal.getIdentity().dotProduct(nextPoint) - distance) > this.sensitivity) {
                epaSimplex.addPoint(nextPoint);
            } else {
                return new CollisionInfo(normal, distance);
            }
        }
    }
}
