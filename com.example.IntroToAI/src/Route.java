/*
 * Copyright (c), Robyn Van Deventer, 2017.
 * Developed at Swinburne University of Technology.
 */

public class Route {

    private String origin;
    private String destination;
    private int actualDistance;
    private int straightDistance;

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getOrigin() {
        return this.origin;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDestination() {
        return this.destination;
    }

    public void setActualDistance(int actualDistance) {
        this.actualDistance = actualDistance;
    }

    public int getActualDistance() {
        return this.actualDistance;
    }

    public void setStraightDistance(int straightDistance) {
        this.straightDistance = straightDistance;
    }

    public String toString() {
        if (actualDistance == -1) {
            return "Cannot drive from " + origin + " to " + destination + ", however there is a straight line distance of " + straightDistance + ".";
        }

        return "Travel from " + origin + " to " + destination + " with a straight line distance of " + straightDistance + " and an actual distance of " + actualDistance + ".";
    }
}

