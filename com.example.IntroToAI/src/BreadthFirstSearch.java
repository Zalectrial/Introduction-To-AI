/*
 * Copyright (c), Robyn Van Deventer, 2017.
 * Developed at Swinburne University of Technology.
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch {

    Node origin;
    Node desintation;

    public BreadthFirstSearch(Node origin, Node destination) {

        this.origin = origin;
        this.desintation = destination;
    }

    public void performSearch() {

        Queue<Node> queue = new LinkedList<>();
        ArrayList<Node> explored = new ArrayList<>();
        queue.add(this.origin);
        explored.add(this.origin);

        System.out.println("Searching tree for destination: " + desintation.name + " from origin: " + origin.name);

        while (!queue.isEmpty()) {
            Node current = queue.remove();
            if (current.equals(desintation)) {
                System.out.println(current.name);
                break;
            }
            else {
                if (!current.getChildren().isEmpty()) {

                    for (Node child: current.getChildren()) {
                        // Do not explore nodes we have already explored
                        if (!explored.contains(child)) {
                            queue.add(child);
                        }
                    }
                    System.out.print(current.name + " -> ");
                }
            }
            explored.add(current);
        }

    }
}
