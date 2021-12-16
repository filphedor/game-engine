package com.philfedor.gameengine.pong;

import com.philfedor.gameengine.collision.CollisionListener;
import com.philfedor.gameengine.geometry.Vector2d;
import com.philfedor.gameengine.collision.CollisionShapeFactory;
import com.philfedor.gameengine.collision.CollisionSolver;
import com.philfedor.gameengine.graphics.CustomWindow;
import com.philfedor.gameengine.graphics.EntityRendererFactoryMap;
import com.philfedor.gameengine.graphics.twoD.BoundedWorldRenderer;
import com.philfedor.gameengine.pong.collision.listeners.BounceListener;
import com.philfedor.gameengine.pong.entities.Wall;
import com.philfedor.gameengine.pong.collision.shapefactories.BallCollisionShapeFactory;
import com.philfedor.gameengine.pong.collision.shapefactories.WallCollisionShapeFactory;
import com.philfedor.gameengine.pong.entities.Ball;
import com.philfedor.gameengine.pong.graphics.BallEntityRendererFactory;
import com.philfedor.gameengine.pong.graphics.WallEntityRendererFactory;
import com.philfedor.gameengine.world.BoundedWorld;

import java.awt.*;
import java.util.HashMap;

import static java.lang.Thread.sleep;

public class Pong {
    public void start() {
        Frame frame = new Frame("Pong");

        HashMap<Class, CollisionShapeFactory> collisionShapeFactoryMap = new HashMap<>();
        collisionShapeFactoryMap.put(Ball.class, new BallCollisionShapeFactory());
        collisionShapeFactoryMap.put(Wall.class, new WallCollisionShapeFactory());

        CollisionSolver collisionSolver = new CollisionSolver(collisionShapeFactoryMap, 0.01, 1);
        collisionSolver.addCollisionListener(new BounceListener());

        BoundedWorld world = new BoundedWorld(1000, 1000);
        world.addListener(collisionSolver);

        Ball ball = new Ball("Ball", new Vector2d(130, 450), 50);
        Wall wall = new Wall("Wall", new Vector2d(25, 500), 50, 1000);
        Wall wall1 = new Wall("Wall1", new Vector2d(975, 500), 50, 1000);
        Wall wall2 = new Wall("Wall2", new Vector2d(500, 25), 800, 50);
        Wall wall3 = new Wall("Wall3", new Vector2d(500, 975), 800, 50);

        world.addEntity(ball);
        world.addEntity(wall);
        world.addEntity(wall1);
        world.addEntity(wall2);
        world.addEntity(wall3);

        EntityRendererFactoryMap entityRendererFactoryMap = new EntityRendererFactoryMap();
        entityRendererFactoryMap.setRenderer(Ball.class, new BallEntityRendererFactory());
        entityRendererFactoryMap.setRenderer(Wall.class, new WallEntityRendererFactory());

        BoundedWorldRenderer worldRenderer = new BoundedWorldRenderer(world, entityRendererFactoryMap, 2000, 2000);

        CustomWindow window = new CustomWindow(worldRenderer);
        window.setSize(500, 500);
        frame.add(window);
        frame.setSize(500, 500);
        frame.setVisible(true);

        while(true) {
            worldRenderer.update();
            world.update();
            window.repaint();

            try {
                sleep(10);
            } catch (Exception e) {
                System.out.println("oopey");
            }
        }
    }
}
