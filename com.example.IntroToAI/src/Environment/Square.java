/*
 * Copyright (c) 2017.
 * Robyn Van Deventer - Developed at Swinburne University
 */

package Environment;

public class Square {

    int x;
    int y;
    private boolean occupied = false;
    private boolean goalPos = false;
    private boolean startPos = false;
    private Square leftChild;
    private Square rightChild;
    private Square topChild;
    private Square bottomChild;
    private int manhattanDistance;
    private int costToNode;

    Square(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isOccupied() {
        return this.occupied;
    }

    void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public boolean isGoalPos() {
        return this.goalPos;
    }

    void setGoalPos(boolean goalPos) {
        this.goalPos = goalPos;
    }

    public boolean isStartPos() {
        return this.startPos;
    }

    void setStartPos(boolean startPos) {
        this.startPos = startPos;
    }

    public boolean hasChildren() {
        return ((leftChild != null) || (rightChild != null) || (topChild != null) || (bottomChild != null));
    }

    public Square getLeftChild() {
        return leftChild;
    }

    void setLeftChild(Square leftChild) {
        this.leftChild = leftChild;
    }

    public Square getRightChild() {
        return rightChild;
    }

    void setRightChild(Square rightChild) {
        this.rightChild = rightChild;
    }

    public Square getTopChild() {
        return this.topChild;
    }

    void setTopChild(Square topChild) {
        this.topChild = topChild;
    }

    public Square getBottomChild() {
        return this.bottomChild;
    }

    void setBottomChild(Square bottomChild) {
        this.bottomChild = bottomChild;
    }

    public String toString() {
        return this.x + "," + this.y;
    }

    public int getManhattanDistance() {

        return this.manhattanDistance;
    }

    void setManhattanDistance(int manhattanDistance) {

        this.manhattanDistance = manhattanDistance;
    }

    public int getCostToNode() {
        return this.costToNode;
    }

    void setCostToNode(int costToNode) {
        this.costToNode = costToNode;
    }

    public int getCombinedCost() {
        return this.costToNode + this.manhattanDistance;
    }

    @Override
    // Override the equals method in order to compare squares only by their x and y values
    public boolean equals(Object obj) {

        if (this == obj) return true;
        if (!(obj instanceof Square)) return false;

        Square square = (Square) obj;

        return this.x == square.x &&
                this.y == square.y;
    }

    @Override
    // Override hashChode method as it is required when you override equals
    public int hashCode() {

        int result = 0;
        result = 31*result + x;
        result = 31*result + y;

        return result;
    }
}
