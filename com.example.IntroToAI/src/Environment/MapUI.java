/*
 * Copyright (c) 2017.
 * Robyn Van Deventer - Developed at Swinburne University
 */

package Environment;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MapUI extends JPanel {

    private JFrame frame = new JFrame();
    private JLabel[][] fieldGrid;
    private JLabel searchType = new JLabel();
    private JLabel searchPath = new JLabel();

    // Sets up the UI
    void setupUserInterface(int row, int col) {

        fieldGrid = new JLabel[row][col];

        JPanel panel = new JPanel(new FlowLayout());

        // Give a generic size to the JPanel
        JPanel map = new JPanel(new GridLayout(row, col, 1, 1));
        map.setSize(col*50, row*50);
        map.setBackground(Color.black);

        // Create a label for each square in the grid and add to nested array
        // Add the squares to a JPanel
        for (int i = 0; i < fieldGrid.length; i++) {
            for (int j = 0; j < fieldGrid[i].length; j++) {
                fieldGrid[i][j] = createLabel(j, i);
                map.add(fieldGrid[i][j]);
            }
        }

        // A JPanel for the purpose of displaying search information
        // Shows search type and the path progress
        JPanel searchInformation = new JPanel();
        searchInformation.setLayout(new BoxLayout(searchInformation, BoxLayout.Y_AXIS));
        searchInformation.add(new JLabel("SEARCH INFORMATION"));
        searchInformation.add(new JLabel("Search Method:"));
        searchInformation.add(searchType);
        searchInformation.add(new JLabel("Path Progress:"));

        // Add a scrollbar to the path progress
        searchPath.setSize(100, 500);
        JScrollPane scroller = new JScrollPane(searchPath, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroller.setPreferredSize(new Dimension(100, 500));
        searchInformation.add(scroller);

        // Add the JPanels to another panel
        panel.add(map);
        panel.add(searchInformation);

        // Set the window size and add the content
        frame.setSize(col*100,row*100);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("Robot Navigation Problem");
        frame.add(panel);
        frame.setVisible(true);
    }

    // Create each square label with the coordinates inside
    private JLabel createLabel(int row, int col) {
        JLabel label = new JLabel(row + "," + col);
        label.setBackground(Color.white);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setOpaque(true);
        return label;
    }

    // Display the Manhattan cost inside the label as well as the coordinates
    public void displayManhattanCost(Square square, String cost) {

        for (int i = 0; i < fieldGrid.length; i++) {
            for (int j = 0; j < fieldGrid[i].length; j++) {
                JLabel label = fieldGrid[i][j];

                if (label.getText().equals(square.toString())) {
                    label.setText("<html>" + label.getText() + "<br>" + cost + "</html>");
                }
            }
        }
    }

    // Colours the start position on the grid
    void colourStartPosLabel(Square square) {

        for (int i = 0; i < fieldGrid.length; i++) {
            for (int j = 0; j < fieldGrid[i].length; j++) {
                JLabel label = fieldGrid[i][j];

                if (label.getText().equals(square.toString())) {
                    label.setBackground(Color.red);
                }
            }
        }
    }

    // Colours the goal position on the grid
    void colourGoalPosLabel(Square square) {

        for (int i = 0; i < fieldGrid.length; i++) {
            for (int j = 0; j < fieldGrid[i].length; j++) {
                JLabel label = fieldGrid[i][j];

                if (label.getText().equals(square.toString())) {
                    label.setBackground(Color.green);
                }
            }
        }
    }

    // Colour all of the occupied spaces
    void colourOccupiedLabels(ArrayList<Square> occupied) {

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

    // Colour the expanded squares so that user can see where has been sarched
    public void colourSearchedLabels(Square square) {

        JLabel label = fieldGrid[square.y][square.x];
        label.setBackground(Color.cyan);
    }

    // Sets the search type
    public void setSearchType(String searchType) {
        this.searchType.setText(searchType);
    }

    // Updates the search path as the searches take place
    public void setSearchPath(String searchPath) {

        this.searchPath.setText("<html>" + this.searchPath.getText() + "<br>" + searchPath + "<html>");
    }
}
