/*
 * Copyright (c) 2017. Robyn Van Deventer.
 * Created for Swinburne University of Technology.
 */

package Inference;

import java.util.ArrayList;

public class TruthTable {

    private String tell;
    private String ask;
    private String string = "";
    private ArrayList<String> facts;
    private ArrayList<String> clauses;
    private ArrayList<String> unique;
    private int truthCount = 0;

    public TruthTable(String tell, String ask) {

        this.tell = tell;
        this.ask = ask;

        facts = new ArrayList<>();
        clauses = new ArrayList<>();
        unique = new ArrayList<>();
        setup();
        getUnique();
        generate(unique.size());
        System.out.println(truthCount());
    }

    private void setup() {

        // split the knowledge base into facts and clauses
        String[] temp = this.tell.split(";");
        for (String string: temp) {
            if (string.contains("=>")) {
                clauses.add(string);
            }
            else {
                facts.add(string.trim());
            }
        }
    }

    private void getUnique() {

        // add facts to unique
        for (String fact: facts) {
            unique.add(fact.trim());
        }

        // add all unique instances in clauses to unique
        for (String clause: clauses) {

            //split the clauses
            String[] a = clause.split("=>");

            // split the premises
            if (a[0].contains("&")) {

                String[] b = a[0].split("&");
                if (!unique.contains(a[1].trim()))unique.add(a[1].trim());

                // add them to unique
                for (String string: b) {
                    if (!unique.contains(string.trim())) unique.add(string.trim());
                }
            }
            else {
                // add them to unique
                for (String string: a) {
                    if (!unique.contains(string.trim())) unique.add(string.trim());
                }
            }
        }

        unique.sort(String::compareToIgnoreCase);
    }

    public void  generate(int n){

        // debug table printing
//        // print headers
//        for (String string: unique) {
//            System.out.print(string + " ");
//        }
//
//        for (String string: clauses) {
//            System.out.print(string + "     ");
//        }
//
//        for (String string: facts) {
//            System.out.print(string + "     ");
//        }

        System.out.println();

        // create the binary table based on number of unique symbols
        int count = 0;
        for (int i = 0 ; i != (1<<n) ; i++) {
            String s = Integer.toBinaryString(i);
            while (s.length() != n) {
                s = '0'+s;
            }
            // debug table printing
            // System.out.println(formatTable(s));
            formatTable(s);
            string = "";
            count++;
        }
        // debug line count
        //System.out.println(count);
    }

    private String formatTable(String s) {

        // find the count of unique symbols
        int length = unique.size();

        // pad the t/f values to line up with the headings
        for (int i = 0; i < length; i++) {
            string += s.substring(i, i+1);
            string += " ";
            if (unique.get(i).length() > 1) string += " ";
        }

        string += " ";

        // validate each clause against the truth table
        // validate each fact against the truth table
        for (String clause: clauses) {
            string += validateClause(clause, s);
            for (int i = 0; i < clause.length(); i++) {
                string += " ";
            }
        }

        for (String fact: facts) {
            string += validateFact(fact, s);
            string += "       ";
        }

        validateAsk(string);

        return string;
    }

    private String validateClause(String clause, String truthTable) {

        // find the clause position
        int clausePosition = clauses.indexOf(clause);
        String string = "";

        // split the clause
        // find the indexes of each symbol
        // find the second symbol value
        String[] splitClause = clauses.get(clausePosition).split("=>");
        int firstSymbolIndex = unique.indexOf(splitClause[0].trim());
        int secondSymbolIndex = unique.indexOf(splitClause[1].trim());
        int secondSymbol = Character.getNumericValue(truthTable.charAt(secondSymbolIndex));

        // split the premise
        if (splitClause[0].contains("&")) {
            String[] splitPremise = splitClause[0].split("&");
            int firstPremiseIndex = unique.indexOf(splitPremise[0]);
            int secondPremiseIndex = unique.indexOf(splitPremise[1]);
            int firstPremiseSymbol = Character.getNumericValue(firstPremiseIndex);
            int secondPremiseSymbol = Character.getNumericValue(secondPremiseIndex);

            // false
            if (((firstPremiseSymbol == 1) && (secondPremiseSymbol == 1)) && secondSymbol == 0) {
                string += "0    ";
            }
            // true
            else {
                string += "1    ";
            }
        }
        else {
            int firstSymbol = Character.getNumericValue(truthTable.charAt(firstSymbolIndex));

            // false
            if ((firstSymbol == 1) && (secondSymbol == 0)) {
                string += "0    ";
            }
            // true
            else {
                string += "1    ";
            }
        }

        return string;
    }

    private String validateFact(String fact, String truthTable) {

        int factPosition = unique.indexOf(fact.trim());

        // return the symbol value of the fact
        return String.valueOf(truthTable.charAt(factPosition));
    }

    private void validateAsk(String string) {
        String table = string.replaceAll("\\s+","");
        String[] completeTable = table.split("");
        ArrayList<String> agenda = new ArrayList<>();
        ArrayList<String> searched = new ArrayList<>();
        agenda.add(ask);
        int askClauseIndex = -1;
        Boolean keepSearching = true;
        String clause = null;

        while (keepSearching) {

            if (agenda.size() > 0) {
                searched.add(agenda.get(0));
                clause = agenda.remove(0);
            }

            // we are at the end and have proved the original ask
            if (!clauses.contains(clause.trim()) && facts.contains(clause.trim())) {

                int trueSymbols = 0;
                for (String symbol: searched) {
                    if (clauses.contains(symbol.trim())) {
                        if (Integer.parseInt(completeTable[unique.size() + clauses.indexOf(symbol.trim())]) == 1) trueSymbols++;
                    }
                    else {
                        if (Integer.parseInt(completeTable[unique.size() + clauses.size() + facts.indexOf(clause.trim())]) == 1) trueSymbols++;
                    }
                }
                if (trueSymbols == searched.size()) {
                    truthCount++;
                }
                return;
            }

            // find the clause with the symbol we are trying to infer
            for (int i = 0; i < clauses.size(); i++) {
                if (clauses.get(i).contains(clause.trim())) {
                    if (clauses.get(i).split("=>")[1].contains(clause.trim())) {
                        askClauseIndex = i;
                    }
                }
            }

            // find the index in the filled out table that corresponds to the ask's clause
            if (askClauseIndex >= 0) {
                int clauseValue = Integer.parseInt(completeTable[unique.size() + askClauseIndex]);

                // if the clause is true, we can proceed
                if (clauseValue == 1) {
                    String[] splitClause = clauses.get(askClauseIndex).split("=>");

                    // check if the clause is inferring our ask
                    if (splitClause[1].contains(clause.trim())) {
                        if (splitClause[0].contains("&")) {
                            String[] splitPremise = splitClause[0].split("&");
                            for (String premise: splitPremise) {
                                agenda.add(premise);
                            }
                        } else {
                            agenda.add(splitClause[0]);
                        }
                    }
                }
                else {
                    keepSearching = false;
                }
            }
            else {
                keepSearching = false;
            }
        }
    }

    private String truthCount() {

        String string = "";

        if (truthCount > 0) {
            string += "YES: " + truthCount;
        }
        else {
            string += "NO";
        }

        return string;
    }
}
