package org.example;

import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;

public class Main {

    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    private long window;
    private Button button1;
    private Button button2;
    public void run() {
        init();
        loop();
        cleanup();
    }

    private void init() {

        if (!GLFW.glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        GLFW.glfwDefaultWindowHints();
        GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE);
        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_FALSE);

        window = GLFW.glfwCreateWindow(WIDTH, HEIGHT, "LWJGL Application", 0, 0);
        if (window == 0) {
            throw new IllegalStateException("Failed to create GLFW window");
        }

        // Make the window's OpenGL context current
        GLFW.glfwMakeContextCurrent(window);

        // Create capabilities instance and initialize OpenGL functions
        GL.createCapabilities();

        GL11.glViewport(0, 0, WIDTH, HEIGHT);

        GLFW.glfwShowWindow(window);

        Mouse.init(window);

        // Create the button
        this.button1 = new Button(0, 0, 200, 100);
        this.button2 = new Button(300, 300, 200, 100);
    }

    private void loop() {
        while (!GLFW.glfwWindowShouldClose(window)) {

            GLFW.glfwPollEvents();
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

            GL11.glLoadIdentity();
            GL11.glOrtho(0, WIDTH, HEIGHT, 0, -1, 1);

            this.button1.update();
            this.button2.update();

            this.button1.render();
            this.button2.render();


            System.out.println(Mouse.getMouseX() + " " + Mouse.getMouseY());

            GLFW.glfwSwapBuffers(window);
        }
    }

    private void cleanup() {
        GLFW.glfwTerminate();
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }
}
