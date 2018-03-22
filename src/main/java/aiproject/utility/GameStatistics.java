package aiproject.utility;

import aiproject.game.GameEventListener;
import aiproject.game.Scenario;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GameStatistics {

    public static StatisticListener getStatWriter(CSVPrinter printer) {
        return new StatisticListener(printer);
    }

    /**
     * Calculates the mean happiness and competitiveness from the input file
     * and saves the results
     *
     * @param parser  The csv input
     * @param printer The csv output
     * @throws IOException
     */
    public static void evalHappinessCompetitiveness(CSVParser parser, CSVPrinter printer) throws IOException {
        Map<Integer, StatisticUtils> happinessMap = new HashMap<>();
        Map<Integer, StatisticUtils> competitiveMap = new HashMap<>();

        for (CSVRecord record : parser.getRecords()) {
            int scenario = Integer.parseInt(record.get(0));
            StatisticUtils hMap = happinessMap.getOrDefault(scenario, new StatisticUtils());
            StatisticUtils cMap = competitiveMap.getOrDefault(scenario, new StatisticUtils());
            hMap.add(Double.parseDouble(record.get(8)));
            cMap.add(Double.parseDouble(record.get(10)));
            happinessMap.put(scenario, hMap);
            competitiveMap.put(scenario, cMap);
        }

        for (Integer scenario : happinessMap.keySet()) {
            printer.print(scenario);
            printer.print(happinessMap.get(scenario).getMean());
            printer.print(competitiveMap.get(scenario).getMean());
            printer.printRecord();
        }
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
