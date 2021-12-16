package com.philfedor.gameengine.collisiontesting.entities;

import com.philfedor.gameengine.collision.CollisionShapeFactory;
import com.philfedor.gameengine.collision.minkowski.MinkowskiSolver;
import com.philfedor.gameengine.geometry.Vector2d;
import com.philfedor.gameengine.geometry.shapes.CollisionShape;
import com.philfedor.gameengine.world.Entity;

import java.util.ArrayList;
import java.util.HashMap;

public class MinkowskiDifferenceTester extends Entity {
    private Entity entityA;
    private Entity entityB;
    private HashMap<Class, CollisionShapeFactory> collisionShapeFactoryMap;
    private ArrayList<Vector2d> points;
    private int granularity;

    public MinkowskiDifferenceTester(String id, Vector2d location, Entity entityA, Entity entityB, HashMap<Class, CollisionShapeFactory> collisionShapeFactoryMap, int granularity) {
        super(id, location);

        this.entityA = entityA;
        this.entityB = entityB;
        this.collisionShapeFactoryMap = collisionShapeFactoryMap;
        this.granularity = granularity;
        this.update();
    }

    @Override
    public void update() {
        super.update();

        MinkowskiSolver minkowskiSolver = new MinkowskiSolver();
        CollisionShape collisionShapeA = this.collisionShapeFactoryMap.get(entityA.getClass()).getCollisionShape(entityA);
        CollisionShape collisionShapeB = this.collisionShapeFactoryMap.get(entityB.getClass()).getCollisionShape(entityB);

        ArrayList<Vector2d> newPoints = new ArrayList<>();

        double radians = (2 * Math.PI) / (double) granularity;

        for (double i = 0; i < 2 * Math.PI; i += radians) {
            Vector2d newPoint = minkowskiSolver.getDifference(new Vector2d(Math.cos(i), Math.sin(i)), collisionShapeA, collisionShapeB);

            if (!newPoints.contains(newPoint)) {
                newPoints.add(newPoint);
            }
        }

        this.points = newPoints;
    }

    public ArrayList<Vector2d> getPoints() {
        return this.points;
    }
}
