/*
 * Copyright (c) 2017.
 * Robyn Van Deventer - Developed at Swinburne University
 */

package SearchMethods;

import Environment.Square;
import java.util.ArrayList;
import java.util.Collections;

public class Search {

    Square originSquare;
    Square goalSquare;
    Square currentSquare;
    ArrayList<Square> visitedSquares = new ArrayList<>();
    ArrayList<Square> frontierSquares = new ArrayList<>();
    ArrayList<Square> allSquares = new ArrayList<>();
    boolean running = true;

    public Search() {

        allSquares = SearchManager.map.allSquares;
        setupSearch();
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

        if (SearchManager.searchMethod == SearchMethod.GBFS) {

            for (Square square: allSquares) {

                SearchManager.map.map.displayManhattanCost(square, String.valueOf(square.getManhattanDistance()));
            }
        }
        else if (SearchManager.searchMethod == SearchMethod.AS) for (Square square: allSquares) {

            String cost = square.getManhattanDistance() + " + " + square.getCostToNode();
            SearchManager.map.map.displayManhattanCost(square, cost);
        }
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

        // Check if any of the visited squares are in the frontier
        for (Square square: visitedSquares) {
            if (frontierSquares.contains(square)) {
                frontierSquares.remove(square);
            }
        }
    }
}
