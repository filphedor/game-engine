package com.philfedor.gameengine.geometry.shapes;

import com.philfedor.gameengine.geometry.Vector2d;

import java.util.ArrayList;

public class Rectangle extends Polygon {
    public Rectangle(Vector2d location, double width, double height) {
        ArrayList<Vector2d> points = new ArrayList<>();
        Vector2d xDiff = new Vector2d(width / 2, 0);
        Vector2d yDiff = new Vector2d(0, height / 2);

        points.add(location.vectorAddition(xDiff.scalarMultiply(-1)).vectorAddition(yDiff));
        points.add(location.vectorAddition(xDiff).vectorAddition(yDiff));
        points.add(location.vectorAddition(xDiff).vectorAddition(yDiff.scalarMultiply(-1)));
        points.add(location.vectorAddition(xDiff.scalarMultiply(-1)).vectorAddition(yDiff.scalarMultiply(-1)));

        this.points = points;
    }
}
