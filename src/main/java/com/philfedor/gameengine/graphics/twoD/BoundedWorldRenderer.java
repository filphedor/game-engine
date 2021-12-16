package com.philfedor.gameengine.graphics.twoD;

import com.philfedor.gameengine.graphics.EntityRenderer;
import com.philfedor.gameengine.graphics.EntityRendererFactoryMap;
import com.philfedor.gameengine.world.BoundedWorld;
import com.philfedor.gameengine.world.Entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

public class BoundedWorldRenderer implements TwoDRenderer {
    private BoundedWorld world;
    private EntityRendererFactoryMap entityRendererFactoryMap;
    private int width;
    private int height;
    private double widthRatio;
    private double heightRatio;
    private BufferedImage lastFrame;
    private HashMap<String, EntityRenderer> renderers;
    private ArrayList<ImageRender> imageRenders;

    public BoundedWorldRenderer(BoundedWorld world, EntityRendererFactoryMap entityRendererFactoryMap, int width, int height) {
        this.world = world;
        this.entityRendererFactoryMap = entityRendererFactoryMap;
        this.width = width;
        this.height = height;
        this.widthRatio = (double) this.width / world.getWidth();
        this.heightRatio = (double) this.height / world.getHeight();
        this.lastFrame = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        this.renderers = new HashMap<>();
    }

    public void update() {
        ArrayList<Entity> entities = this.world.getEntities();
        this.imageRenders = new ArrayList<>();

        this.updateRendererList(entities);

        for (EntityRenderer entityRenderer : this.renderers.values()) {
            TwoDEntityRenderer twoDEntityRenderer = (TwoDEntityRenderer) entityRenderer;
            twoDEntityRenderer.setRenderer(this);
            twoDEntityRenderer.render();
        }

        BufferedImage frame = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = frame.createGraphics();
        graphics2D.setColor(new Color(0, 0, 0));
        graphics2D.fillRect(0, 0, this.width, this.height);

        for (ImageRender imageRender : this.imageRenders) {
            graphics2D.drawImage(imageRender.image, imageRender.x, imageRender.y, imageRender.width, imageRender.height, null);
        }

        this.lastFrame = frame;
    }

    public void updateRendererList(ArrayList<Entity> entities) {
        for (Entity entity : entities) {
            if (!this.renderers.containsKey(entity.getId())) {
                this.renderers.put(entity.getId(), this.entityRendererFactoryMap.getEntityRenderer(entity.getClass()).getRenderer());
            }

            this.renderers.get(entity.getId()).updateEntity(entity);
        }
    }

    @Override
    public BufferedImage getFrame() {
        return this.lastFrame;
    }

    @Override
    public void renderImage(BufferedImage image, int x, int y, int width, int height) {
        this.imageRenders.add(new ImageRender(image, (int) (x * this.widthRatio), (int) (y * this.heightRatio), (int) (width * this.widthRatio), (int) (height * this.heightRatio)));
    }
}
