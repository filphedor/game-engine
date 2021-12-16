package com.philfedor.gameengine.pong.graphics;

import com.philfedor.gameengine.graphics.twoD.TwoDEntityRenderer;
import com.philfedor.gameengine.graphics.twoD.TwoDRenderer;
import com.philfedor.gameengine.pong.entities.Ball;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class BallRenderer extends TwoDEntityRenderer {
    private BufferedImage image;
    private TwoDRenderer renderer;

    public BallRenderer() {
        try {
            this.image = ImageIO.read(new File("resources/ball.png"));
        } catch (Exception e) {
            System.out.println("oopy");
        }
    }

    public void setRenderer(TwoDRenderer twoDRenderer) {
        this.renderer = twoDRenderer;
    }

    public void render() {
        Ball ball = (Ball) this.entity;

        this.renderer.renderImage(this.image, (int) (ball.getLocation().getX() - ball.getRadius()), (int) (ball.getLocation().getY() - ball.getRadius()), (int) ball.getRadius() * 2, (int) ball.getRadius() * 2);
    }
}