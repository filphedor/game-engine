package com.philfedor.gameengine;

import com.philfedor.gameengine.pong.Pong;

public class GameEngine {
    public static void main(String[] args) {
        Pong pong = new Pong();
        pong.start();
    }
}
