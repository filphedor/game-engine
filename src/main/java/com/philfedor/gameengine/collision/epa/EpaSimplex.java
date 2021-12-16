package com.philfedor.gameengine.collision.epa;

import com.philfedor.gameengine.geometry.Vector2d;

import java.util.ArrayList;

public class EpaSimplex {
    private ArrayList<Vector2d> points;
    private boolean hasChanged;
    private double closestDistance;
    private Vector2d normal;
    private ArrayList<Vector2d> closestEdge;

    public EpaSimplex(ArrayList<Vector2d> points) {
        this.points = points;
        this.hasChanged = true;
    }

    public void addPoint(Vector2d vector2d) {
        //add point between points making up closest edge to maintain convex
        int firstPointIndex = 0;

        for (int i = 0; i < this.points.size(); i++) {
            if (this.points.get(i) == this.closestEdge.get(0)) {
                firstPointIndex = i;
            }
        }

        if (firstPointIndex < this.points.size() - 1) {
            this.points.add(firstPointIndex + 1, vector2d);
        } else {
            this.points.add(vector2d);
        }

        this.hasChanged = true;
    }

    public double getClosestDistance() {
        if (!this.hasChanged) {
            return this.closestDistance;
        } else {
            this.update();
            return this.closestDistance;
        }
    }

    public Vector2d getNormal() {
        if (!this.hasChanged) {
            return this.normal;
        } else {
            this.update();
            return this.normal;
        }
    }

    public ArrayList<Vector2d> getClosestEdge() {
        if (!this.hasChanged) {
            return this.closestEdge;
        } else {
            this.update();
            return this.closestEdge;
        }
    }

    public ArrayList<Vector2d> getPoints() {
        if (!this.hasChanged) {
            return this.points;
        } else {
            this.update();
            return this.points;
        }
    }

    private void update() {
        double closestDistance = Double.POSITIVE_INFINITY;
        Vector2d normal = new Vector2d(0, 0);
        Vector2d pointA = this.points.get(0);
        Vector2d pointB = this.points.get(1);

        for (int i = 0; i < this.points.size(); i++) {
            Vector2d currentPoint = this.points.get(i);

            Vector2d nextPoint;

            if (i + 1 < this.points.size()) {
                nextPoint = this.points.get(i + 1);
            } else {
                nextPoint = this.points.get(0);
            }

            Vector2d closestPoint = this.closestPointToOrigin(currentPoint, nextPoint);
            double distance = closestPoint.getMagnitude();

            if (distance < closestDistance) {
                closestDistance = distance;
                normal = closestPoint;
                pointA = currentPoint;
                pointB = nextPoint;
            }
        }

        ArrayList<Vector2d> closestEdge = new ArrayList<>();
        closestEdge.add(pointA);
        closestEdge.add(pointB);

        this.closestDistance = closestDistance;
        this.normal = normal;
        this.closestEdge = closestEdge;
        this.hasChanged = false;
    }

    private Vector2d closestPointToOrigin(Vector2d pointA, Vector2d pointB) {
        double ax = pointA.getX();
        double ay = pointA.getY();
        double bx = pointB.getX();
        double by = pointB.getY();

        boolean isVert = ax == bx;
        boolean isHoriz = ay == by;

        if (isVert) {
            if ((ay > 0 && by > 0) || (ay < 0 && by < 0)) {
                if (Math.abs(pointA.getY()) < Math.abs(pointB.getY())) {
                    return  pointA;
                }

                return pointB;
            } else {
                return new Vector2d(pointA.getX(), 0);
            }
        } else if (isHoriz) {
            if ((ax > 0 && bx > 0) || (ax < 0 && bx < 0)) {
                if (Math.abs(pointA.getX()) < Math.abs(pointB.getX())) {
                    return  pointA;
                }

                return pointB;
            } else {
                return new Vector2d(0, pointA.getY());
            }
        } else {
            double m1 = (by - ay) / (bx - ax);
            double m2 = -1 / m1;

            double c1 = ay - (m1 * ax);

            double xInter = c1 / (m2 - m1);

            if (xInter < ax && xInter < bx) {
                if (pointA.getX() < pointB.getX()) {
                    return  pointA;
                }

                return pointB;
            } else if (xInter > ax && xInter > bx) {
                if (pointA.getX() > pointB.getX()) {
                    return  pointA;
                }

                return pointB;
            } else {
                return new Vector2d(xInter, m2 * xInter);
            }
        }
    }
}
