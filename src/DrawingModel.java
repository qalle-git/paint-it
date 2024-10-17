import Shapes.Shape;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DrawingModel {
    private List<Shape> shapes = new ArrayList<>();

    Color currentColor = Color.BLACK;
    String currentShape = "dot";

    public DrawingModel() {
    }

    public void addShape(Shape shape) {
        shapes.add(shape);
    }

    public void removeLastShape() {
        int shapeCount = this.getShapeCount();

        if (shapeCount <= 0) return;

        shapes.remove(shapeCount - 1);
    }

    public int getShapeCount() {
        return shapes.size();
    }

    public Shape getShape(int index)
    {
        return shapes.get(index);
    }

    public void setShapeType(String shape) {
        currentShape = shape;
    }

    public void setColor(Color color) {
        currentColor = color;
    }

    public String getShapeType() {
        return currentShape;
    }

    public Color getColor() {
        return currentColor;
    }

    public void clear() {
        shapes.clear();
    }
}
