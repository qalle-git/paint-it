package Listeners;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class ColorListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
    }
}
