package aiproject;


import aiproject.agent.CompetitiveAgent;
import aiproject.game.*;

import javax.swing.*;
import java.util.LinkedList;

public class Application {

    private static final int NUM_AGENTS = 5;
    private static final int TARGETS_PER_AGENT = 5;
    private static final int GAME_WIDTH = 100;
    private static final int GAME_HEIGHT = 100;
    private static final int RADAR_RANGE = 10;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Game");

        LinkedList<Agent> competitiveAgents = new LinkedList<>();

        for (int i = 0; i < NUM_AGENTS; i++) {
            competitiveAgents.add(new CompetitiveAgent(i));
        }

        GameModel model = new GameModel(GAME_WIDTH, GAME_HEIGHT, RADAR_RANGE, TARGETS_PER_AGENT);

        GameView view = new GameView(model);
        Timer paintTimer = new Timer(20, e -> view.repaint());
        paintTimer.start();

        frame.add(view);
        frame.setSize(400, 420);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

        Game game = new Game(model, Scenario.COMPETITION);
        game.start(competitiveAgents);
    }
}
