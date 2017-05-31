# Introduction-To-AI

Assignment 2 - Inference Engine

Robyn Van Deventer - 4931645

Team COS30019_A02_T013

## Features/Bugs/Missing

This program takes two parameters in the form of a text file containing a knowledge base and a method; either Forward Chaining, Backward Chaining or Truth Table. Depending on the method, one of three classes is created in order to perform the search.

Each class splits the knowledge base into facts and clauses in order to ease the searches.

### Forward Chaining

The forward chaining works by adding all of the facts to an agenda in order to find if these facts entail anything else. The program iterates through the agenda in order to find if the query is satisfied. Once a fact is taken from the agenda it is added to an entailed ArrayList which produces the output at the end. When finding what a fact entails, the program finds any clause where the fact is the first symbol and then adds the second symbol to the agenda to be searched too. This goes on for each entailed symbol until we find the query, or can't prove it.

In the scenario that was presented in the assignment brief, a, b and p2 were facts so were added to entailed. If p2 were being explored it would find that p2 entails p3, so p3 would be added to the agenda. p3 is then found to entail p1, and p1 is then added to the agenda. Lastly, p1 entails d, the query, so d is the added to the agenda. If the current agenda is found to be the query, we have satisfied it.

### Backward Chaining

The backward chaining works very similar to the forward chaining in that it makes use of an agenda and an entailment variable to track what has been entailed so far.

The backward chaining works in the reverse order to the forward chaining in that we begin with the query, d in the agenda and it is the first symbol to be added to entailed. The program then looks for the clauses where the agenda is the second symbol in order to move along the chain. d, is entailed by p1, so p1 is added to the agenda. p1 is entailed by p3 so it is the next one to be added to the agenda. Lastly, p3 is entailed by p2 and we have found the end of the line.

### Truth Table

The truth table was very different to the chaining methods in that we needed to create a truth table and evaluate the different clauses and facts for every entry into the table. Depending on how many unique symbols in the knowledge base, this table could get very large. The number of rows is equal to 2^no.unique symbols. In the case of the example provided in the assignment brief, there should be 2048 rows. All 7 clauses and all 3 facts would then need to be validated against each and every row.
 
To create the table, I created a binary table based of the number of unique symbols resulting in my table being based of 1/0 rather than T/F or any other variation. A clause is determined to be false if S1 is true AND S2 is false. In every other case, the clause is true. Facts are true or false based upon whether or not the symbol is true or false in this iteration of the knowledge base.

The method that evaluates the number of models that are true work in a similar way to the overall FC/BC methods. It begins by adding the query to the agenda and searches for clauses where this agenda is entailed and where the clause is true. If the clause is true, the first symbol is added to the agenda and that is then searched too. Once we can't find any clauses we look to the facts. If the fact that is our agenda is true, then we have a model where the query is satisfied. A truthCounter is then incremented. This is done for every single entry into the truth table. 

### Bugs

I believe that I have made a logic error somewhere along the line in implementing the truth table. For some knowledge bases, the result is correct, yet in the example provided in the assignment brief, my output differs from the result there. If I have interpreted the way in which to calculate the number of models correctly, I am not sure where the error lies, as some results are correct.

The program does not react in an expected manner if no facts are provided in the knowledge base.

### Missing

Everything in the assignment brief has been implemented in this inference engine.

## Test Cases

There were a couple of different knowledge bases used to test this program. Nothing was implemented to automate a test case generation. The test cases that were used helped to identify the two bugs listed above.

#### The test cases that were used are as follows:

TELL
a => b; b =>c; c => d;
ASK
d

TELL
A => B; B => C; C => D; A;
ASK
D

TELL
p2=> p3; p3 => p1; c => e; b&e => f; f&g => h; p1=>d; p1&p3 => c; a; b; p2;
ASK
d

## Acknowledgements/Resources

http://snipplr.com/view/56296/ai-forward-chaining-implementation-for-propositional-logic-horn-form-knowledge-bases/ - This example was the most comprehensive example of using a Forward Chaining method. This example provided me with a huge understanding of how to implement the FC, and upon understanding this, it also provided more understanding of how to implement the Backward chaining. 

http://snipplr.com/view/56297/ - This was the partner example to the FC one above, except this one focused on the backward chaining example. Whilst this example was useful, once I understood the FC, it was easier to interpret how to reverse that for use in the BC method.

https://stackoverflow.com/a/10723228 - This comment provided me with the know how of how to create a binary table based on the number of unique symbols that I had to work with.

Introduction to AI A Modern Approach - The textbook helped further clarify the concepts of this assignment.

http://mrieppel.net/prog/truthtable.html - This tool was handy for plugging in knowledge bases to generate a table and see if mine was producing the correct amount of entries.

Lecture slides - The lecture slides pertaining to this assignment were useful in clarifying some of the concepts.

## Notes

### How to use this program

To run this program from the command line, the path would need to be set to this folder:
…\Introduction-To-AI\out\production\com.example.IntroToAI	in the respective directory where the folder is stored. The program can then be run with the following command: java iengine TT test1.txt. Where test1 is the name of the text file in the current folder.
The program can also be run with a file in another directory if it is properly specified: java iengine “…\Introduction-To-AI\test1.txt” TT


## Summary Report

As this assignment was completed alone, my contributions for this assignment equate to 100% of the workload.

