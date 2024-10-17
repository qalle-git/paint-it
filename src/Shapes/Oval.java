package Shapes;

import java.awt.*;

public class Oval extends Shape {
    public Oval(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Set the color for the circle
        g.setColor(this.color);

        System.out.println("Oval drawn at: " + this.x + ", " + this.y + ", " + this.width + ", " + this.height);

        // Draw an oval
        g.fillOval(this.x, this.y, this.width, this.height); // (x, y, width, height)
    }
}
