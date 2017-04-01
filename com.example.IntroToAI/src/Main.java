/*
 * Copyright (c) 2017.
 * Robyn Van Deventer - Developed at Swinburne University
 */

import Environment.Map;
import Environment.MapUI;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main {

    static int[] dimensions = new int[2];
    static int[] initialState = new int[2];
    static int[] goalState = new int[2];
    static ArrayList<int[]> occupied = new ArrayList<>();
     static int[][] occupiedStates;

    public static void main(String[] args) {

        if (args.length < 3) {
            System.out.println("Not enough arguments");
            System.exit(0);
        }

        // Loop through the arguments and assign them to the variables
        for (int i = 0; i < args.length; i++) {

            // Find the first argument, this should be an array of two integers
            if (i == 0) {

                String sizeArray[] = args[i].replaceAll("\\[", "").replaceAll("]", "").replaceAll("\\s", "").split(",");

                if (sizeArray.length != 2) {
                    System.out.println("Invalid format provided in dimensions");
                    System.exit(0);
                }

                for (int j = 0; j < sizeArray.length; j++) {
                    dimensions[j] = Integer.parseInt(sizeArray[j]);
                }

            }
            // Find the second argument, this should be an ArrayList of two integers
            else if (i == 1) {

                String startPosArray[] = args[i].replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("\\s", "").split(",");

                if (startPosArray.length != 2) {
                    System.out.println("Invalid format provided in start position");
                    System.exit(0);
                }

                for (int j = 0; j < startPosArray.length; j++) {
                    initialState[j] = Integer.parseInt(startPosArray[j]);
                }

            }
            // Find the third argument, this should be an ArrayList of two integers
            else if (i == 2) {

                String goalPosArray[] = args[i].replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("\\s", "").split(",");

                if (goalPosArray.length != 2) {
                    System.out.println("Invalid format provided in goal position");
                    System.exit(0);
                }

                for (int j = 0; j < goalPosArray.length; j++) {
                    goalState[j] = Integer.parseInt(goalPosArray[j]);
                }

            }
            else {
                // For the rest of the arguments, add these to the occupied states
                // These should be an ArrayLost of four integers
                String[] occupiedArray = args[i].replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("\\s", "").split(",");

                if (occupiedArray.length != 4) {
                    System.out.println("Invalid format provided in occupied states");
                    System.exit(0);
                }

                int[] temp = new int[4];

                for (int j = 0; j < occupiedArray.length; j++) {
                    temp[j] = Integer.parseInt(occupiedArray[j]);
                }
                occupied.add(temp);
            }
        }

        occupiedStates = new int[occupied.size()][4];
        for (int i = 0; i < occupied.size(); i++) {
            occupiedStates[i] = occupied.get(i);
        }

        for (int i = 0; i < dimensions.length; i++) {
            System.out.print(dimensions[i]);
        }

        for (int i = 0; i < initialState.length; i++) {
            System.out.print(initialState[i]);
        }

        for (int i = 0; i < goalState.length; i++) {
            System.out.print(goalState[i]);
        }

        for (int i = 0; i < occupiedStates.length; i++) {
                System.out.print(occupiedStates[i]);
        }

        Map map = new Map(initialState, goalState, dimensions, occupiedStates);
    }
}
