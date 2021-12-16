package com.philfedor.gameengine.pong.entities;

import com.philfedor.gameengine.geometry.Vector2d;
import com.philfedor.gameengine.geometry.shapes.CollisionShape;
import com.philfedor.gameengine.collision.CollisionShapeFactory;
import com.philfedor.gameengine.collision.gjk.GjkSimplex;
import com.philfedor.gameengine.world.Entity;

import java.util.ArrayList;
import java.util.HashMap;

public class CollisionEntity extends Entity {
    private CollisionShape collisionShapeA;
    private CollisionShape collisionShapeB;
    private HashMap<Class, CollisionShapeFactory> collisionMap;
    private double sensitivity;

    private GjkSimplex gjkSimplex;
    private Vector2d d;

    private boolean done;

    public CollisionEntity(
            String id,
            Vector2d location,
            Entity entityA,
            Entity entityB,
            HashMap<Class, CollisionShapeFactory> collisionMap,
            double sensitivity
    ) {
        super(id, location);

        this.collisionMap = collisionMap;
        this.sensitivity = sensitivity;
        this.done = false;

        this.collisionShapeA = this.collisionMap.get(entityA.getClass()).getCollisionShape(entityA);
        this.collisionShapeB = this.collisionMap.get(entityB.getClass()).getCollisionShape(entityB);

        Vector2d initialVector = entityA.getLocation().vectorSubtraction(entityB.getLocation());

        Vector2d pointA = this.mikwoskiDifference(initialVector, this.collisionShapeA, this.collisionShapeB);
        Vector2d pointB = this.mikwoskiDifference(initialVector.scalarMultiply(-1), this.collisionShapeA, this.collisionShapeB);

        Vector2d origin = new Vector2d(0, 0);

        Vector2d v1 = pointB.vectorSubtraction(pointA).perp();
        Vector2d v2 = v1.scalarMultiply(-1);
        Vector2d bO = origin.vectorSubtraction(pointB);

        if (v1.dotProduct(bO) > v2.dotProduct(bO)) {
            this.d = v1;
        } else {
            this.d = v2;
        }

        this.gjkSimplex = new GjkSimplex(this.sensitivity);
        gjkSimplex.addPoint(pointA);
        gjkSimplex.addPoint(pointB);

        Vector2d newPoint = this.mikwoskiDifference(this.d, collisionShapeA, collisionShapeB);
        gjkSimplex.addPoint(newPoint);
    }

    public GjkSimplex getSimplex() {
        return this.gjkSimplex;
    }

    @Override
    public void update() {
        super.update();

        if (!this.done) {
            System.out.println("simplex");
            ArrayList<Vector2d> points = this.gjkSimplex.getPoints();
            for (Vector2d point : points) {
                System.out.println("x: " + point.getX() + " y: " + point.getY());
            }

            if (gjkSimplex.containsOrigin()) {
                this.done = true;
                System.out.println("Yes");
            } else {
                this.d = gjkSimplex.update();

                Vector2d newPoint = this.mikwoskiDifference(this.d, collisionShapeA, collisionShapeB);

                if (newPoint.dotProduct(this.d) < 0) {
                    this.done = true;
                    System.out.println("No");
                }

                gjkSimplex.addPoint(newPoint);

                if (!gjkSimplex.isProgressMade()) {
                    this.done = true;
                    System.out.println("no progress");
                }
        }
    }
    }

    private Vector2d mikwoskiDifference(Vector2d vector, CollisionShape collisionShapeA, CollisionShape collisionShapeB) {
        return collisionShapeA.farthestPointInDirection(vector).vectorSubtraction(collisionShapeB.farthestPointInDirection(vector.scalarMultiply(-1)));
    }
}
