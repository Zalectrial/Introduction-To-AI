/*
 * Copyright (c) 2017.
 * Robyn Van Deventer - Developed at Swinburne University
 */

package Environment;

import SearchMethods.SearchManager;
import SearchMethods.SearchMethod;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class MapReader {

    private String fileName;
    private String[] fileLines;
    private static int[] dimensions = new int[2];
    private static int[] initialState = new int[2];
    private static int[] goalState = new int[2];
    private static ArrayList<int[]> occupied = new ArrayList<>();
    private static int[][] occupiedStates;

    public MapReader(String fileName, String searchMethod) {

        this.fileName = fileName;
        readFile();
        parseFile();
        Map map = new Map(initialState, goalState, dimensions, occupiedStates);
        new SearchManager(SearchMethod.valueOf(searchMethod), map);
    }

    private void readFile() {

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName + ".txt"));
            String line;
            ArrayList<String> lines = new ArrayList<>();

            // Read each line of the file while there is one and add it to a list
            while((line = reader.readLine()) != null) {
                lines.add(line);
            }

            fileLines = new String[lines.size()];

            // Add the lines stored to an array
            for (int i = 0; i < fileLines.length; i++) {

                fileLines[i] = lines.get(i);
            }

            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parseFile() {

        // Check if there are enough arguments in the file
        // Need at least size, start and goal
        if (fileLines.length < 3) {
            System.out.println("Not enough arguments");
            System.exit(0);
        }

        for (int i = 0; i < fileLines.length; i++) {

            // The first index is the grid dimensions
            if (i == 0) {

                String sizeArray[] = fileLines[i].replaceAll("\\[", "").replaceAll("]", "").replaceAll("\\s", "").split(",");

                // Check for invalid dimensions
                if (sizeArray.length != 2) {
                    System.out.println("Invalid format provided in dimensions");
                    System.exit(0);
                }

                // Set the dimensions to be used later
                for (int j = 0; j < sizeArray.length; j++) {
                    dimensions[j] = Integer.parseInt(sizeArray[j]);
                }
            }
            // Index 1 is the start position
            else if (i == 1) {

                String startPosArray[] = fileLines[i].replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("\\s", "").split(",");

                // Check for invalid start position
                if (startPosArray.length != 2) {
                    System.out.println("Invalid format provided in start position");
                    System.exit(0);
                }

                // Set the start position to be used later
                for (int j = 0; j < startPosArray.length; j++) {
                    initialState[j] = Integer.parseInt(startPosArray[j]);
                }
            }
            // Index 2 is the goal position
            else if (i == 2) {

                String goalPosArray[] = fileLines[i].replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("\\s", "").split(",");

                // Check for invalid goal position
                if (goalPosArray.length != 2) {
                    System.out.println("Invalid format provided in goal position");
                    System.exit(0);
                }

                // Set the goal position to be used later
                for (int j = 0; j < goalPosArray.length; j++) {
                    goalState[j] = Integer.parseInt(goalPosArray[j]);
                }
            }
            else {

                // For the rest of the arguments, add these to the occupied states
                // These should be an ArrayList of four integers
                String[] occupiedArray = fileLines[i].replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("\\s", "").split(",");

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

        // Set all of the occupied states to the array to be used later
        occupiedStates = new int[occupied.size()][4];
        for (int i = 0; i < occupied.size(); i++) {
            occupiedStates[i] = occupied.get(i);
        }
    }
}
