package com.philfedor.gameengine.graphics.twoD;

import com.philfedor.gameengine.graphics.Renderer;

import java.awt.image.BufferedImage;

public interface TwoDRenderer extends Renderer {
    void renderImage(BufferedImage image, int x, int y, int width, int height);
}
