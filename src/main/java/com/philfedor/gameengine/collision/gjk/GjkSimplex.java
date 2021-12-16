package com.philfedor.gameengine.collision.gjk;

import com.philfedor.gameengine.geometry.Vector2d;

import java.util.ArrayList;

public class GjkSimplex {
    private ArrayList<Vector2d> points;
    private double sensitivity;

    public GjkSimplex(double sensitivity) {
        this.sensitivity = sensitivity;
        this.points = new ArrayList<>();
    }

    public void addPoint(Vector2d vector2d) {
        points.add(vector2d);
    }

    public ArrayList<Vector2d> getPoints() {
        return this.points;
    }

    public Vector2d getLast() {
        return this.points.get(this.points.size() - 1);
    }

    public Vector2d update() {
        Vector2d a = this.points.get(0);
        Vector2d b = this.points.get(1);
        Vector2d c = this.points.get(2);

        //perp vector to sides
        //ba was last start, cant be again
        Vector2d bc = c.vectorSubtraction(b).perp();
        Vector2d ca = a.vectorSubtraction(c).perp();

        //vector towards origin
        Vector2d cO = c.scalarMultiply(-1);

        double bcDot = cO.dotProduct(bc);
        double caDot = cO.dotProduct(ca);

        if (bcDot > caDot) {
            this.points.set(0, b);
            this.points.set(1, c);
            this.points.remove(2);

            return bc;
        } else {
            this.points.set(0, a);
            this.points.set(1, c);
            this.points.remove(2);

            return ca;
        }
    }

    public boolean containsOrigin() {
        if (this.points.size() < 3) {
            return false;
        }

        Vector2d a = this.points.get(0);
        Vector2d b = this.points.get(1);
        Vector2d c = this.points.get(2);

        //perp vector to sides
        Vector2d ab = b.vectorSubtraction(a).perp();
        Vector2d bc = c.vectorSubtraction(b).perp();
        Vector2d ca = a.vectorSubtraction(c).perp();

        //vector towards origin
        Vector2d aO = a.scalarMultiply(-1);
        Vector2d bO = b.scalarMultiply(-1);
        Vector2d cO = c.scalarMultiply(-1);

        boolean inADir = aO.dotProduct(ab) > 0;
        boolean inBDir = bO.dotProduct(bc) > 0;
        boolean inCDir = cO.dotProduct(ca) > 0;

        return inADir == inBDir && inBDir == inCDir;
    }

    public boolean isProgressMade() {
        Vector2d last = this.points.get(2);
        Vector2d secondLast = this.points.get(1);

        double lastDistance = Math.pow(last.getX(), 2) + Math.pow(last.getY(), 2);
        double secondLastDistance = Math.pow(secondLast.getX(), 2) + Math.pow(secondLast.getY(), 2);

        return Math.abs(lastDistance - secondLastDistance) > Math.pow(this.sensitivity, 2);
    }
}
