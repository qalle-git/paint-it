package Components;

import javax.swing.*;
import java.awt.*;

public class Footer extends JPanel {
    JLabel footerText = null;

    public Footer() {
        footerText = new JLabel("");

        this.add(footerText);
    }

    public void setText(String text) {
        footerText.setText(text);
    }
}
