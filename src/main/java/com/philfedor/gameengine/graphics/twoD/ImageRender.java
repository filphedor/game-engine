package com.philfedor.gameengine.graphics.twoD;

import java.awt.image.BufferedImage;

public class ImageRender {
    public BufferedImage image;
    public int x;
    public int y;
    public int width;
    public int height;

    public ImageRender(BufferedImage image, int x, int y, int width, int height) {
        this.image = image;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}
