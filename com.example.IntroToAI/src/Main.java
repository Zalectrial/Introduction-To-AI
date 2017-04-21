/*
 * Copyright (c) 2017.
 * Robyn Van Deventer - Developed at Swinburne University
 */

import Environment.MapReader;

// All the main class does is pass on the arguments given
public class Main {

    public static void main(String[] args) {

        new MapReader(args[0], args[1]);
    }
}
