package com.Elixer.net.Util;

import com.Elixer.net.Display.Window;
import org.lwjgl.BufferUtils;

import java.nio.DoubleBuffer;

import static org.lwjgl.glfw.GLFW.*;

/**
 * Created by aweso on 10/23/2016.
 */
public class Input {

    private static Window window = null;

    public static double mouseX = 0;
    public static double mouseY = 0;
    public static double mouseDelta;
    public static double mouseDeltaX;
    public static double mouseDeltaY;

    public static boolean W = false;
    public static boolean S = false;
    public static boolean D = false;
    public static boolean A = false;

    public static void update() {


        updateMousePos();
    }

    public static void init(Window window) {

        Input.window = window;

        glfwSetKeyCallback(Input.window.getId(), (win, key, scancode, action, mods) -> {
            if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
                glfwSetWindowShouldClose(win, true); // We will detect this in our rendering loop
            if (key == GLFW_KEY_W && action == GLFW_PRESS)
                Input.W = true;
            if (key == GLFW_KEY_W && action == GLFW_RELEASE)
                Input.W = false;
            if (key == GLFW_KEY_S && action == GLFW_PRESS)
                Input.S = true;
            if (key == GLFW_KEY_S && action == GLFW_RELEASE)
                Input.S = false;
            if (key == GLFW_KEY_D && action == GLFW_PRESS)
                Input.D = true;
            if (key == GLFW_KEY_D && action == GLFW_RELEASE)
                Input.D = false;
            if (key == GLFW_KEY_A && action == GLFW_PRESS)
                Input.A = true;
            if (key == GLFW_KEY_A && action == GLFW_RELEASE)
                Input.A = false;
        });
    }

    private static void updateMousePos() {
        DoubleBuffer xBuffer = BufferUtils.createDoubleBuffer(1);
        DoubleBuffer yBuffer = BufferUtils.createDoubleBuffer(1);

        glfwGetCursorPos(window.getId(), xBuffer, yBuffer);

        mouseDelta = Math.sqrt(Math.pow(xBuffer.get(0) - mouseX, 2) + Math.pow(yBuffer.get(0) - mouseY, 2));
        mouseDeltaX = xBuffer.get(0) - mouseX;
        mouseDeltaY = yBuffer.get(0) - mouseY;

        mouseX = xBuffer.get(0);
        mouseY = yBuffer.get(0);
    }

    public static void cursorLock(boolean bool) {
        if(bool) {
            glfwSetInputMode(window.getId(), GLFW_CURSOR, GLFW_CURSOR_DISABLED);
        } else {
            glfwSetInputMode(window.getId(), GLFW_CURSOR, GLFW_CURSOR_NORMAL);
        }
    }
}
