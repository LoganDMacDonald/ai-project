package aiproject.utility;

public class StatisticUtils {

    double max = Long.MIN_VALUE;
    double min = Long.MAX_VALUE;
    double sumOfSquares = 0;
    double sum = 0;
    int numValues = 0;

    public void add(double value) {
        sum += value;
        sumOfSquares += value * value;
        numValues++;
        min = Math.min(min, value);
        max = Math.max(max, value);
    }

    public double getMean() {
        if (numValues == 0)
            throw new ArithmeticException();
        return sum / (double) numValues;
    }

    public double getVariance() {
        if (numValues == 0)
            throw new ArithmeticException();
        return (sumOfSquares - sum * sum / numValues) / (numValues - 1);
    }

    public double getStandardDev() {
        return Math.sqrt(getVariance());
    }

    public double getMax() {
        if (numValues == 0)
            throw new ArithmeticException();
        return max;
    }

    public double getMin() {
        if (numValues == 0)
            throw new ArithmeticException();
        return min;
    }

    public int getNumValues() {
        return numValues;
    }
}
