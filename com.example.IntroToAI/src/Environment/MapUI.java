/*
 * Copyright (c) 2017.
 * Robyn Van Deventer - Developed at Swinburne University
 */

package Environment;

import javax.swing.*;
import java.awt.*;

public class MapUI extends JPanel {

    JFrame frame = new JFrame();

    public MapUI() {
        setupUserInterface();
    }

    public void setupUserInterface() {
        frame.setSize(600,600);
        frame.setBackground(Color.white);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
