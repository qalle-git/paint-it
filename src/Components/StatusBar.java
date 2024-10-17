package Components;

import Listeners.ColorListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class StatusBar extends JPanel {
    static final String[] colors = {"black", "red", "green", "custom"};
    static final String[] shapes = {"dot", "oval", "rect"};
    static final String[] buttons = {"undo", "save", "load"};

    public StatusBar(ActionListener colorListener, ActionListener shapeListener, ActionListener controlListener) {
        this.setLayout(new FlowLayout());

        for (String color : colors) {
            JButton jButton = new JButton(color);
            jButton.addActionListener(colorListener);
            this.add(jButton);
        }

        this.add(new JSeparator(SwingConstants.VERTICAL)); // Add a vertical separator

        for (String shape : shapes) {
            JButton jButton = new JButton(shape);
            jButton.addActionListener(shapeListener);
            this.add(jButton);
        }

        this.add(new JSeparator(SwingConstants.VERTICAL)); // Add a vertical separator

        for (String button : buttons) {
            JButton jButton = new JButton(button);
            jButton.addActionListener(controlListener);
            this.add(jButton);
        }
    }
}
