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

        private StatisticUtils statUtils;
        private CSVPrinter printer;
        private int scenario = -1;

        public StatisticListener(CSVPrinter printer) {
            this.printer = printer;
        }

        @Override
        public void turnComplete(Map<Integer, Integer> steps, Map<Integer, Integer> targetsCollected) {
            try {
                for (Integer agentId : steps.keySet()) {
                    int collected = targetsCollected.getOrDefault(agentId, 0);
                    int numSteps = steps.get(agentId);
                    double happiness = collected / (double) (numSteps + 1);
                    statUtils.add(happiness);
                    double competitiveness = (happiness - statUtils.getMin()) / (statUtils.getMax() - statUtils.getMin());
                    double stdDev = statUtils.getStandardDev();

                    if (Double.isNaN(competitiveness))
                        competitiveness = 0;
                    if (Double.isNaN(stdDev))
                        stdDev = 0;

                    printer.print(scenario);
                    printer.print(statUtils.getNumValues());
                    printer.print(agentId);
                    printer.print(collected);
                    printer.print(numSteps);
                    printer.print(happiness);
                    printer.print(statUtils.getMax());
                    printer.print(statUtils.getMin());
                    printer.print(statUtils.getMean());
                    printer.print(stdDev);
                    printer.print(competitiveness);
                    printer.printRecord();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void gameComplete(int turnCount, int winner) {
            try {
                printer.print(winner);
                printer.printRecord();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void gameStart(Scenario scenario) {
            this.scenario = scenario.getNum();
            statUtils = new StatisticUtils();
        }
    }
}
