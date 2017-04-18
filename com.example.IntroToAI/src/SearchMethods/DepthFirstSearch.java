/*
 * Copyright (c) 2017.
 * Robyn Van Deventer - Developed at Swinburne University
 */

package SearchMethods;

import Environment.Square;

import java.util.ArrayList;

public class DepthFirstSearch {

    Square originSquare;
    Square goalSquare;
    Square currentSquare;
    ArrayList<Square> visitedSquares = new ArrayList<>();
    ArrayList<Square> frontierSquares = new ArrayList<>();
    ArrayList<Square> allSquares = new ArrayList<>();
    boolean running = true;

    public DepthFirstSearch() {

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
        SearchManager.map.map.setSearchPath(currentSquare.toString());
    }

    public void search() {

        // Check if start position is occupied, can't search if it is
        if (originSquare.isOccupied()) {
            SearchManager.map.map.setSearchPath("Start position is occupied, exiting search.");
            running = false;
            return;
        }

        // Check if there are squares in the frontier
        if ((frontierSquares.size() == 0) && (currentSquare != originSquare)) {
            SearchManager.map.map.setSearchPath("There are no more squares to search in the frontier.");
            running = false;
            return;
        }

        // Add the current square to visited
        // Assign the next frontier square to the current square
        visitedSquares.add(currentSquare);

        if (currentSquare.equals(originSquare) && (frontierSquares.size() > 0)) {
            currentSquare = frontierSquares.get(frontierSquares.size() - 1);
            frontierSquares.remove(frontierSquares.size() - 1);
            SearchManager.map.map.setSearchPath(currentSquare.toString());
            System.out.println(this.getClass() + "-" + currentSquare);
        }
        else if (frontierSquares.size() > 0) {
            currentSquare = frontierSquares.get(frontierSquares.size() - 1);
            frontierSquares.remove(frontierSquares.size() - 1);
            SearchManager.map.map.setSearchPath(currentSquare.toString());
            SearchManager.map.map.colourSearchedLabels(currentSquare);
            System.out.println(this.getClass() + "-" + currentSquare);
        }

        // Check if any of the visited squares are in the frontier
        for (Square square: visitedSquares) {
            if (frontierSquares.contains(square)) {
                frontierSquares.remove(square);
            }
        }

        // Check if the current square is the goal state
        if (currentSquare.equals(goalSquare)) {
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
            // Add the children if they haven't been searched already
            // Add the children if they aren't occupied
            if ((currentSquare.getRightChild() != null) && !(visitedSquares.contains(currentSquare.getRightChild())) && (!currentSquare.getRightChild().isOccupied())) {
                frontierSquares.add(currentSquare.getRightChild());
            }
            if ((currentSquare.getBottomChild() != null) && !(visitedSquares.contains(currentSquare.getBottomChild())) && (!currentSquare.getBottomChild().isOccupied())) {
                frontierSquares.add(currentSquare.getBottomChild());
            }
            if ((currentSquare.getLeftChild() != null) && !(visitedSquares.contains(currentSquare.getLeftChild())) && (!currentSquare.getLeftChild().isOccupied())) {
                frontierSquares.add(currentSquare.getLeftChild());
            }
            if ((currentSquare.getTopChild() != null) && !(visitedSquares.contains(currentSquare.getTopChild())) && (!currentSquare.getTopChild().isOccupied())) {
                frontierSquares.add(currentSquare.getTopChild());
            }
        }
    }
}
