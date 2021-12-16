package com.philfedor.gameengine.pong.graphics;

import com.philfedor.gameengine.graphics.EntityRenderer;
import com.philfedor.gameengine.graphics.EntityRendererFactory;

public class WallEntityRendererFactory implements EntityRendererFactory {
    @Override
    public EntityRenderer getRenderer() {
        return new WallRenderer();
    }
}
