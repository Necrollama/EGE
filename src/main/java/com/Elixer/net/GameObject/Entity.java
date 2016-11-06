package com.Elixer.net.GameObject;

import com.Elixer.net.Display.Mesh;
import com.Elixer.net.Display.Vertex;
import org.joml.AxisAngle4f;
import org.joml.Matrix4f;
import org.joml.Vector3f;

/**
 * Created by aweso on 10/29/2016.
 */
public class Entity {

    private Matrix4f transform = new Matrix4f();
    private Mesh mesh = null;

    protected Vector3f Up;
    protected Vector3f Left;
    protected Vector3f Forward;

    public Entity(float x, float y, float z) {
        transform.translate(x, y, z);
    }

    public Matrix4f getTransform() {
        return transform;
    }

    public Vector3f getPosition() {
        Vector3f pos = new Vector3f();
        transform.getTranslation(pos);
        return pos;
    }

    public Vector3f getScale() {
        Vector3f pos = new Vector3f();
        transform.getScale(pos);
        return pos;
    }

    public Entity defineMesh(int[] indecies, Vertex... verticies) {
        mesh = new Mesh(indecies, verticies);
        return this;
    }

    public Mesh getMesh() {
        return mesh;
    }

    public void setUp(Vector3f up) {
        Up = up.normalize();
    }
}
