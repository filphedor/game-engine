package com.philfedor.gameengine.collisiontesting.collision;

import com.philfedor.gameengine.collision.CollisionShapeFactory;
import com.philfedor.gameengine.collisiontesting.entities.MinkowskiDifferenceTester;
import com.philfedor.gameengine.geometry.Vector2d;
import com.philfedor.gameengine.geometry.shapes.CollisionShape;
import com.philfedor.gameengine.geometry.shapes.Polygon;
import com.philfedor.gameengine.world.Entity;

import java.util.ArrayList;

public class MinkowskiDifferenceTesterCollisionShapeFactory implements CollisionShapeFactory {
    @Override
    public CollisionShape getCollisionShape(Entity entity) {
        MinkowskiDifferenceTester minkowskiDifferenceTester = (MinkowskiDifferenceTester) entity;

        ArrayList<Vector2d> points = minkowskiDifferenceTester.getPoints();
        ArrayList<Vector2d> shiftedPoints = new ArrayList<>();
        for (Vector2d point : points) {
            Vector2d shiftedPoint = point.vectorAddition(minkowskiDifferenceTester.getLocation());
            shiftedPoints.add(shiftedPoint);
        }

        return new Polygon(shiftedPoints);
    }
}
