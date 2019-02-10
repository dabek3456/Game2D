package dev.marcindabrowski.tilegame;

import dev.marcindabrowski.tilegame.display.Display;
import dev.marcindabrowski.tilegame.gfx.Assets;
import dev.marcindabrowski.tilegame.gfx.GameCamera;
import dev.marcindabrowski.tilegame.input.KeyManager;
import dev.marcindabrowski.tilegame.states.GameState;
import dev.marcindabrowski.tilegame.states.State;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable {

    // States
    private State gameState;
    private State menuState;

    private Display display;
    private BufferStrategy bufferStrategy;
    private Graphics graphics;

    private boolean running = false;

    // Input
    private KeyManager keyManager;

    private int width;
    private int height;
    public String title;

    // camera

    private GameCamera gameCamera;

    // handler

    private Handler handler;

    private Thread thread;

    public Game(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        keyManager = new KeyManager();
    }

    private void init() {
        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyManager);
        Assets.init();

        handler = new Handler(this);
        gameCamera = new GameCamera(handler,0 ,0);

        gameState = new GameState(handler);
        menuState = new GameState(handler);


        State.setState(gameState);
    }

    private void tick() {

        keyManager.tick();
        if(State.getState() != null) {
            State.getState().tick();
        }
    }

    private void render() {
        bufferStrategy = display.getCanvas().getBufferStrategy();
        if (bufferStrategy == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }

        graphics = bufferStrategy.getDrawGraphics();
        graphics.clearRect(0, 0, width, height);

        // Draw here!!

        if(State.getState() != null) {
            State.getState().render(graphics);
        }

        // End Drawing!
        bufferStrategy.show();
        graphics.dispose();

    }

    public void run() {
        init();

        int fps = 60;
        double timePerTick = 1.0E09 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while (running) {

            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if (delta >= 1) {

                tick();
                render();

                ticks++;
                delta--;
            }

            if(timer >= 1000000000) {

                System.out.println("Ticks and Frames: " + ticks);
                ticks = 0;
                timer = 0;
            }
        }

        stop();
    }

    public KeyManager getKeyManager() {
        return keyManager;
    }

    public GameCamera getGameCamera() {
        return gameCamera;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public synchronized void start() {
        if (!running) {
            running = true;
            thread = new Thread(this);
            thread.start();
        }
    }

    public synchronized void stop() {
        if (running) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            running = false;
        }
    }
}
