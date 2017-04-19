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
