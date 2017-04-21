/*
 * Copyright (c) 2017.
 * Robyn Van Deventer - Developed at Swinburne University
 */

package SearchMethods;

public class BreadthFirstSearch extends Search {

    BreadthFirstSearch() {

        super();

        while (running) {
            search();
        }
    }

    public void search() {

        // Call the parent search method
        super.search();

        // If we have a frontier and we haven't searched anything yet, do this
        // Get the first object in the frontier and assign it to the current square
        // Remove this square from the frontier
        if (currentSquare.equals(originSquare) && (frontierSquares.size() > 0)) {
            currentSquare = frontierSquares.get(0);
            frontierSquares.remove(0);
            SearchManager.map.map.setSearchPath(currentSquare.toString());
            SearchManager.map.map.colourSearchedLabels(currentSquare);
            System.out.println(currentSquare);
        }
        // If we have searched already and we have a frontier, do this
        // Get the first object from the frontier and assign it to the current square
        // Remove this square from the frontier
        else if (frontierSquares.size() > 0) {
            currentSquare = frontierSquares.get(0);
            frontierSquares.remove(0);
            SearchManager.map.map.setSearchPath(currentSquare.toString());
            SearchManager.map.map.colourSearchedLabels(currentSquare);
            System.out.println(currentSquare);
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
            if ((currentSquare.getTopChild() != null) && !(visitedSquares.contains(currentSquare.getTopChild())) && (!currentSquare.getTopChild().isOccupied())) {
                frontierSquares.add(currentSquare.getTopChild());
            }
            if ((currentSquare.getLeftChild() != null) && !(visitedSquares.contains(currentSquare.getLeftChild())) && (!currentSquare.getLeftChild().isOccupied())) {
                frontierSquares.add(currentSquare.getLeftChild());
            }
            if ((currentSquare.getBottomChild() != null) && !(visitedSquares.contains(currentSquare.getBottomChild())) && (!currentSquare.getBottomChild().isOccupied())) {
                frontierSquares.add(currentSquare.getBottomChild());
            }
            if ((currentSquare.getRightChild() != null) && !(visitedSquares.contains(currentSquare.getRightChild())) && (!currentSquare.getRightChild().isOccupied())) {
                frontierSquares.add(currentSquare.getRightChild());
            }
        }
    }
}
