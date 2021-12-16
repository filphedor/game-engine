package com.philfedor.gameengine.graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class CustomWindow extends JPanel {
    private Renderer renderer;
    public CustomWindow(Renderer renderer) {
        this.renderer = renderer;
    }

    @Override
    public void update(Graphics g) {
        this.paint(g);
    }

    @Override
    public void paint(Graphics g) {
        BufferedImage image = this.renderer.getFrame();

        double windowWidth = this.getWidth();
        double windowHeight = this.getHeight();

        double imageWidth = image.getWidth();
        double imageHeight = image.getHeight();

        double widthRatio = windowWidth / imageWidth;
        double heightRatio = windowHeight / imageHeight;

        BufferedImage sizedImage = new BufferedImage((int) windowWidth, (int) windowHeight, image.getType());

        AffineTransform affineTransform = new AffineTransform();
        affineTransform.scale(widthRatio, heightRatio);
        AffineTransformOp transformOp = new AffineTransformOp(affineTransform, AffineTransformOp.TYPE_BILINEAR);

        sizedImage = transformOp.filter(image, sizedImage);

        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.drawImage(sizedImage, 0, 0, (int) windowWidth, (int) windowHeight, null);
    }
}
