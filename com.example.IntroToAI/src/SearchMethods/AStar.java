/*
 * Copyright (c) 2017.
 * Robyn Van Deventer - Developed at Swinburne University
 */

package SearchMethods;

import Environment.Square;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class AStar extends Search {

    public AStar() {

        super();

        while (running) {
            search();
        }
    }

    public void search() {

        super.search();

        if (currentSquare.equals(originSquare) && (frontierSquares.size() > 0)) {
            currentSquare = frontierSquares.get(0);
            frontierSquares.remove(0);
            SearchManager.map.map.setSearchPath(currentSquare.toString() + " Cost: " + currentSquare.getManhattanDistance() + " + " + currentSquare.getCostToNode());
            SearchManager.map.map.colourSearchedLabels(currentSquare);
            System.out.println(this.getClass() + "-" + currentSquare);
        }
        else if (frontierSquares.size() > 0) {

            Collections.sort(frontierSquares, new Comparator<Square>() {
                @Override
                public int compare(Square o1, Square o2) {
                    return Integer.compare(o2.getCombinedCost(), o1.getCombinedCost());
                }
            });

            currentSquare = frontierSquares.get(0);
            frontierSquares.remove(0);
            SearchManager.map.map.setSearchPath(currentSquare.toString() + " Cost: " + currentSquare.getManhattanDistance() + " + " + currentSquare.getCostToNode());
            SearchManager.map.map.colourSearchedLabels(currentSquare);
            System.out.println(this.getClass() + "-" + currentSquare);
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

            // Find the lowest cost move
            ArrayList<Square> lowestCost = orderLowestCost();

            // Remove the squares we aren't going to use
            for (Iterator<Square> it = lowestCost.iterator(); it.hasNext(); ) {
                Square square = it.next();
                if ((visitedSquares.contains(square)) || (square.isOccupied())) {
                    it.remove();
                }
            }

            for (Square square: lowestCost) {
                frontierSquares.add(square);
            }
        }
    }

    private ArrayList<Square> orderLowestCost() {

        Square topSquare = currentSquare.getTopChild();
        Square leftSquare = currentSquare.getLeftChild();
        Square bottomSquare = currentSquare.getBottomChild();
        Square rightSquare = currentSquare.getRightChild();

        ArrayList<Square> orderedSquares = new ArrayList<>();

        if (topSquare != null) {
            orderedSquares.add(topSquare);
        }
        if (leftSquare != null) {
            orderedSquares.add(leftSquare);
        }
        if (bottomSquare != null) {
            orderedSquares.add(bottomSquare);
        }
        if (rightSquare != null) {
            orderedSquares.add(rightSquare);
        }

        // Implement a comparator to order the squares by manhattan distance
        Collections.sort(orderedSquares, new Comparator<Square>() {
            @Override
            public int compare(Square square2, Square square1)
            {
                if ((square1 == null) || (square2 == null)) { return 0; }
                return  Integer.compare(square2.getCombinedCost(), square1.getCombinedCost());
            }
        });

        return orderedSquares;
    }
}
