/**
 * Task 1 - VaccumAgent
 *
 * This agent uses enums for the action, location and status as they're easy to switch through
 * and don't need a small classes.
 *
 * This agent begins with a list of 4 percepts, including the current one.
 * It then performs an action of SUCK or it moves to another room.
 * After moving, the current percept is removed from the list.
 */

import java.util.ArrayList;
import java.util.List;

enum ACTION {
    LEFT,
    RIGHT,
    UP,
    DOWN,
    SUCK,
    NOOP
}

enum LOCATION {
    A,
    B,
    C,
    D
}

enum STATUS {
    DIRTY,
    CLEAN
}

public class VacuumAgent {

    static List<Percept> percepts = new ArrayList<Percept>();
    static LOCATION currentLocation;
    static STATUS currentsStatus = STATUS.CLEAN;
    static Percept currentPercept;
    static int steps;
    static int stepsCompleted;

    public static void main(String[] args) {
        currentLocation = LOCATION.valueOf(args[0]);
        steps = Integer.parseInt(args[1]);
        currentPercept = new Percept(currentLocation, currentsStatus);
        getPercepts();
        while (!percepts.isEmpty()) {
            System.out.println(next(percepts));
        }
        System.out.println(getPerformanceScore(stepsCompleted));
}

    // Generate some percepts to give to the agent
    public static void getPercepts() {
        percepts.add(currentPercept);
        percepts.add(new Percept(LOCATION.B, STATUS.DIRTY));
        percepts.add(new Percept(LOCATION.C, STATUS.DIRTY));
        percepts.add(new Percept(LOCATION.D, STATUS.CLEAN));
    }

    public static ACTION next(List<Percept> percepts) {

        ACTION action;

        // Do actions based on current percept
        Percept currentPercept = percepts.get(0);

         if (currentPercept.status == STATUS.DIRTY) {
             action = ACTION.SUCK;
             currentPercept.status = STATUS.CLEAN;
             stepsCompleted++;
         }
         else {
             action = getNextDirection(currentPercept.location);
             stepsCompleted++;
             percepts.remove(0);
         }

        return action;
    }

    // Get an action based on current location: agent moves clockwise
    public static ACTION getNextDirection(LOCATION location) {
        switch (location) {
            case A: return ACTION.RIGHT;
            case B: return ACTION.DOWN;
            case C: return ACTION.LEFT;
            case D: return ACTION.UP;
        }
        return ACTION.NOOP;
    }

    // Give a performance score based on number of steps completed
    public static String getPerformanceScore(int stepsCompleted) {
        if (stepsCompleted < steps) {
            return "VacuumAgent has outdone itself! 10/10";
        }
        else if (stepsCompleted - steps < 5) {
            return "VaccumAgent has done a good job! 8/10";
        }
        else if (stepsCompleted - steps < 8) {
            return "VaccumAgent has done okay. 5/10";
        }
        else
            return "VacuumAgent has done a poor job. 2/10";
    }
}
