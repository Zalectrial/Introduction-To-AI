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
    JLabel searchType = new JLabel();
    JLabel searchPath = new JLabel();

    public void setupUserInterface(int row, int col) {

        fieldGrid = new JLabel[row][col];

        JPanel panel = new JPanel(new FlowLayout());

        JPanel map = new JPanel(new GridLayout(row, col, 1, 1));
        map.setSize(col*50, row*50);
        map.setBackground(Color.black);

        for (int i = 0; i < fieldGrid.length; i++) {
            for (int j = 0; j < fieldGrid[i].length; j++) {
                fieldGrid[i][j] = createLabel(j, i);
                map.add(fieldGrid[i][j]);
            }
        }

        JPanel searchInformation = new JPanel();
        searchInformation.setLayout(new BoxLayout(searchInformation, BoxLayout.Y_AXIS));
        searchInformation.add(new JLabel("SEARCH INFORMATION"));
        searchInformation.add(new JLabel("Search Method:"));
        searchInformation.add(searchType);
        searchInformation.add(new JLabel("Path Progress:"));

        searchPath.setSize(100, 500);
        JScrollPane scroller = new JScrollPane(searchPath, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroller.setPreferredSize(new Dimension(100, 500));
        searchInformation.add(scroller);

        panel.add(map);
        panel.add(searchInformation);

        frame.setSize(col*100,row*100);
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

    public void displayManhattanCost(Square square, int cost) {

        for (int i = 0; i < fieldGrid.length; i++) {
            for (int j = 0; j < fieldGrid[i].length; j++) {
                JLabel label = fieldGrid[i][j];

                if (label.getText().equals(square.toString())) {
                    label.setText("<html>" + label.getText() + "<br>" + cost + "</html>");
                }
            }
        }
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

    public void colourSearchedLabels(Square square) {

        JLabel label = fieldGrid[square.y][square.x];
        label.setBackground(Color.cyan);
    }

    public void setSearchType(String searchType) {
        this.searchType.setText(searchType);
    }

    public void setSearchPath(String searchPath) {

        this.searchPath.setText("<html>" + this.searchPath.getText() + "<br>" + searchPath + "<html>");
    }
}
