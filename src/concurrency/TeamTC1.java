package concurrency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Random;

class TeamTC1 implements Runnable {
    String name;


    public static List<String> team;

    public TeamTC1(List<String> team, String name) {
        this.team = team;
        this.name = name;
    }

    //This thread should be created by implementing the Runnable interface, NOT by extending the Thread class.  In the run method of this thread, print out the name of each student in your TA group, (starting with your TA).  There should be a pause of 1 second before each name is printed to the console.The name should then be pushed to the team List  After all the names have been pushed to this List, print out the entire list of all the students in your TA group. Don't forget your TA as well!  All of these steps should be done whenever the thread is started.  (i.e. it can be done directly in the run()method of the thread itself).  Kick off the thread in the Main class of the concurrency package.

    @Override
    public void run() {
        Thread.currentThread().setName(name);

        System.out.println(Color.getRandom() + "Initializing Thread List for " + name + ": ");
        List<String> copy = new ArrayList<String>();
        for (String s : team) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Color.getRandom() + s);
            copy.add(Color.getRandom() + s);

        }

        System.out.println(Color.getRandom() + "We are the " + name + ": ");
        System.out.println(Color.getRandom() + copy.stream().collect(Collectors.joining(", ")));
    }

}