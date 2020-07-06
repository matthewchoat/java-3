package concurrency;

import java.util.ArrayList;
import java.util.List;

public class Main {
   
    public static void main(String[] args) {

         //run all of your threads from this main class.
        Reasoning t = new Reasoning();
        t.start();

        Reasoning t2 = new Reasoning();
        t2.start();

        //TeamTC1
        var team = List.of("Mark Bennett", "Aaron White", "Caleb Waters", "Cody Clark", "Gotham Katta", "John Bozarov", "Justin Cheng", "Kevin Keesee", "Matthew Choat", "Korey Brooks","Sarah Bates", "Tyler Clements", "Zach Johnson");
        TeamTC1 tc1 = new TeamTC1(team, "Code Connoisseurs");

        tc1.run();


    }
}