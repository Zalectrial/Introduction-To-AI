/*
 * Copyright (c) 2017.
 * Robyn Van Deventer - Developed at Swinburne University
 */

package Environment;

public class Square {

    int x;
    int y;
    boolean occupied = false;
    boolean goalPos = false;
    boolean startPos = false;

    public Square(int x, int y) {
        this.x = x;
        this.y = y;
        this.occupied = occupied;
        this.goalPos = goalPos;
        this.startPos = startPos;
    }

    public String toString() {
        return this.x + "," + this.y;
    }
}
