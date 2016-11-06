package com.Elixer.net.Display;

import com.Elixer.net.Util.Logger;
import com.Elixer.net.Util.Util;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.FloatBuffer;
import java.util.HashMap;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL32.GL_GEOMETRY_SHADER;

/**
 * Created by aweso on 10/29/2016.
 */
public class Shader {

    private int id;
    private HashMap<String, Integer> uniforms;

    public Shader() {
        id = glCreateProgram();
        uniforms = new HashMap<String, Integer>();

        if(id == 0) {
            Logger.errorEnd("Shader Creation failed.");
        }
    }

    public void addVertexShader(String fileName) {
        addProgram(loadShader(fileName), GL_VERTEX_SHADER);
    }

    public void addFragmentShader(String fileName) {
        addProgram(loadShader(fileName), GL_FRAGMENT_SHADER);
    }

    public void addGeometryShader(String fileName) {
        addProgram(loadShader(fileName), GL_GEOMETRY_SHADER);
    }

    public void compileShader() {
        glLinkProgram(id);

        if(glGetProgrami(id, GL_LINK_STATUS) == 0) {
            Logger.errorEnd(glGetProgramInfoLog(id, 1024));
        }

        glValidateProgram(id);

        if(glGetProgrami(id, GL_VALIDATE_STATUS) == 0) {
            Logger.errorEnd(glGetProgramInfoLog(id, 1024));
        }
    }

    public void addUniform(String name) {
        int uniform = glGetUniformLocation(id, name);

        if(uniform == -1) {
            Logger.error("Cant find Uniform: " + name + ".");
        }

        uniforms.put(name, uniform);
    }

    public void setUniform(String name, int value) {
        glUniform1i(uniforms.get(name), value);
    }

    public void setUniform(String name, float value) {
        glUniform1f(uniforms.get(name), value);
    }

    public void setUniform(String name, Vector3f value) {
        glUniform3f(uniforms.get(name), value.x, value.y, value.z);
    }

    public void setUniform(String name, Matrix4f value) {
        FloatBuffer fb = BufferUtils.createFloatBuffer(16);
        value.get(fb);
        glUniformMatrix4fv(uniforms.get(name), false, fb);
    }

    public void bind() {
        glUseProgram(id);
    }

    private void addProgram(String text, int type) {
        int shader = glCreateShader(type);

        if(shader == 0)
            Logger.errorEnd("Unable to make shader of type " + type + ".");

        glShaderSource(shader, text);
        glCompileShader(shader);

        if(glGetShaderi(shader, GL_COMPILE_STATUS) == 0) {
            Logger.errorEnd(glGetShaderInfoLog(shader, 1024));
        }

        glAttachShader(id, shader);
    }

    private String loadShader(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        StringBuilder shaderSource = new StringBuilder();
        BufferedReader shaderReader = null;

        try {
            shaderReader = new BufferedReader(new FileReader(classLoader.getResource("shaders/" + fileName).getFile()));
            String line;

            while((line = shaderReader.readLine()) != null) {
                shaderSource.append(line).append("\n");
            }

            shaderReader.close();
        } catch(Exception e) {
            Logger.error(e.getMessage());
        }

        return shaderSource.toString();
    }
}
