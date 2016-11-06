package com.Elixer.net.Display;

import com.Elixer.net.ElixerGame;
import com.Elixer.net.GameObject.Entity;
import com.Elixer.net.Util.Logger;
import org.joml.Math;
import org.joml.Matrix4f;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

/**
 * Created by aweso on 10/29/2016.
 */
public class Renderer {

    private Shader shader;

    private ElixerGame game;
    private float fov, width, hieght, zNear, zFar;
    private boolean is3D = true;

    public Renderer(ElixerGame game) {

        this.game = game;

        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

/*        glFrontFace(GL_CW);
        glCullFace(GL_BACK);
        glEnable(GL_CULL_FACE);*/

        glEnable(GL_DEPTH_TEST);
        glEnable(GL_ALPHA_TEST);

        glEnable(GL_FRAMEBUFFER_SRGB);

        shader = new Shader();
    }

    public Matrix4f getPerspectiveMatrix() {
        Matrix4f mat;

        if(is3D) {
            mat = new Matrix4f().perspective(fov, width/hieght, zNear, zFar);
        } else {
            mat = new Matrix4f().setOrtho(-width/2, width/2, -hieght/2, hieght/2, zNear, zFar);
        }

        return mat;
    }

    public void setPerspectiveValues(float fov, float width, float height, float zNear, float zFar) {
        this.fov = (float) Math.toRadians(fov);
        this.width = width;
        this.hieght = height;
        this.zNear = zNear;
        this.zFar = zFar;
    }

    public void clear() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }

    public void clearColor(float r, float g, float b, float a) {
        glClearColor(r, g, b, a);
    }

    public static String getOpenGlVersion() {
        return "OpenGL " + glGetString(GL_VERSION);
    }

    public Shader getShader() {
        return shader;
    }

    public void draw(Entity entity) {
        shader.setUniform("transform", getPerspectiveMatrix().mul(entity.getTransform()));
        shader.setUniform("texture_sampler", 0);
        entity.getMesh().draw();
    }
}
