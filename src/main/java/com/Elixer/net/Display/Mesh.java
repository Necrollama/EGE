package com.Elixer.net.Display;

import com.Elixer.net.Util.Logger;
import com.Elixer.net.Util.Util;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL21;

import javax.vecmath.Vector2f;
import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL13.*;
/**
 * Created by aweso on 10/29/2016.
 */
public class Mesh {

    private Texture texture;

    private int positionVBO;
    private int iboId;
    private int vaoId;
    private int size;

    public Mesh(int[] indecies, Vertex... vertecies) {
        size = indecies.length;

        vaoId = glGenVertexArrays();
        glBindVertexArray(vaoId);

        positionVBO = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, positionVBO);
        glBufferData(GL_ARRAY_BUFFER, getFlippedBuffer(vertecies), GL_STATIC_DRAW);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, Vertex.SIZE * 4, 0);
        glVertexAttribPointer(1, 2, GL_FLOAT, false, Vertex.SIZE * 4, 12);

        iboId = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, iboId);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, Util.createFlippedBuffer(indecies), GL_STATIC_DRAW);

        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
        glBindVertexArray(0);
    }

    public void addTexture(Texture texture) {
        this.texture = texture;
    }

    private FloatBuffer getFlippedBuffer(Vertex... vertecies) {
        FloatBuffer data = BufferUtils.createFloatBuffer(vertecies.length * Vertex.SIZE);

        for(Vertex v: vertecies) {
            data.put(v.getPos().x);
            data.put(v.getPos().y);
            data.put(v.getPos().z);
            data.put(v.getUv().x);
            data.put(v.getUv().y);
        }

        data.flip();

        return data;
    }

    private FloatBuffer getFlippedBuffer(Vector2f... vertecies) {
        FloatBuffer data = BufferUtils.createFloatBuffer(vertecies.length * 2);

        for(Vector2f v: vertecies) {
            data.put(v.x);
            data.put(v.y);
        }

        data.flip();

        return data;
    }

    public void draw() {
        glBindVertexArray(vaoId);
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);

        glActiveTexture(GL_TEXTURE0);
        glBindTexture(GL_TEXTURE_2D, texture.getId());

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, iboId);
        glDrawElements(GL_TRIANGLES, size, GL_UNSIGNED_INT, 0);

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
        glDisableVertexAttribArray(1);
        glDisableVertexAttribArray(0);
        glBindVertexArray(0);
    }
}
