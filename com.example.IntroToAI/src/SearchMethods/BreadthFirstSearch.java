/*
 * Copyright (c) 2017.
 * Robyn Van Deventer - Developed at Swinburne University
 */

package SearchMethods;

import Environment.Square;
import java.util.ArrayList;

public class BreadthFirstSearch {

    Square originSquare;
    Square goalSquare;
    Square currentSquare;
    ArrayList<Square> visitedSquares = new ArrayList<>();
    ArrayList<Square> frontierSquares = new ArrayList<>();
    ArrayList<Square> allSquares = new ArrayList<>();
    boolean running = true;

    public BreadthFirstSearch() {

        allSquares = SearchManager.map.allSquares;
        setupSearch();

        while (running) {
            search();
        }
    }

    public void setupSearch() {

        for (Square square: allSquares) {
            if (square.isGoalPos()) {
                goalSquare = square;
            }

            if (square.isStartPos()) {
                originSquare = square;
            }
        }

        currentSquare = originSquare;
        visitedSquares.add(currentSquare);
        SearchManager.map.map.setSearchPath(currentSquare.toString());
    }

    public void search() {

        // Check if start position is occupied, can't search if it is
        if (originSquare.isOccupied()) {
            SearchManager.map.map.setSearchPath("Start position is occupied, exiting search.");
            running = false;
            return;
        }

        // Check if the current square is the goal state
        if (currentSquare == goalSquare) {
            SearchManager.map.map.setSearchPath("Goal position found at: " + currentSquare);
            running = false;
            return;
        }

        // Check if the square has children we can move to
        // End the program if not
        if (!currentSquare.hasChildren()) {
            SearchManager.map.map.setSearchPath("There are no children left to move to.");
            running = false;
            return;
        }
        else {

            // Move in the order of UP, LEFT, DOWN, RIGHT
            // Add the children to the frontier if they exist
            if (currentSquare.getTopChild() != null) {
                frontierSquares.add(currentSquare.getTopChild());
            }
            else if (currentSquare.getLeftChild() != null) {
                frontierSquares.add(currentSquare.getLeftChild());
            }
            else if (currentSquare.getBottomChild() != null) {
                frontierSquares.add(currentSquare.getBottomChild());
            }
            else if (currentSquare.getRightChild() != null) {
                frontierSquares.add(currentSquare.getRightChild());
            }
        }
    }
}
