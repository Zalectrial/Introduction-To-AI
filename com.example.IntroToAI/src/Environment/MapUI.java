/*
 * Copyright (c) 2017.
 * Robyn Van Deventer - Developed at Swinburne University
 */

package Environment;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MapUI extends JPanel {

    JFrame frame = new JFrame();
    JLabel[][] fieldGrid;

    public void setupUserInterface(int row, int col) {

        fieldGrid = new JLabel[row][col];

        JPanel panel = new JPanel(new GridLayout(row, col, 1, 1));
        panel.setBackground(Color.black);

        for (int i = 0; i < fieldGrid.length; i++) {
            for (int j = 0; j < fieldGrid[i].length; j++) {
                fieldGrid[i][j] = createLabel(j, i);
                panel.add(fieldGrid[i][j]);
            }
        }

        frame.setSize(col*50,row*50);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Robot Navigation Problem");
        frame.add(panel);
        frame.setVisible(true);
    }

    private JLabel createLabel(int row, int col) {
        JLabel label = new JLabel(row + "," + col);
        label.setBackground(Color.white);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setOpaque(true);
        return label;
    }

    public void colorStartPosLabel(Square square) {

        for (int i = 0; i < fieldGrid.length; i++) {
            for (int j = 0; j < fieldGrid[i].length; j++) {
                JLabel label = fieldGrid[i][j];

                if (label.getText().equals(square.toString())) {
                    label.setBackground(Color.red);
                }
            }
        }
    }

    public void colorGoalPosLabel(Square square) {

        for (int i = 0; i < fieldGrid.length; i++) {
            for (int j = 0; j < fieldGrid[i].length; j++) {
                JLabel label = fieldGrid[i][j];

                if (label.getText().equals(square.toString())) {
                    label.setBackground(Color.green);
                }
            }
        }
    }

    public void colorOccupiedLabels(ArrayList<Square> occupied) {

        for (int i = 0; i < fieldGrid.length; i++) {
            for (int j = 0; j < fieldGrid[i].length; j++) {
                JLabel label = fieldGrid[i][j];

                for (Square square: occupied) {
                    if (label.getText().equals(square.toString())) {
                        label.setBackground(Color.gray);
                    }
                }
            }
        }

    }
}
