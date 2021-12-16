package com.philfedor.gameengine.collisiontesting.graphics;

import com.philfedor.gameengine.collision.CollisionShapeFactory;
import com.philfedor.gameengine.graphics.EntityRenderer;
import com.philfedor.gameengine.world.BoundedWorld;
import com.philfedor.gameengine.world.Entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

public class BoundedWorldCollisionRenderer implements TwoDCollisionRenderer {
    private BoundedWorld world;
    private HashMap<Class, CollisionShapeFactory> collisionDataMap;
    private HashMap<Class, Color> colorMap;
    private int width;
    private int height;
    private double widthRatio;
    private double heightRatio;
    private BufferedImage lastFrame;
    private HashMap<String, EntityRenderer> renderers;
    private Graphics2D graphics2D;

    public BoundedWorldCollisionRenderer(
            BoundedWorld world,
            HashMap<Class, CollisionShapeFactory> collisionDataMap,
            HashMap<Class, Color> colorMap,
            int width,
            int height
    ) {
        this.world = world;
        this.collisionDataMap = collisionDataMap;
        this.colorMap = colorMap;
        this.width = width;
        this.height = height;
        this.widthRatio = (double) this.width / world.getWidth();
        this.heightRatio = (double) this.height / world.getHeight();
        this.lastFrame = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        this.renderers = new HashMap<>();
    }

    public void update() {
        ArrayList<Entity> entities = this.world.getEntities();

        this.updateRendererList(entities);

        BufferedImage frame = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);
        this.graphics2D = frame.createGraphics();
        this.graphics2D.setColor(new Color(0, 0, 0));
        this.graphics2D.fillRect(0, 0, this.width, this.height);

        for (EntityRenderer entityRenderer : this.renderers.values()) {
            TwoDCollisionEntityRenderer twoDCollisionEntityRenderer = (TwoDCollisionEntityRenderer) entityRenderer;
            twoDCollisionEntityRenderer.setRenderer(this);
            twoDCollisionEntityRenderer.render();
        }

        this.lastFrame = frame;
    }

    public void updateRendererList(ArrayList<Entity> entities) {
        for (Entity entity : entities) {
            if (!this.renderers.containsKey(entity.getId())) {
                this.renderers.put(
                        entity.getId(),
                        new TwoDCollisionEntityRenderer(this.colorMap.get(entity.getClass()), this.collisionDataMap)
                );
            }

            this.renderers.get(entity.getId()).updateEntity(entity);
        }
    }

    @Override
    public BufferedImage getFrame() {
        return this.lastFrame;
    }

    @Override
    public void renderPolygon(Color color, int[] xs, int[] ys) {
        this.graphics2D.setColor(color);

        int[] adjustedXs = new int[xs.length];
        int[] adjustedYs = new int[ys.length];

        for (int i = 0; i < xs.length; i++) {
            adjustedXs[i] = (int) (xs[i] * this.widthRatio);
            adjustedYs[i] = (int) (ys[i] * this.heightRatio);
        }
        this.graphics2D.fillPolygon(adjustedXs, adjustedYs, adjustedXs.length);
    }

    @Override
    public void renderCircle(Color color, int x, int y, int radius) {
        this.graphics2D.setColor(color);
        this.graphics2D.fillOval(
                (int) (x * this.widthRatio),
                (int) (y * this.heightRatio),
                (int) (radius * 2 * this.widthRatio),
                (int) (radius * 2 * this.heightRatio)
        );
    }
}
