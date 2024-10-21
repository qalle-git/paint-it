import Components.StatusBar;
import Shapes.Dot;
import Shapes.Oval;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class DrawingController {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Better Photoshop");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 700);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        DrawingModel model = new DrawingModel();
        DrawingView view = new DrawingView(model);
        DrawingController controller = new DrawingController(model, view);

        frame.add(view);

        frame.setVisible(true);
    }

    DrawingModel myModel;
    DrawingView myView;

    Shapes.Shape currentShape = null;
    Vector2 startMousePosition = new Vector2(0, 0);

    public DrawingController(DrawingModel model, DrawingView view) {
        this.myModel = model;
        this.myView = view;

        StatusBar statusBar = new StatusBar(
            this::colorButtonPressed,
            this::shapeButtonPressed,
            this::controlButtonPressed
        );

        this.myView.add(statusBar, BorderLayout.NORTH);

        this.myView.addMouseListener(
            new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
//                    System.out.println("Mouse clicked at: " + e.getX() + ", " + e.getY());
//
//                    Shape shape = null;
//
//                    if (currentShape.equals("dot")) {
//                        shape = new Dot(e.getX(), e.getY(), currentColor);
//                    }
//
//                    myModel.addShape(shape);
//                    myView.repaint();
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    if (myModel.currentShape.equals("dot")) {
                        Dot dot = new Dot(e.getX(), e.getY(), myModel.currentColor);

                        myModel.addShape(dot);
                        myView.repaint();

                        return;
                    }

                    startMousePosition = new Vector2(e.getX(), e.getY());

                    currentShape = null;

                    if (myModel.currentShape.equals("rect")) {
                        currentShape = new Shapes.Rectangle(0, 0, 0, 0, myModel.currentColor);
                    } else if (myModel.currentShape.equals("oval")) {
                        currentShape = new Oval(0, 0, 0, 0, myModel.currentColor);
                    }

                    myModel.addShape(currentShape);
                    myView.repaint();
                }

                @Override
                public void mouseReleased(MouseEvent e) {}

                @Override
                public void mouseEntered(MouseEvent e) {}

                @Override
                public void mouseExited(MouseEvent e) {}
            }
        );
        this.myView.addMouseMotionListener(
            new MouseMotionListener() {
                @Override
                public void mouseDragged(MouseEvent e) {
                    if (myModel.currentShape.equals("dot")) return;

                    int width = e.getX() - startMousePosition.x;
                    int height = e.getY() - startMousePosition.y;

                    if (width == 0 || height == 0) return;

                    Vector2 startPosition = new Vector2(startMousePosition.x, startMousePosition.y);

                    if (width < 0) {
                        startPosition.x = e.getX();
                    }

                    if (height < 0) {
                        startPosition.y = e.getY();
                    }

                    currentShape.setPosition(startPosition.x, startPosition.y);
                    currentShape.setWidth(Math.abs(width));
                    currentShape.setHeight(Math.abs(height));

                    myView.repaint();
                }

                @Override
                public void mouseMoved(MouseEvent e) {}
            }
        );

        this.myView.updateFooter();
    }

    public static final Map<String, Color> COLORS = new HashMap<>() {{
        put("black", Color.BLACK);
        put("red", Color.RED);
        put("green", Color.GREEN);
    }};

    private void colorButtonPressed(ActionEvent e) {
        String colorPressed = e.getActionCommand();

        if (colorPressed.equals("custom")) {
            Color newColor = JColorChooser.showDialog(this.myView, "Choose a color", this.myModel.getColor());

            if (newColor != null) {
                this.myModel.setColor(newColor);
                this.myView.updateFooter();
            }

            return;
        }


        this.myModel.setColor(COLORS.get(e.getActionCommand()));
        this.myView.updateFooter();
    }

    private void shapeButtonPressed(ActionEvent e) {
        this.myModel.setShapeType(e.getActionCommand());
        this.myView.updateFooter();
    }

    private void controlButtonPressed(ActionEvent e) {
        System.out.println("Control button pressed: " + e.getActionCommand());

        switch (e.getActionCommand()) {
            case "undo":
                myModel.removeLastShape();
                myView.repaint();

                break;
            case "save":
                saveFile();

                break;
            case "load":
                File loadFile = selectFileToLoad();

                if (loadFile == null) return;

                loadSelectedFile(loadFile);

                break;
            default:
                System.out.println("Unknown command: " + e.getActionCommand());

                break;
        }
    }

    private void saveFile() {
        String fileName = JOptionPane.showInputDialog("Give a file name:");

        if (fileName == null)
            return;

        try {
            FileOutputStream output = new FileOutputStream("src/Saves/" + fileName + ".txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(output);

            for (int shapeIndex = 0; shapeIndex < this.myModel.getShapeCount(); shapeIndex++) {
                objectOutputStream.writeObject(this.myModel.getShape(shapeIndex));
            }

            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (IOException e) {
            System.out.println("Write failed");
        }
    }


    /**
     * Opens a file chooser dialog to allow the user to select a file to load.
     * @return The file that the user selected to load, or null if the user canceled the operation, or no saves exist.
     */
    private File selectFileToLoad() {
        String specificFolderPath = "src/Saves";
        File specificFolder = new File(specificFolderPath);

        JFileChooser fileChooser = new JFileChooser();

        if (specificFolder.exists() && specificFolder.isDirectory()) { // make sure the folder exists, and the user actually has savesssss
            fileChooser.setCurrentDirectory(specificFolder);
        } else {
            System.out.println("The specified folder does not exist or is not a directory.");

            return null;
        }

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
        fileChooser.setFileFilter(filter);

        int returnValue = fileChooser.showOpenDialog(this.myView);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());

            return selectedFile;
        } else {
            System.out.println("File selection canceled.");
        }

        return null;
    }

    private void loadSelectedFile(File fileToLoad) {
        try {
            myModel.clear();

            FileInputStream input = new FileInputStream(fileToLoad);
            ObjectInputStream objectInputStream = new ObjectInputStream(input);

            while (true) {
                try {
                    Shapes.Shape shape = (Shapes.Shape) objectInputStream.readObject();

                    myModel.addShape(shape);
                } catch (ClassNotFoundException | EOFException e) {
                    break;
                }
            }

            myView.repaint();

            objectInputStream.close();
        } catch (Exception e) {
            System.out.println("Read failed");
        }
    }
}
