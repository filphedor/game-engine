package com.philfedor.gameengine.pong.graphics;

import com.philfedor.gameengine.graphics.twoD.TwoDEntityRenderer;
import com.philfedor.gameengine.graphics.twoD.TwoDRenderer;
import com.philfedor.gameengine.pong.entities.Wall;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class WallRenderer extends TwoDEntityRenderer {
    private BufferedImage image;
    private TwoDRenderer renderer;

    public WallRenderer() {
        try {
            this.image = ImageIO.read(new File("resources/wall.jpg"));
        } catch (Exception e) {
            System.out.println("oopy");
        }
    }

    public void setRenderer(TwoDRenderer twoDRenderer) {
        this.renderer = twoDRenderer;
    }

    public void render() {
        Wall wall = (Wall) this.entity;

        this.renderer.renderImage(this.image, (int) (wall.getLocation().getX() - (wall.getWidth() / 2)), (int) (wall.getLocation().getY() - (wall.getHeight() / 2)), (int) wall.getWidth(), (int) wall.getHeight());
    }
}