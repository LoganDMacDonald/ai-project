package ai_project;

import java.util.ArrayList;
import java.util.Random;

//GAME MASTER
// first targetcount to hit 0 is the winner

public class Game {

    public static boolean gameover = false;
    public static ArrayList<Agent> agents = new ArrayList();
    public static ArrayList<Target> targets = new ArrayList();
    public static int winner = -1;

    public static void main(String args[]) {
        // Initialize lists and game state

        boolean gameOver = false;
        int turnNum = 1;
        int width = 1000;
        int height = 1000;

        System.out.println("initializing objects");
        // Initialize all Class objects
        for (int i = 0; i < 5; i++) {
            Agent a = randomAgent(i, width, height); // 5 agents are made
            agents.add(a);
            System.out.println("agent " + i + "'s starting location is : " + a.getX() + "," + a.getY());
            System.out.println("");
            for (int j = 0; j < 5; j++) {
                Target t = randomTarget(i, width, height);
                // 5 targets are made for each agent and each target is given a
                // unique id
                targets.add(t);
                System.out.println("target " + t.getID() + "'s location is : " + t.getX() + "," + t.getY());
                System.out.println("and belongs to agent #" + a.id);
            }
            System.out.println("\n*****");
        }

        System.out.println("Starting game");
        // Run game until finished
        while (!gameover) {
            for (int i = 0; i < agents.size(); i++) {
                Agent a = agents.get(i);
                a.run();
            }
            winner = checkWin();
            if (winner != -1) {
                gameover = true;
            }
            System.out.println(" Targets remaining total: " + targets.size());
            System.out.println(" *************  turn " + turnNum++ + " *************");
        }
        System.out.println("\nWinner is agent " + winner);

    }


    public static int checkWin() {
        //does not account for 2 agents winning at the same time
        Agent a;
        for (int i = 0; i < agents.size(); i++) {
            a = agents.get(i);
            if (a.targetCount < 5) {
                return i;
            }
        }
        return -1;
    }

    private static Agent randomAgent(int id, int gameWidth, int gameHeight) {
        return new Agent(id, (int) (Math.random() * gameWidth), (int) (Math.random() * gameHeight));
    }

    private static Target randomTarget(int id, int gameWidth, int gameHeight) {
        return new Target(id, (int) (Math.random() * gameWidth), (int) (Math.random() * gameWidth));
    }

}
