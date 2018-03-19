package aiproject.utility;

import aiproject.game.GameEventListener;
import aiproject.game.Scenario;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.util.Map;

public class GameStatistics {

    public static StatisticListener getStatWriter(CSVPrinter printer) {
        return new StatisticListener(printer);
    }

    private static class StatisticListener implements GameEventListener {
        private CSVPrinter printer;
        private int scenario = -1;
        private int iterationNumber = 0;

        public StatisticListener(CSVPrinter printer) {
            this.printer = printer;
        }

        @Override
        public void turnComplete(Map<Integer, Integer> steps, Map<Integer, Integer> targetsCollected) {
        }

        @Override
        public void gameComplete(Map<Integer, Integer> targetsCollected, int turnCount, int winner) {
            try {
                StatisticUtils statisticUtils = new StatisticUtils();
                targetsCollected.forEach((k, v) -> statisticUtils.add(v / (double) (turnCount + 1)));

                for (Integer agent : targetsCollected.keySet()) {
                    double happiness = targetsCollected.get(agent) / (double) (turnCount + 1);
                    printer.print(scenario);
                    printer.print(iterationNumber);
                    printer.print(agent);
                    printer.print(targetsCollected.get(agent));
                    printer.print(turnCount);
                    printer.print(happiness);
                    printer.print(statisticUtils.getMax());
                    printer.print(statisticUtils.getMin());
                    printer.print(statisticUtils.getMean());
                    printer.print(statisticUtils.getStandardDev());
                    printer.print((happiness - statisticUtils.getMin()) /
                            (statisticUtils.getMax() - statisticUtils.getMin()));
                    printer.printRecord();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            iterationNumber++;
        }

        @Override
        public void gameStart(Scenario scenario) {
            this.scenario = scenario.getNum();
        }
    }
}
