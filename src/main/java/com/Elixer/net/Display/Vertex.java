package com.Elixer.net.Display;

import javax.vecmath.Vector2f;
import javax.vecmath.Vector3f;

/**
 * Created by aweso on 10/29/2016.
 */
public class Vertex {

    public static final int SIZE = 5;

    private Vector3f pos;
    private Vector2f uv;

    public Vertex(float x, float y, float z) {
        this(x, y, z, 0, 0);
    }

    public Vertex(float x, float y, float z, float u, float v) {
        pos = new Vector3f(x, y, z);
        uv = new Vector2f(u, v);
    }

    public Vector3f getPos() {
        return pos;
    }

    public void setPos(Vector3f pos) {
        this.pos = pos;
    }

    public Vector2f getUv() {
        return uv;
    }
}
