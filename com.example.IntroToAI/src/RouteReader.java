/*
 * Copyright (c), Robyn Van Deventer, 2017.
 * Developed at Swinburne University of Technology.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * -------Question 1-------
 * The different paradigms of AI are that of thinking vs acting and behaving rationally or like a human.
 * Thinking and acting like a human likely needs thoughts and actions to have some form of emotion involved as
 * well as compassion or empathy. A human-like AI most likely needs to not always make sense and perhaps makes mistakes,
 * or takes time to think about or complete actions. It needs to behave like a human, which aren't always rational or
 * predictable. A rationally thinking or acting AI simply needs to perform the most optimal actions.
 *
 * Due to these differences, the solutions to one paradigm would not always be applicable to solve problems in another.
 *
 * -------Question 2-------
 * A
 * Looking at something like a driverless car, you'd expect the AI to act rationally. Something like SIRI,
 * I'd expect to have human-like qualities as it's sole purpose is human interaction. Game AI is an interesting one
 * depending on the type of NPC or environment. Some NPCs you may want to think or act like a human others could be
 * very rational.
 *
 * B
 * The driverless car is a potentially controversial issue due to the fact that lives are at stake and people would
 * likely expect the car to be perfect and never result in any loss of life. However, I believe
 * the car should act extremely rationally, which could potentially kill the occupant of the car. Say the car is
 * approaching a head on collision, a human would likely swerve the car to avoid the crash but this may put them or
 * others in further danger. What if swerving the car then results in hitting pedestrians on the side of the road?
 * The AI needs to act so rationally that sometimes the rational option would be to accept the head on collision and
 * trust in the vehicle engineering to try and minimise injury. This however, is just my personal opinion. I accept
 * that no matter how good the AI is, people can and will do stupid things which may paint the AI as the bad guy
 * when accidents do occur. I lack a lot of knowledge to be making these decisions though. I know nothing of
 * vehicle engineering or any of the physics involved in trying to minimise accidents and preserve life. I also can't
 * even begin to imagine the calculations and algorithms potentially used to calculate the best course of action.
 *
 *
 * */
public class RouteReader {

    static ArrayList<String> fileLines = new ArrayList<>();
    static ArrayList<Route> routes = new ArrayList<>();

    public static void main(String[] args) {
        readFile("C:\\Users\\Xalec\\Documents\\COS30019 Introduction to AI\\Introduction-To-AI\\com.example.IntroToAI\\src\\README.txt");
        parseFile();

        for (Route route: routes) {
            System.out.println(route.toString());
        }

        Node fagaras = new Node("Fagaras", null, null);
        Node arad = new Node("Arad", fagaras, null);
        Node bucharest = new Node("Bucharest", fagaras, null);
        fagaras.leftChild = arad;
        fagaras.rightChild = bucharest;

        BreadthFirstSearch search = new BreadthFirstSearch(fagaras, bucharest);
        BreadthFirstSearch search1 = new BreadthFirstSearch(arad, fagaras);
        BreadthFirstSearch search2 = new BreadthFirstSearch(arad, bucharest);
        search.performSearch();
        search1.performSearch();
        search2.performSearch();
    }

    public static void readFile(String fileName) {

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;

            // Read each line of the file while there is one and add it to a list
            while((line = reader.readLine()) != null) {
                fileLines.add(line);
            }

            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void parseFile() {

        // Parse through each entry of lines and split them to create a route
        for (String line: fileLines) {
            Route route = new Route();
            String[] splitLine;
            splitLine = line.split(" ");

            route.setOrigin(splitLine[0]);
            route.setDestination(splitLine[1]);
            route.setActualDistance(Integer.parseInt(splitLine[2]));
            route.setStraightDistance(Integer.parseInt(splitLine[3]));

            routes.add(route);
        }
    }
}
