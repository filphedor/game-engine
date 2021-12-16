package com.philfedor.gameengine.collisiontesting.graphics;

import com.philfedor.gameengine.graphics.Renderer;

import java.awt.*;

public interface TwoDCollisionRenderer extends Renderer {
    void renderPolygon(Color color, int[] x, int[] y);
    void renderCircle(Color color, int x, int y, int radius);
}
