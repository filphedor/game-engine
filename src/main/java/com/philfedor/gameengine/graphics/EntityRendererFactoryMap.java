package com.philfedor.gameengine.graphics;

import java.util.HashMap;

public class EntityRendererFactoryMap {
    private HashMap<Class, EntityRendererFactory> rendererMap;

    public EntityRendererFactoryMap() {
        this.rendererMap = new HashMap<>();
    }

    public void setRenderer(Class entityClass, EntityRendererFactory rendererFactory) {
        this.rendererMap.put(entityClass, rendererFactory);
    }

    public EntityRendererFactory getEntityRenderer(Class entityClass) {
        return this.rendererMap.get(entityClass);
    }
}
