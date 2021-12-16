package com.philfedor.gameengine.collisiontesting.graphics;

import com.philfedor.gameengine.graphics.EntityRenderer;

import java.awt.*;
import java.util.HashMap;

public class CollisionEntityRenderer extends EntityRenderer {
    private HashMap<Class, Color> colorMap;

    public CollisionEntityRenderer(HashMap<Class, Color> colorMap) {
        this.colorMap = colorMap;
    }

    @Override
    public void render() {

    }
}
