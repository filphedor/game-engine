package com.philfedor.gameengine.collisiontesting.collision;

import com.philfedor.gameengine.collision.CollisionShapeFactory;
import com.philfedor.gameengine.collisiontesting.entities.GjkTester;
import com.philfedor.gameengine.geometry.Vector2d;
import com.philfedor.gameengine.geometry.shapes.CollisionShape;
import com.philfedor.gameengine.geometry.shapes.Polygon;
import com.philfedor.gameengine.world.Entity;

import java.util.ArrayList;

public class GjkTesterCollisionShapeFactory implements CollisionShapeFactory {
    @Override
    public CollisionShape getCollisionShape(Entity entity) {
        GjkTester gjkTester = (GjkTester) entity;

        ArrayList<Vector2d> points = gjkTester.getPoints();
        ArrayList<Vector2d> shiftedPoints = new ArrayList<>();
        for (Vector2d point : points) {
            Vector2d shiftedPoint = point.vectorAddition(gjkTester.getLocation());
            shiftedPoints.add(shiftedPoint);
        }

        return new Polygon(shiftedPoints);
    }
}
