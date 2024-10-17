import Components.Footer;
import Components.StatusBar;
import Shapes.Dot;
import Shapes.Shape;

import javax.swing.*;
import java.awt.*;

public class DrawingView extends JPanel {
    private final DrawingModel model;

    private final Footer footer;

    public DrawingView(DrawingModel model) {
        this.model = model;

        this.setBackground(Color.WHITE);
        this.setLayout(new BorderLayout());

        this.footer = new Footer();

        this.add(footer, BorderLayout.SOUTH);
    }

    public void updateFooter() {
        this.footer.setText("Mode: " + model.getShapeType() + " | Color: " + model.getColor());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        System.out.println("Drawing " + model.getShapeCount() + " shapes");

        for (int shapeIndex = 0; shapeIndex < model.getShapeCount(); shapeIndex++) {
            Shape shape = model.getShape(shapeIndex);

            if (shape != null) {
                shape.draw(g);
            }
        }
    }
}
