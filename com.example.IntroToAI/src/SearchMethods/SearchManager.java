/*
 * Copyright (c) 2017.
 * Robyn Van Deventer - Developed at Swinburne University
 */

package SearchMethods;

import Environment.Map;

public class SearchManager {

    SearchMethod searchMethod;
    static Map map;

    public SearchManager(SearchMethod searchMethod, Map map) {

        this.searchMethod = searchMethod;
        this.map = map;
        Search();
    }

    public void Search() {
        map.map.setSearchType(searchMethod.toString());

        // TODO implement search handling here

        switch (searchMethod) {
            case AS: new AStar();
            case DFS: new DepthFirstSearch();
            case BFS: new BreadthFirstSearch();
            case GBFS: new GreedyBestFirstSearch();
            case CUS1: new CustomSearch1();
            case CUS2: new CustomSearch2();
        }
    }
}
