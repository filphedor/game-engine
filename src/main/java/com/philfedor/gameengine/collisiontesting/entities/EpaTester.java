package com.philfedor.gameengine.collisiontesting.entities;

import com.philfedor.gameengine.collision.CollisionInfo;
import com.philfedor.gameengine.collision.CollisionShapeFactory;
import com.philfedor.gameengine.collision.epa.EpaSimplex;
import com.philfedor.gameengine.collision.gjk.GjkSimplex;
import com.philfedor.gameengine.collision.gjk.GjkSolver;
import com.philfedor.gameengine.collision.minkowski.MinkowskiSolver;
import com.philfedor.gameengine.geometry.Vector2d;
import com.philfedor.gameengine.geometry.shapes.CollisionShape;
import com.philfedor.gameengine.world.Entity;

import java.util.ArrayList;
import java.util.HashMap;

public class EpaTester extends Entity {
    private Entity entityA;
    private Entity entityB;
    private HashMap<Class, CollisionShapeFactory> collisionShapeFactoryMap;
    private EpaSimplex epaSimplex;
    private Vector2d d;
    private double sensitivity;
    private boolean isRunning;

    public EpaTester(String id, Vector2d location, Entity entityA, Entity entityB, HashMap<Class, CollisionShapeFactory> collisionShapeFactoryMap, double sensitivity) {
        super(id, location);

        this.entityA = entityA;
        this.entityB = entityB;
        this.collisionShapeFactoryMap = collisionShapeFactoryMap;
        this.sensitivity = sensitivity;
        this.isRunning = true;

        CollisionShape collisionShapeA = collisionShapeFactoryMap.get(entityA.getClass()).getCollisionShape(entityA);
        CollisionShape collisionShapeB = collisionShapeFactoryMap.get(entityB.getClass()).getCollisionShape(entityB);
        GjkSolver gjkSolver = new GjkSolver(sensitivity);
        GjkSimplex gjkSimplex = gjkSolver.doCollide(entityA.getLocation(), collisionShapeA, entityB.getLocation(), collisionShapeB);

        if (gjkSimplex != null) {
            this.epaSimplex = new EpaSimplex(gjkSimplex.getPoints());
        }
    }

    @Override
    public void update() {
        super.update();

        if (isRunning) {
            MinkowskiSolver minkowskiSolver = new MinkowskiSolver();

            CollisionShape collisionShapeA = collisionShapeFactoryMap.get(entityA.getClass()).getCollisionShape(entityA);
            CollisionShape collisionShapeB = collisionShapeFactoryMap.get(entityB.getClass()).getCollisionShape(entityB);

            Vector2d normal = this.epaSimplex.getNormal();
            double distance = this.epaSimplex.getClosestDistance();

            Vector2d nextPoint = minkowskiSolver.getDifference(normal, collisionShapeA, collisionShapeB);

            if ((normal.getIdentity().dotProduct(nextPoint) - distance) > this.sensitivity) {
                this.epaSimplex.addPoint(nextPoint);
            } else {
                System.out.println("Collision depth: " + distance);
                System.out.println("Collision normal: " + normal.getX() + " " + normal.getY());
                this.isRunning = false;
            }
        }
    }

    public ArrayList<Vector2d> getPoints() {
        return this.epaSimplex.getPoints();
    }
}
