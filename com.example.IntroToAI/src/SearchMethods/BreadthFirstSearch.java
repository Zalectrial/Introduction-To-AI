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

    public BreadthFirstSearch() {

        allSquares = SearchManager.map.allSquares;
        setupSearch();
        search();
    }

    public void setupSearch() {

        for (Square square: allSquares) {
            if (square.isGoalPos()) {
                goalSquare = square;
            }

            if (square.isStartPos()) {
                originSquare = square;
            }

            currentSquare = originSquare;
            visitedSquares.add(currentSquare);
            SearchManager.map.map.setSearchPath(currentSquare.toString());
        }
    }

    public void search() {


    }
}
