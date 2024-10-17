package Shapes;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class Shape extends JPanel implements Serializable {
    int x;
    int y;

    int width;
    int height;

    Color color;

    public Shape(int x, int y, int width, int height, Color color) {
        this.x = x;
        this.y = y;

        this.width = width;
        this.height = height;

        this.color = color;
    }

    public void draw(Graphics g) {
        this.paintComponent(g);
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
