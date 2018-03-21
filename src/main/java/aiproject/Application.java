package aiproject;

import aiproject.agent.CollaborativeAgent;
import aiproject.agent.CompetitiveAgent;
import aiproject.game.*;
import aiproject.utility.GameStatistics;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;

public class Application {

    private static final int NUM_AGENTS = 5;
    private static final int TARGETS_PER_AGENT = 5;
    private static final int GAME_WIDTH = 100;
    private static final int GAME_HEIGHT = 100;
    private static final int RADAR_RANGE = 10;

    public static void main(String[] args) throws IOException, InterruptedException {
        JFrame frame = new JFrame("Game");

        LinkedList<Agent> competitiveAgents = new LinkedList<>();

        for (int i = 0; i < NUM_AGENTS; i++) {
            competitiveAgents.add(new CollaborativeAgent(i));
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

        //BufferedWriter bw = Files.newBufferedWriter(Paths.get("./G10_1.csv"));
        //CSVPrinter printer = new CSVPrinter(bw, CSVFormat.DEFAULT);
        //game.addEventListener(GameStatistics.getStatWriter(printer));

        while (true) {
            game.start(competitiveAgents, 15);
            Thread.sleep(1000);
        }

        //printer.close();
    }
}
