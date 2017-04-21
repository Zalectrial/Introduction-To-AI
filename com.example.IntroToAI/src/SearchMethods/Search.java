/*
 * Copyright (c) 2017.
 * Robyn Van Deventer - Developed at Swinburne University
 */

package SearchMethods;

import Environment.Square;
import java.util.ArrayList;

public class Search {

    Square originSquare;
    Square goalSquare;
    Square currentSquare;
    ArrayList<Square> visitedSquares = new ArrayList<>();
    ArrayList<Square> frontierSquares = new ArrayList<>();
    private ArrayList<Square> allSquares = new ArrayList<>();
    boolean running = true;

    Search() {

        allSquares = SearchManager.map.allSquares;
        setupSearch();
    }

    // Gets the program ready for searching
    private void setupSearch() {

        // Sets some handy variables of the start and goal positions to be used during searching
        for (Square square: allSquares) {
            if (square.isGoalPos()) {
                goalSquare = square;
            }

            if (square.isStartPos()) {
                originSquare = square;
            }
        }

        // Sets the current square to be the start of the search
        // Updates the search path on the UI
        currentSquare = originSquare;
        SearchManager.map.map.setSearchPath(currentSquare.toString());

        // If the search type is GFBS, display Manhattan costs
        if (SearchManager.searchMethod == SearchMethod.GBFS) {

            for (Square square: allSquares) {

                if (!square.isOccupied()) {
                    SearchManager.map.map.displayManhattanCost(square, String.valueOf(square.getManhattanDistance()));
                }
            }
        }
        // If the search type is A* display the Manhattan costs and cost to node
        else if (SearchManager.searchMethod == SearchMethod.AS) for (Square square: allSquares) {

            if (!square.isOccupied()) {
                String cost = square.getManhattanDistance() + " + " + square.getCostToNode();
                SearchManager.map.map.displayManhattanCost(square, cost);
            }
        }
    }

    public void search() {

        // Check if start position is occupied, can't search if it is
        if (originSquare.isOccupied()) {
            SearchManager.map.map.setSearchPath("Start position is occupied, exiting search.");
            System.out.println("Start position is occupied, exiting search.");
            running = false;
            return;
        }

        // Check if there are squares in the frontier
        if ((frontierSquares.size() == 0) && (currentSquare != originSquare)) {
            SearchManager.map.map.setSearchPath("There are no more squares to search in the frontier.");
            System.out.println("There are no more squares to search in the frontier.");
            running = false;
            return;
        }

        // Add the current square to visited
        visitedSquares.add(currentSquare);

        // Check if any of the visited squares are in the frontier and remove them if they are
        for (Square square: visitedSquares) {
            if (frontierSquares.contains(square)) {
                frontierSquares.remove(square);
            }
        }
    }
}
