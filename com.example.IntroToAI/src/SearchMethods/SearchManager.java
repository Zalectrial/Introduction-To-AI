/*
 * Copyright (c) 2017.
 * Robyn Van Deventer - Developed at Swinburne University
 */

package SearchMethods;

import Environment.Map;

public class SearchManager {

    static SearchMethod searchMethod;
    static Map map;

    public SearchManager(SearchMethod searchMethod, Map map) {

        this.searchMethod = searchMethod;
        this.map = map;
        Search();
    }

    // Sets the search type on the UI
    // Switches through the search methods and calls the appropriate search class
    private void Search() {
        map.map.setSearchType(searchMethod.toString());

        switch (searchMethod) {
            case AS: new AStar(); break;
            case DFS: new DepthFirstSearch(); break;
            case BFS: new BreadthFirstSearch(); break;
            case GBFS: new GreedyBestFirstSearch(); break;
            case CUS1: new CustomSearch1(); break;
            case CUS2: new CustomSearch2(); break;
        }
    }
}
