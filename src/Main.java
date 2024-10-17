import javax.swing.*;

public class Main {
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
}