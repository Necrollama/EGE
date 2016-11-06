package com.Elixer.net;

import com.Elixer.net.Display.Renderer;
import com.Elixer.net.Display.Texture;
import com.Elixer.net.Display.Vertex;
import com.Elixer.net.Display.Window;
import com.Elixer.net.GameObject.Entity;
import com.Elixer.net.Util.Input;
import com.Elixer.net.Util.Logger;
import com.Elixer.net.Util.Util;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL;

/**
 * Created by aweso on 10/29/2016.
 */
public abstract class ElixerGame {

    private static Window window;
    private Renderer Renderer;

    private boolean isRunning = false;
    private String name;
    private long FPS = 60;

    public ElixerGame(String name) {
        this.name = name;
    }

    public void start() {
        Logger.title("Elixer Stating");
        isRunning = true;

        init();

        //Log stuff
        Logger.message(Renderer.getOpenGlVersion());

        entity = new Entity(0, 0, -5);
        entity.defineMesh(new int[] {0, 1, 2, 0, 2, 3}, new Vertex(-1, 1, 0, 0, -1), new Vertex(1, 1, 0, -1, -1), new Vertex(1,-1, 0, -1, 0), new Vertex(-1, -1, 0, 0, 0));

        entity.getMesh().addTexture(new Texture("GrassTile_01.png"));

        run();
    }

    public void stop() {
        isRunning = false;
    }

    private void update() {
        Input.update();
    }

    Entity entity;

    private void render() {
        Renderer.clear();
        Renderer.getShader().bind();

        Renderer.draw(entity);

        window.swapBuffers();
    }

    private void init() {
        window = new Window(name, 1200, 800);
        GL.createCapabilities();
        Renderer = new Renderer(this);
        Renderer.getShader().addVertexShader("vertex330.glsl");
        Renderer.getShader().addFragmentShader("fragment330.glsl");
        Renderer.getShader().compileShader();
        Renderer.setPerspectiveValues(80, window.getWidth(), window.getHeight(), 0.01f, 1000f);
        Renderer.getShader().addUniform("transform");
        Renderer.getShader().addUniform("texture_sampler");

        Input.init(window);

        Renderer.clearColor(0.2f, 0.1f, 0.2f, 1);

        onInit();
    }

    private void cleanUp() {

    }

    private void run() {
        isRunning = true;

        long previous = System.nanoTime();
        long steps = 0;
        final long nanoPerFrame = 1000000000L/FPS;

        int frames = 0;
        long counter = System.currentTimeMillis();

        while(isRunning) {
            window.pollEvents();

            long current = System.nanoTime();
            long elasped = current - previous;
            previous = current;
            steps += elasped;

            while(steps >= nanoPerFrame) {
                update();
                frames++;
                steps -= nanoPerFrame;
            }

            render();

            if((System.currentTimeMillis() - counter) >= 1000) {
                Logger.message("FPS: " + frames);
                frames = 0;
                counter += 1000;
            }

            if(window.windowShouldClose()) {
                this.stop();
            }
        }

        cleanUp();
    }

    public long getFPS() {
        return FPS;
    }

    public void setFPS(long FPS) {
        this.FPS = FPS;
    }

    public abstract void onInit();

    public abstract void onEnd();
}
