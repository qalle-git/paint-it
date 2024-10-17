package Shapes;

import java.awt.*;

public class Rectangle extends Shape {
    public Rectangle(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Set the color for the circle
        g.setColor(this.color);

        // Draw a rectangle
        g.fillRect(this.x, this.y, this.width, this.height);

        System.out.println("Rectangle drawn at: " + this.getX() + ", " + this.getY() + ", " + this.getWidth() + ", " + this.getHeight());
    }
}
