package com.philfedor.gameengine.collisiontesting.entities;

import com.philfedor.gameengine.collision.CollisionShapeFactory;
import com.philfedor.gameengine.collision.gjk.GjkSimplex;
import com.philfedor.gameengine.collision.minkowski.MinkowskiSolver;
import com.philfedor.gameengine.geometry.Vector2d;
import com.philfedor.gameengine.geometry.shapes.CollisionShape;
import com.philfedor.gameengine.world.Entity;

import java.util.ArrayList;
import java.util.HashMap;

public class GjkTester extends Entity {
    private Entity entityA;
    private Entity entityB;
    private HashMap<Class, CollisionShapeFactory> collisionShapeFactoryMap;
    private GjkSimplex gjkSimplex;
    private Vector2d d;
    private double sensitivity;
    private boolean isRunning;

    public GjkTester(String id, Vector2d location, Entity entityA, Entity entityB, HashMap<Class, CollisionShapeFactory> collisionShapeFactoryMap, double sensitivity) {
        super(id, location);

        this.entityA = entityA;
        this.entityB = entityB;
        this.collisionShapeFactoryMap = collisionShapeFactoryMap;
        this.sensitivity = sensitivity;
        this.isRunning = true;

        MinkowskiSolver minkowskiSolver = new MinkowskiSolver();
        Vector2d initialVector = entityA.getLocation().vectorSubtraction(entityB.getLocation());
        CollisionShape collisionShapeA = collisionShapeFactoryMap.get(entityA.getClass()).getCollisionShape(entityA);
        CollisionShape collisionShapeB = collisionShapeFactoryMap.get(entityB.getClass()).getCollisionShape(entityB);

        Vector2d pointA = minkowskiSolver.getDifference(initialVector, collisionShapeA, collisionShapeB);
        Vector2d pointB = minkowskiSolver.getDifference(initialVector.scalarMultiply(-1), collisionShapeA, collisionShapeB);

        Vector2d origin = new Vector2d(0, 0);

        Vector2d v1 = pointB.vectorSubtraction(pointA).perp();
        Vector2d v2 = v1.scalarMultiply(-1);
        Vector2d bO = origin.vectorSubtraction(pointB);

        if (v1.dotProduct(bO) > v2.dotProduct(bO)) {
            this.d = v1;
        } else {
            this.d = v2;
        }

        Vector2d newPoint = minkowskiSolver.getDifference(this.d, collisionShapeA, collisionShapeB);

        this.gjkSimplex = new GjkSimplex(this.sensitivity);
        this.gjkSimplex.addPoint(pointA);
        this.gjkSimplex.addPoint(pointB);
        this.gjkSimplex.addPoint(newPoint);
    }

    @Override
    public void update() {
        super.update();

        if (isRunning) {
            MinkowskiSolver minkowskiSolver = new MinkowskiSolver();
            CollisionShape collisionShapeA = collisionShapeFactoryMap.get(entityA.getClass()).getCollisionShape(entityA);
            CollisionShape collisionShapeB = collisionShapeFactoryMap.get(entityB.getClass()).getCollisionShape(entityB);

            if (!gjkSimplex.isProgressMade()) {
                this.isRunning = false;
                System.out.println("Not colliding");
            }

            if (gjkSimplex.containsOrigin()) {
                this.isRunning = false;
                System.out.println("Colliding");
            }

            d = gjkSimplex.update();

            Vector2d newPoint = minkowskiSolver.getDifference(d, collisionShapeA, collisionShapeB);

            if (newPoint.dotProduct(d) < 0) {
                this.isRunning = false;
                System.out.println("Not colliding");
            }

            gjkSimplex.addPoint(newPoint);
        }
    }

    public ArrayList<Vector2d> getPoints() {
        return this.gjkSimplex.getPoints();
    }
}
