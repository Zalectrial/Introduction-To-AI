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
    private Square topChild;
    private Square bottomChild;

    public Square(int x, int y) {
        this.x = x;
        this.y = y;
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

    public boolean hasChildren() {
        return ((leftChild != null) || (rightChild != null) || (topChild != null) || (bottomChild != null));
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

    public Square getTopChild() {
        return this.topChild;
    }

    public void setTopChild(Square topChild) {
        this.topChild = topChild;
    }

    public Square getBottomChild() {
        return this.bottomChild;
    }

    public void setBottomChild(Square bottomChild) {
        this.bottomChild = bottomChild;
    }

    public String toString() {
        return this.x + "," + this.y;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;
        if (!(obj instanceof Square)) return false;

        Square square = (Square) obj;

        return this.x == square.x &&
                this.y == square.y;
    }


}
