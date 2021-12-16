package com.philfedor.gameengine.collision;

import com.philfedor.gameengine.collision.epa.EpaSolver;
import com.philfedor.gameengine.collision.gjk.GjkSimplex;
import com.philfedor.gameengine.collision.gjk.GjkSolver;
import com.philfedor.gameengine.geometry.shapes.CollisionShape;
import com.philfedor.gameengine.geometry.Vector2d;
import com.philfedor.gameengine.world.Entity;
import com.philfedor.gameengine.world.WorldListener;

import java.util.ArrayList;
import java.util.HashMap;

public class CollisionSolver extends WorldListener {
    private HashMap<Class, CollisionShapeFactory> collisionShapeFactoryMap;
    private ArrayList<CollisionListener> collisionListeners;
    private double sensitivity;

    public CollisionSolver(HashMap<Class, CollisionShapeFactory> collisionShapeFactoryMap, double sensitivity, int priority) {
        super(priority);

        this.collisionShapeFactoryMap = collisionShapeFactoryMap;
        this.collisionListeners = new ArrayList<>();
        this.sensitivity = sensitivity;
    }

    public void addCollisionListener(CollisionListener collisionListener) {
        this.collisionListeners.add(collisionListener);
    }

    @Override
    public void update(ArrayList<Entity> entities) {
        GjkSolver gjkSolver = new GjkSolver(this.sensitivity);
        EpaSolver epaSolver = new EpaSolver(this.sensitivity);

        for (int a = 0; a < entities.size(); a++) {
            for (int b = a + 1; b < entities.size(); b++) {
                Entity entityA = entities.get(a);
                Entity entityB = entities.get(b);

                Vector2d locationA = entityA.getLocation();
                Vector2d locationB = entityB.getLocation();

                CollisionShape collisionShapeA = this.collisionShapeFactoryMap.get(entityA.getClass()).getCollisionShape(entityA);
                CollisionShape collisionShapeB = this.collisionShapeFactoryMap.get(entityB.getClass()).getCollisionShape(entityB);

                GjkSimplex gjkSimplex = gjkSolver.doCollide(locationA, collisionShapeA, locationB, collisionShapeB);
                if (gjkSimplex != null) {
                    CollisionInfo collisionInfo = epaSolver.getCollisionInfo(collisionShapeA, collisionShapeB, gjkSimplex.getPoints());

                    if (collisionInfo.getDepth() != 0) {
                        for (CollisionListener collisionListener : this.collisionListeners) {
                            collisionListener.respond(entityA, entityB, collisionInfo);
                        }
                    }
                }
            }
        }
    }
}
