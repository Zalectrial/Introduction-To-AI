/*
 * Copyright (c) 2017.
 * Robyn Van Deventer - Developed at Swinburne University
 */

package Environment;

import java.util.ArrayList;

public class Map {

    static int[] startPos = new int[2];
    int[] goalPos = new int[2];
    int[] dimensions = new int[2];
    int[][] occupiedPos;
    public ArrayList<Square> allSquares = new ArrayList<>();
    public MapUI map = new MapUI();

    public Map(int[] startPos, int[] goalPos, int[] dimensions, int[][] occupiedPos) {

        this.startPos = startPos;
        this.goalPos = goalPos;
        this.dimensions = dimensions;
        this.occupiedPos = occupiedPos;

        map.setupUserInterface(dimensions[0], dimensions[1]);
        generateSquares(dimensions);
        setChildSquares();
        setRootNode();
        setStartSquare(startPos);
        setGoalSquare(goalPos);
        setOccupiedSquares(occupiedPos);
    }

    public void generateSquares(int[] dimensions) {

        for (int row = 0; row < dimensions[0]; row++) {
            for (int col = 0; col < dimensions[1]; col++) {
                Square square = new Square(col, row);
                allSquares.add(square);
            }
        }
    }

    public void setChildSquares() {

        for (Square square: allSquares) {
            Square left = new Square(square.x, square.y + 1);
            Square right = new Square(square.x + 1, square.y);

            if (allSquares.contains(left)) {
                square.setLeftChild(left);
                square.parentNodes.add(square);
            }
            if (allSquares.contains(right)) {
                square.setRightChild(right);
                square.parentNodes.add(square);
            }
        }
    }

    public void setRootNode() {

        for (Square square: allSquares) {
           if ((square.x == 0) && (square.y == 0)) {
               square.setRootNode(true);
           }
        }
    }

    public void setStartSquare(int[] startPos) {

        for (Square square: allSquares) {
            if (square.x == startPos[0] && square.y == startPos[1]) {
                square.setStartPos(true);
                map.colorStartPosLabel(square);
            }
        }
    }

    public void setGoalSquare(int[] goalPos) {

        for (Square square: allSquares) {
            if (square.x == goalPos[0] && square.y == goalPos[1]) {
                square.setGoalPos(true);
                map.colorGoalPosLabel(square);
            }
        }
    }

    public void setOccupiedSquares(int[][] occupiedPos) {

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

        map.colorOccupiedLabels(occupied);
    }
}
