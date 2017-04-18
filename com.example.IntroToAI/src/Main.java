/*
 * Copyright (c) 2017.
 * Robyn Van Deventer - Developed at Swinburne University
 */

import Environment.Map;
import Environment.MapReader;
import SearchMethods.SearchManager;
import SearchMethods.SearchMethod;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        MapReader mapReader = new MapReader(args[0], args[1]);

        // TODO Pass the search method to the search manager
//        SearchManager searchManager = new SearchManager(SearchMethod.BFS, map);
    }
}
