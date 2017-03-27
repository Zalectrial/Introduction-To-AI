/*
 * Copyright (c), Robyn Van Deventer, 2017.
 * Developed at Swinburne University of Technology.
 */

import java.util.ArrayList;

public class Node {

    String name;
    Node leftChild;
    Node rightChild;

    public Node(String name, Node leftChild, Node rightChild) {
        this.name = name;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public ArrayList<Node> getChildren() {

        ArrayList<Node> children = new ArrayList<>();

        if (this.leftChild != null) {
            children.add(this.leftChild);
        }

        if (this.rightChild != null) {
            children.add(this.rightChild);
        }

        return children;
    }
}
