package com.philfedor.gameengine.geometry.shapes;

import com.philfedor.gameengine.geometry.Vector2d;

import java.util.ArrayList;

public class Polygon implements CollisionShape {
    protected ArrayList<Vector2d> points;

    public Polygon() {
        this.points = new ArrayList<>();
    }

    public Polygon(ArrayList<Vector2d> points) {
        this.points = points;
    }

    public ArrayList<Vector2d> getPoints() {
        return this.points;
    }

    @Override
    public Vector2d farthestPointInDirection(Vector2d vector2d) {
        double maxDotProduct = Double.NEGATIVE_INFINITY;
        Vector2d furthestPoint = null;

        for (Vector2d point : this.points) {
            double dotProduct = point.dotProduct(vector2d);
            if (dotProduct > maxDotProduct) {
                furthestPoint = point;
                maxDotProduct = dotProduct;
            }
        }

        return furthestPoint;
    }
}
