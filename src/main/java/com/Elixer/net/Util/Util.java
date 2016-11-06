package com.Elixer.net.Util;

import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

/**
 * Created by aweso on 10/29/2016.
 */
public class Util {

    public static FloatBuffer createFlippedBuffer(Matrix4f value)
    {
        FloatBuffer buffer = BufferUtils.createFloatBuffer(4 * 4);

        buffer.put(value.get(new float[16]));

        buffer.flip();

        return buffer;
    }

    public static IntBuffer createFlippedBuffer(int[] indecies) {
        IntBuffer buffer = BufferUtils.createIntBuffer(indecies.length);

        buffer.put(indecies);

        buffer.flip();

        return buffer;
    }
}
