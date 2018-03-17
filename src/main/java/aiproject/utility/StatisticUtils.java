package aiproject.utility;

public class StatisticUtils {

    private long max = Long.MIN_VALUE;
    private long min = Long.MAX_VALUE;
    private long sumOfSquares = 0;
    private long numValues = 0;
    private long sum = 0;

    public void add(long value) {
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
        return (sumOfSquares - sum * sum / (double) numValues) / (numValues - 1);
    }

    public double getStandardDev() {
        return Math.sqrt(getVariance());
    }

    public long getMax() {
        if (numValues == 0)
            throw new ArithmeticException();
        return max;
    }

    public long getMin() {
        if (numValues == 0)
            throw new ArithmeticException();
        return min;
    }

    public long getNumValues() {
        return numValues;
    }
}
