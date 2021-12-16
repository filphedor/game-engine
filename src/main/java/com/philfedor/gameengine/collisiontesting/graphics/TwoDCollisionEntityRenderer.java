package com.philfedor.gameengine.collisiontesting.graphics;

import com.philfedor.gameengine.geometry.Vector2d;
import com.philfedor.gameengine.geometry.shapes.Circle;
import com.philfedor.gameengine.geometry.shapes.CollisionShape;
import com.philfedor.gameengine.geometry.shapes.Polygon;
import com.philfedor.gameengine.collision.CollisionShapeFactory;
import com.philfedor.gameengine.graphics.EntityRenderer;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class TwoDCollisionEntityRenderer extends EntityRenderer {
    private TwoDCollisionRenderer renderer;
    private Color color;
    private HashMap<Class, CollisionShapeFactory> collisionDataMap;

    public void setRenderer(TwoDCollisionRenderer renderer) {
        this.renderer = renderer;
    };

    public TwoDCollisionEntityRenderer(Color color, HashMap<Class, CollisionShapeFactory> collisionDataMap) {
        this.color = color;
        this.collisionDataMap = collisionDataMap;
    }

    @Override
    public void render() {
        CollisionShape collisionShape = this.collisionDataMap.get(this.entity.getClass()).getCollisionShape(this.entity);

        if (collisionShape instanceof Circle) {
            Circle circle = (Circle) collisionShape;

            this.renderer.renderCircle(
                    this.color,
                    (int) (circle.getLocation().getX() - circle.getRadius()),
                    (int) (circle.getLocation().getY() - circle.getRadius()),
                    (int) circle.getRadius()
            );
        }

        if (collisionShape instanceof Polygon) {
            Polygon polygon = (Polygon) collisionShape;

            ArrayList<Vector2d> points = polygon.getPoints();

            int[] xs = new int[points.size()];
            int[] ys = new int[points.size()];

            for (int i = 0; i < points.size(); i++) {
                xs[i] = (int) points.get(i).getX();
                ys[i] = (int) points.get(i).getY();
            }

            this.renderer.renderPolygon(this.color, xs, ys);
        }
    }
}
