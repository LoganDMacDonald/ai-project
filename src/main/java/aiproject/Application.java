package aiproject;

import aiproject.agent.CollaborativeAgent;
import aiproject.agent.CompassionateAgent;
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

    private static final LinkedList<Agent> agents = new LinkedList<>();
    private static final int NUM_AGENTS = 5;
    private static final int TARGETS_PER_AGENT = 5;
    private static final int GAME_WIDTH = 100;
    private static final int GAME_HEIGHT = 100;
    private static final int RADAR_RANGE = 10;

    private static final GameModel model = new GameModel(GAME_WIDTH, GAME_HEIGHT, RADAR_RANGE, TARGETS_PER_AGENT);

    public static void main(String[] args) throws IOException, InterruptedException {
        if (args.length > 0 && "-test".equals(args[0])) {
            test();
        } else {
            Scenario scenario = Scenario.COLLABORATION;
            try {
                if (args.length > 0) {
                    int sc = Integer.parseInt(args[0]);
                    if (Scenario.valueOf(sc) == null) {
                        System.err.println("Invalid Scenario");
                        return;
                    }
                    scenario = Scenario.valueOf(sc);
                }
            } catch (NumberFormatException exception) {
                System.err.println("Invalid Scenario");
                return;
            }

            display(scenario);
        }
    }

    private static void initAgents(Scenario scenario) {
        agents.clear();
        for (int i = 0; i < Application.NUM_AGENTS; i++) {
            switch (scenario) {
                case COMPETITION:
                    agents.add(new CompetitiveAgent(i));
                    break;
                case COLLABORATION:
                    agents.add(new CollaborativeAgent(i));
                    break;
                case COMPASSION:
                    agents.add(new CompassionateAgent(i));
                    break;
            }
        }
    }

    public static void test() throws IOException {
        Game game = new Game(model, null);

        BufferedWriter bw = Files.newBufferedWriter(Paths.get("./G10_1.csv"));
        CSVPrinter printer = new CSVPrinter(bw, CSVFormat.DEFAULT);
        game.addEventListener(GameStatistics.getStatWriter(printer));

        for (Scenario scenario : Scenario.values()) {
            game.setScenario(scenario);
            initAgents(scenario);
            for (int i = 0; i < 100; i++) {
                game.start(agents, 0);
            }
        }

        printer.close();
    }

    public static void display(Scenario scenario) throws InterruptedException {
        JFrame frame = new JFrame("Game");

        System.err.println(scenario.name());
        GameView view = new GameView(model);
        Timer paintTimer = new Timer(20, e -> view.repaint());
        paintTimer.start();

        frame.add(view);
        frame.setSize(400, 420);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

        Game game = new Game(model, scenario);

        initAgents(scenario);

        while (true) {
            game.start(agents, 15);
            Thread.sleep(1000);
        }
    }
}
