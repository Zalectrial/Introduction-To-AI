/*
 * Copyright (c) 2017.
 * Robyn Van Deventer - Developed at Swinburne University
 */

package SearchMethods;

import Environment.Map;

public class SearchManager {

    SearchMethod searchMethod;
    Map map;

    public SearchManager(SearchMethod searchMethod, Map map) {

        this.searchMethod = searchMethod;
        this.map = map;
        Search();
    }

    public void Search() {
        map.map.setSearchType(searchMethod.toString());

        // TODO implement search handling here
        map.map.setSearchPath("Path route");
    }
}
