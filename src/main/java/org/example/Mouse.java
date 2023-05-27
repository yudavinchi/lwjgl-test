package org.example;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWMouseButtonCallback;

public class Mouse {
    private static boolean[] buttons = new boolean[GLFW.GLFW_MOUSE_BUTTON_LAST];
    private static double mouseX;
    private static double mouseY;

    private static GLFWMouseButtonCallback mouseButtonCallback;

    public static void init(long window) {
        mouseButtonCallback = new GLFWMouseButtonCallback() {
            @Override
            public void invoke(long window, int button, int action, int mods) {
                buttons[button] = action != GLFW.GLFW_RELEASE;
            }
        };

        GLFW.glfwSetMouseButtonCallback(window, mouseButtonCallback);

        GLFW.glfwSetCursorPosCallback(window, (win, xPos, yPos) -> {
            mouseX = xPos;
            mouseY = yPos;
        });
    }

    public static boolean isButtonDown(int button) {
        return buttons[button];
    }

    public static double getMouseX() {
        return mouseX;
    }

    public static double getMouseY() {
        return mouseY;
    }

    public static void cleanup() {
        mouseButtonCallback.free();
    }
}
