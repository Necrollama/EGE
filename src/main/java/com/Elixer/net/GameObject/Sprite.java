package com.Elixer.net.GameObject;

import com.Elixer.net.Display.Mesh;
import com.Elixer.net.Display.Texture;
import com.Elixer.net.Display.Vertex;

/**
 * Created by aweso on 10/31/2016.
 */
public class Sprite extends Entity {

    public Sprite(float x, float y, float z, Texture texture) {
        super(x, y, z);

        this.defineMesh(new int[] {0,1,2,0,2,3}, new Vertex(-1, 1, 0, 0, 0), new Vertex(1, 1, 0, 1, 0), new Vertex(1, -1, 0, 1, 1), new Vertex(-1, -1, 0, 0, 1));
        this.getMesh().addTexture(texture);
    }
}
