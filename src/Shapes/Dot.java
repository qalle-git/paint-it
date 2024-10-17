package Shapes;

import java.awt.*;
import javax.swing.*;

public class Dot extends Shape {
    private static final int DIAMETER = 10;

    public Dot(int x, int y, Color color) {
        super(x, y, DIAMETER, DIAMETER, color);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Set the color for the circle
        g.setColor(this.color);

        // Draw a circle
        int diameter = DIAMETER; // Diameter of the circle
        g.fillOval(this.x - diameter / 2, this.y - diameter / 2, diameter, diameter); // (x, y, width, height)
    }
}
