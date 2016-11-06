package com.Elixer.net.Display;

import com.Elixer.net.Util.Logger;
import de.matthiasmann.twl.utils.PNGDecoder;
import org.lwjgl.BufferUtils;
import java.io.IOException;
import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

/**
 * Created by aweso on 10/30/2016.
 */
public class Texture {

    private int id;
    private int width, height;

    public Texture(String fileName) {
        try {
            PNGDecoder decoder = new PNGDecoder(Texture.class.getResourceAsStream("/textures/" + fileName));
            ByteBuffer buffer = BufferUtils.createByteBuffer(4 * decoder.getWidth() * decoder.getHeight());

            width = decoder.getWidth();
            height = decoder.getHeight();

            decoder.decode(buffer, decoder.getWidth() * 4, PNGDecoder.Format.RGBA);
            buffer.flip();

            glEnable(GL_TEXTURE_2D);

            id = glGenTextures();
            glBindTexture(GL_TEXTURE_2D, id);

            glTexParameterf( GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

            glPixelStorei(GL_UNPACK_ALIGNMENT, 1);
            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, decoder.getWidth(), decoder.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);
            glGenerateMipmap(GL_TEXTURE_2D);

            glBindTexture(GL_TEXTURE_2D, 0);

        } catch (IOException e) {
            Logger.errorEnd(e.getMessage());
        }
    }

    public int getId() {
        return id;
    }
}
