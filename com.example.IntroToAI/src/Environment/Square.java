/*
 * Copyright (c) 2017.
 * Robyn Van Deventer - Developed at Swinburne University
 */

package Environment;

import java.util.ArrayList;

public class Square {

    int x;
    int y;
    private boolean rootNode = false;
    private boolean occupied = false;
    private boolean goalPos = false;
    private boolean startPos = false;
    private Square leftChild;
    private Square rightChild;
    ArrayList<Square> parentNodes = new ArrayList<>();

    public Square(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isrootNode() {
        return this.rootNode;
    }

    public void setRootNode(boolean rootNode) {
        this.rootNode = rootNode;
    }

    public boolean isOccupied() {
        return this.occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public boolean isGoalPos() {
        return this.goalPos;
    }

    public void setGoalPos(boolean goalPos) {
        this.goalPos = goalPos;
    }

    public boolean isStartPos() {
        return this.startPos;
    }

    public void setStartPos(boolean startPos) {
        this.startPos = startPos;
    }

    public boolean hasChild() {
        return ((leftChild != null) || (rightChild != null));
    }

    public Square getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Square leftChild) {
        this.leftChild = leftChild;
    }

    public Square getRightChild() {
        return rightChild;
    }

    public void setRightChild(Square rightChild) {
        this.rightChild = rightChild;
    }

    public String toString() {
        return this.x + "," + this.y;
    }
}
