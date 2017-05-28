/*
 * Copyright (c) 2017. Robyn Van Deventer.
 * Created for Swinburne University of Technology.
 */

package Inference;

import java.util.ArrayList;

public class ForwardChaining {

    private String tell;
    private String ask;
    private ArrayList<String> agenda;
    private ArrayList<String> facts;
    private ArrayList<String> clauses;
    private ArrayList<String> entailed;

    public ForwardChaining(String tell, String ask) {

        this.tell = tell;
        this.ask = ask;

        agenda  = new ArrayList<>();
        clauses  = new ArrayList<>();
        entailed  = new ArrayList<>();
        facts  = new ArrayList<>();
        setup();
        System.out.print(execute());
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
                agenda.add(string);
            }
        }
    }

    // execute the search and print the output
    private String execute(){
        String output;

        if (entails()){
            // the method returned true so it entails
            output = "YES: ";
            // loop through all entailed symbols in reverse
            for (int i=entailed.size()-1;i>=0;i--){
                if (i==0)
                    output += entailed.get(i);
                else
                    // no comma at the end
                    output += entailed.get(i)+", ";

            }
        }
        // no
        else{
            output = "NO";
        }
        return output;
    }

    // find if the agenda is entailed
    // add more to the agenda
    private boolean entails(){

        while(!agenda.isEmpty()){

            String currentAgenda = agenda.remove(agenda.size()-1);
            entailed.add(currentAgenda);

            // if we have entailed the ask, we are done
            // check if there are any facts we have not added to entails
            if (currentAgenda.equals(ask)) {
                for (String string: agenda) {
                    if (facts.contains(string) && !entailed.contains(string)) {
                        entailed.add(string);
                    }
                }
                agenda.clear();
                return true;
            }

                ArrayList<String> splitPremises = new ArrayList<>();
                for (String clause : clauses) {

                    if (clause.contains("=>")) {
                        // split the clause
                        String[] splitClause = clause.split("=>");

                        // this clause entails current agenda
                        if (splitClause[0].contains(currentAgenda.trim())) {

                            if (splitClause[0].contains("&")) {
                                String[] splitPremise = splitClause[0].split("&");
                                for (String premise : splitPremise) {
                                    splitPremises.add(premise.trim());
                                }
                            }
                            else {
                                if (!entailed.contains(splitClause[1])) {
                                    agenda.add(splitClause[1].trim());
                                }
                            }
                        }
                    }
                }
                // if this isn't a fact and size is 0, there are no premises
                if (splitPremises.size() != 0) {
                    for (String premise : splitPremises) {
                        if (!entailed.contains(premise.trim())) agenda.add(premise.trim());
                    }
                }
            }
        return true;
    }
}
