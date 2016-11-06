package com.Elixer.net.Display;

import com.Elixer.net.Util.Logger;
import org.lwjgl.glfw.GLFWVidMode;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.*;
/**
 * Created by aweso on 10/29/2016.
 */
public class Window {

    private long id;
    private String name;
    private int width, height;

    public Window(String name, int width, int height) {
        this.name = name;
        this.width = width;
        this.height = height;

        if(!glfwInit())
            Logger.errorEnd("GLFW could not init.");

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);

        id = glfwCreateWindow(width, height, name, NULL, NULL);

        if(id == 0)
            Logger.errorEnd("Window was unable to be created");

        GLFWVidMode vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());

        glfwSetWindowPos(id, (vidMode.width() - width) / 2,(vidMode.height() - height) / 2);

        glfwMakeContextCurrent(id);

        glfwSwapInterval(1);

        glfwShowWindow(id);
    }

    public void pollEvents() {
        glfwPollEvents();
    }

    public void swapBuffers() {
        glfwSwapBuffers(id);
    }

    public boolean windowShouldClose() {
        return glfwWindowShouldClose(id);
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public long getId() {
        return id;
    }
}
