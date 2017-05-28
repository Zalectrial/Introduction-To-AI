/*
 * Copyright (c) 2017. Robyn Van Deventer.
 * Created for Swinburne University of Technology.
 */

package Inference;

import java.util.ArrayList;

public class TruthTable {

    private String tell;
    private String ask;
    private ArrayList<String> facts;
    private ArrayList<String> clauses;
    private ArrayList<String> unique;

    public TruthTable(String tell, String ask) {

        this.tell = tell;
        this.ask = ask;

        facts = new ArrayList<>();
        clauses = new ArrayList<>();
        unique = new ArrayList<>();
        setup();
        getUnique();
        generate(unique.size());
    }

    private void setup() {

        // split the knowledge base into facts and clauses
        String[] temp = this.tell.split(";");
        for (String string: temp) {
            if (string.contains("=>")) {
                clauses.add(string);
            }
            else {
                facts.add(string);
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

        // print headers
        for (String string: unique) {
            System.out.print(string + "     ");
        }

        for (String string: clauses) {
            System.out.print(string + "     ");
        }

        for (String string: facts) {
            System.out.print(string + "     ");
        }

        System.out.println();

        int count = 0;
        for (int i = 0 ; i != (1<<n) ; i++) {
            String s = Integer.toBinaryString(i);
            while (s.length() != n) {
                s = '0'+s;
            }
            System.out.println(s + "   ");
            count++;
        }
        System.out.println(count);
    }
}
