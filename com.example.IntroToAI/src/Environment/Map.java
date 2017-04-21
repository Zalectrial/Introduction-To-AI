/*
 * Copyright (c) 2017.
 * Robyn Van Deventer - Developed at Swinburne University
 */

package Environment;

import java.util.ArrayList;

public class Map {

    private static int[] startPos = new int[2];
    private int[] goalPos = new int[2];
    public ArrayList<Square> allSquares = new ArrayList<>();
    public MapUI map = new MapUI();

    Map(int[] startPos, int[] goalPos, int[] dimensions, int[][] occupiedPos) {

        this.startPos = startPos;
        this.goalPos = goalPos;

        map.setupUserInterface(dimensions[0], dimensions[1]);
        generateSquares(dimensions);
        setChildSquares();
        setStartSquare(startPos);
        setGoalSquare(goalPos);
        setOccupiedSquares(occupiedPos);
        calculateManhattanCost();
        calculateCostToNode();
    }

    // Generate all the squares needed to fill the NxM grid
    private void generateSquares(int[] dimensions) {

        for (int row = 0; row < dimensions[0]; row++) {
            for (int col = 0; col < dimensions[1]; col++) {
                Square square = new Square(col, row);
                allSquares.add(square);
            }
        }
    }

    // For every square, find if it has children and assign them to the correct property
    private void setChildSquares() {

        for (Square square: allSquares) {
            Square left = new Square(square.x - 1, square.y);
            Square right = new Square(square.x + 1, square.y);
            Square top = new Square(square.x, square.y - 1);
            Square bottom = new Square(square.x, square.y + 1);

            for (Square child: allSquares) {
                if (child.equals(left)) {
                    square.setLeftChild(child);
                }
                if (child.equals(right)) {
                    square.setRightChild(child);
                }
                if (child.equals(top)) {
                    square.setTopChild(child);
                }
                if (child.equals(bottom)) {
                    square.setBottomChild(child);
                }
            }
        }
    }

    // Find the start square and set it as start position = true
    private void setStartSquare(int[] startPos) {

        for (Square square: allSquares) {
            if (square.x == startPos[0] && square.y == startPos[1]) {
                square.setStartPos(true);
                map.colourStartPosLabel(square);
            }
        }
    }

    // Find the goal square and set it as goal position = true
    private void setGoalSquare(int[] goalPos) {

        for (Square square: allSquares) {
            if (square.x == goalPos[0] && square.y == goalPos[1]) {
                square.setGoalPos(true);
                map.colourGoalPosLabel(square);
            }
        }
    }

    // Find the coordinates of every occupied square
    // For each of these squares, set the occupied property to true
    private void setOccupiedSquares(int[][] occupiedPos) {

        ArrayList<Square> occupied = new ArrayList<>();

        for (int i = 0; i < occupiedPos.length; i++) {
            for (int j = 0; j < occupiedPos[i].length; j++) {
                Square square = new Square(occupiedPos[i][0], occupiedPos[i][1]);
                occupied.add(square);

                if (occupiedPos[i][2] == 1 && occupiedPos[i][3] == 1) { continue; }
                else {
                    for (int x = square.x; x < square.x + occupiedPos[i][2]; x++) {
                        for (int y = square.y; y < square.y + occupiedPos[i][3]; y++) {
                            Square temp = new Square(x, y);
                            occupied.add(temp);
                        }
                    }
                }
            }
        }

        for (Square square: allSquares) {
            for (Square occupiedSquares: occupied) {

                if (square.x == occupiedSquares.x && square.y == occupiedSquares.y) {
                    square.setOccupied(true);
                }
            }

        }

        map.colourOccupiedLabels(occupied);
    }

    // For every square, calculate its Manhattan distance to the goal square
    private void calculateManhattanCost() {

        int distance;

        for (Square square: allSquares) {

            if ((goalPos[0] == square.x) && (goalPos[1] == square.y)) {
                distance = 0;
            }
            else if (goalPos[0] == square.x) {
                distance = Math.abs(goalPos[1] - square.y);
            }
            else if (goalPos[1] == square.y) {
                distance = Math.abs(square.x - goalPos[0]);
            }
            else {
                distance = Math.abs((goalPos[0] - square.x)) + Math.abs((goalPos[1] - square.y));
            }

            square.setManhattanDistance(Math.abs(distance));
        }
    }

    // For every square calculate its cost to reach from the start square
    private void calculateCostToNode() {

        int distance;

        for (Square square: allSquares) {

            if ((startPos[0] == square.x) && (startPos[1] == square.y)) {
                distance = 0;
            }
            else if (startPos[0] == square.x) {
                distance = Math.abs(startPos[1] - square.y);
            }
            else if (startPos[1] == square.y) {
                distance = Math.abs(square.x - startPos[0]);
            }
            else {
                distance = Math.abs((startPos[0] - square.x)) + Math.abs((startPos[1] - square.y));
            }

            square.setCostToNode(Math.abs(distance));
        }
    }
}
