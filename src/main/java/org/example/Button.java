package org.example;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

public class Button {
    private double x;
    private double y;
    private double width;
    private double height;
    private boolean isHovered;
    private boolean isPressed;

    public Button(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.isHovered = false;
        this.isPressed = false;
    }

    public void update() {
        double mouseX = Mouse.getMouseX();
        double mouseY = Mouse.getMouseY();

        if (mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height) {
            isHovered = true;

            if (Mouse.isButtonDown(GLFW.GLFW_MOUSE_BUTTON_LEFT)) {
                isPressed = true;
            } else {
                isPressed = false;
            }
        } else {
            isHovered = false;
            isPressed = false;
        }
    }

    public void render() {
        drawRectangle();

        if (isPressed) {
            GL11.glColor3f(0.2f, 0.8f, 0.2f);  // Set color for pressed state
        } else if (isHovered) {
            GL11.glColor3f(0.4f, 0.6f, 0.8f);  // Set color for hovered state
        } else {
            GL11.glColor3f(0.6f, 0.6f, 0.6f);  // Set color for default state
        }
    }

    public boolean isHovered() {
        return isHovered;
    }

    public boolean isPressed() {
        return isPressed;
    }

    private void drawRectangle() {
        GL11.glBegin(GL11.GL_QUADS);

        // Specify the vertices of the rectangle
        GL11.glVertex2d(x, y);
        GL11.glVertex2d(x + width, y);
        GL11.glVertex2d(x + width, y + height);
        GL11.glVertex2d(x, y + height);

        GL11.glEnd();
    }
}
