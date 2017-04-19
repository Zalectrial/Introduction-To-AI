/*
 * Copyright (c) 2017.
 * Robyn Van Deventer - Developed at Swinburne University
 */

package SearchMethods;

public class DepthFirstSearch extends Search {

    public DepthFirstSearch() {

        super();

        while (running) {
            search();
        }
    }

    public void search() {

        super.search();

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
