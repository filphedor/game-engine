package com.philfedor.gameengine.collisiontesting;

import com.philfedor.gameengine.collision.CollisionShapeFactory;
import com.philfedor.gameengine.collisiontesting.collision.CircleEntityCollisionShapeFactory;
import com.philfedor.gameengine.collisiontesting.collision.MinkowskiDifferenceTesterCollisionShapeFactory;
import com.philfedor.gameengine.collisiontesting.collision.RectangleEntityCollisionShapeFactory;
import com.philfedor.gameengine.collisiontesting.entities.CircleEntity;
import com.philfedor.gameengine.collisiontesting.entities.MinkowskiDifferenceTester;
import com.philfedor.gameengine.collisiontesting.entities.RectangleEntity;
import com.philfedor.gameengine.collisiontesting.graphics.BoundedWorldCollisionRenderer;
import com.philfedor.gameengine.geometry.Vector2d;
import com.philfedor.gameengine.graphics.CustomWindow;
import com.philfedor.gameengine.world.BoundedWorld;

import java.awt.*;
import java.util.HashMap;

import static java.lang.Thread.sleep;

public class MinkowskiDifferenceTesting {
    public void start() {
        Frame frame = new Frame("Minkowski Testing");

        HashMap<Class, CollisionShapeFactory> collisionShapeFactoryMap = new HashMap<>();
        collisionShapeFactoryMap.put(CircleEntity.class, new CircleEntityCollisionShapeFactory());
        collisionShapeFactoryMap.put(RectangleEntity.class, new RectangleEntityCollisionShapeFactory());
        collisionShapeFactoryMap.put(MinkowskiDifferenceTester.class, new MinkowskiDifferenceTesterCollisionShapeFactory());

        HashMap<Class, Color> colorMap = new HashMap<>();
        colorMap.put(CircleEntity.class, new Color(255, 0, 0, 100));
        colorMap.put(RectangleEntity.class, new Color(0, 255, 0, 100));
        colorMap.put(MinkowskiDifferenceTester.class, new Color(0, 0, 255, 100));

        BoundedWorld world = new BoundedWorld(1000, 1000);

        CircleEntity circleEntity = new CircleEntity("Circle", new Vector2d(80, 450), 50);
        RectangleEntity rectangleEntity = new RectangleEntity("Rectangle", new Vector2d(25, 500), 50, 1000);
        MinkowskiDifferenceTester minkowskiDifferenceTester = new MinkowskiDifferenceTester("MinkoskiTester", new Vector2d(500, 500), circleEntity, rectangleEntity, collisionShapeFactoryMap, 100);
        world.addEntity(circleEntity);
        world.addEntity(rectangleEntity);
        world.addEntity(minkowskiDifferenceTester);

        BoundedWorldCollisionRenderer worldRenderer = new BoundedWorldCollisionRenderer(world, collisionShapeFactoryMap, colorMap,2000, 2000);

        CustomWindow window = new CustomWindow(worldRenderer);
        window.setSize(500, 500);
        frame.add(window);
        frame.setSize(500, 500);
        frame.setVisible(true);

        while(true) {
            System.out.println("as");
            worldRenderer.update();
            world.update();
            window.repaint();

            try {
                sleep(50);
            } catch (Exception e) {
                System.out.println("oopey");
            }
        }
    }
}