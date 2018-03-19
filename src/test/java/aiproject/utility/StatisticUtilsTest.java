package aiproject.utility;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class StatisticUtilsTest
{

    @Before
    public void setUp()
    {

    }

    @After
    public void tearDown()
    {

    }

    /*
     * Testing Conditon(s): Default
     */
    @Test
    public void test_method_add_0_branch_0()
    {
        System.out.println("Now Testing Method:add Branch:0");

        //Constructor
        StatisticUtils instance = new StatisticUtils();

        //Call Method
        instance.add(2.0);

        //Check Test Verification Points
        assertEquals(2.0, 2.0, instance.sum);
        assertEquals(4.0, 4.0, instance.sumOfSquares);
        assertEquals(4.0, 4.0, instance.sumOfSquares);
        assertEquals(1, 1, instance.numValues);
        assertEquals(2.0, 2.0, instance.min);

    }

    /*
     * Testing Conditon(s): if: (numValues == 0)
     */
    @Test(expected = ArithmeticException.class)
    public void test_method_getMean_1_branch_0() throws ArithmeticException
    {
        System.out.println("Now Testing Method:getMean Branch:0");

        //Constructor
        StatisticUtils instance = new StatisticUtils();

        instance.sum = 0;
        instance.numValues = 0;

        //Check Test Verification Points
        assertEquals(0, 0, instance.numValues);
        assertEquals(0, 0, instance.sum);

        Object expResult = true;
        Object result = instance.getMean();

        //Check Return value
        assertEquals(expResult, result);
    }

    /*
     * Testing Conditon(s): else: Not (numValues == 0)
     */
    @Test
    public void test_method_getMean_1_branch_1()
    {

        System.out.println("Now Testing Method:getMean Branch:1");

        //Constructor
        StatisticUtils instance = new StatisticUtils();

        instance.numValues = 2;
        instance.sum = 6.0;

        //Check Test Verification Points
        assertEquals(2, 2, instance.numValues);
        assertEquals(6.0, 6.0, instance.sum);

        //Get expected result and result
        Object expResult = 3.0;
        Object result = instance.getMean();

        //Check Return value
        assertEquals(expResult, result);
    }

    /*
     * Testing Conditon(s): if: (numValues == 0)
     */
    @Test(expected = ArithmeticException.class)
    public void test_method_getVariance_2_branch_0() throws ArithmeticException
    {
        System.out.println("Now Testing Method:getVariance Branch:0");

        //Constructor
        StatisticUtils instance = new StatisticUtils();

        instance.numValues = 0;
        instance.sumOfSquares = 0;
        instance.sum = 0;

        //Check Test Verification Points
        assertEquals(0, 0, instance.numValues);
        assertEquals(0, 0, instance.sum);
        assertEquals(0, 0, instance.sumOfSquares);

        Object expResult = true;
        Object result = instance.getVariance();

        //Check Return value
        assertEquals(expResult, result);
    }

    /*
     * Testing Conditon(s): else: Not (numValues == 0)
     */
    @Test
    public void test_method_getVariance_2_branch_1()
    {

        System.out.println("Now Testing Method:getVariance Branch:1");

        //Constructor
        StatisticUtils instance = new StatisticUtils();

        instance.numValues = 2;
        instance.sum = 6.0;
        instance.sumOfSquares = 20.0;

        //Check Test Verification Points
        assertEquals(2, 2, instance.numValues);
        assertEquals(6.0, 6.0, instance.sum);
        assertEquals(20.0, 20.0, instance.sumOfSquares);

        //Get expected result and result
        double expResult = 2.0;
        double resultNeg = (double) (instance.getVariance() - 0.01);
        double resultPos = (double) (instance.getVariance() + 0.01);

        //Check Return value
        equals(expResult > (resultNeg) && expResult < (resultPos));
    }

    /*
     * Testing Conditon(s): Default
     */
    @Test
    public void test_method_getStandardDev_3_branch_0()
    {
        System.out.println("Now Testing Method:getStandardDev Branch:0");

        //Constructor
        StatisticUtils instance = new StatisticUtils();

        instance.numValues = 2;
        instance.sum = 6.0;
        instance.sumOfSquares = 20.0;

        //Check Test Verification Points
        assertEquals(2.0, 2.0, instance.getVariance());

        //Get expected result and result
        double expResult = 1.4;
        double resultNeg = (double) (instance.getStandardDev() - 0.01);
        double resultPos = (double) (instance.getStandardDev() + 0.01);

        //Check Return value
        equals(expResult > (resultNeg) && expResult < (resultPos));
    }

    /*
     * Testing Conditon(s): if: (numValues == 0)
     */
    @Test(expected = ArithmeticException.class)
    public void test_method_getMax_4_branch_0() throws ArithmeticException
    {
        System.out.println("Now Testing Method:getMax Branch:0");

        //Constructor
        StatisticUtils instance = new StatisticUtils();

        instance.numValues = 0;

        Object expResult = true;
        Object result = instance.getMax();

        //Check Return value
        assertEquals(expResult, result);
    }

    /*
     * Testing Conditon(s): else: Not (numValues == 0)
     */
    @Test
    public void test_method_getMax_4_branch_1()
    {
        System.out.println("Now Testing Method:getMax Branch:1");

        //Constructor
        StatisticUtils instance = new StatisticUtils();

        instance.add(2.0);
        instance.add(4.0);

        //Check Test Verification Points
        assertEquals(2, 2, instance.numValues);

        //Get expected result and result
        Object expResult = 4.0;
        Object result = instance.getMax();

        //Check Return value
        assertEquals(expResult, result);
    }

    /*
     * Testing Conditon(s): if: (numValues == 0)
     */
    @Test(expected = ArithmeticException.class)
    public void test_method_getMin_5_branch_0() throws ArithmeticException
    {
        System.out.println("Now Testing Method:getMin Branch:0");

        //Constructor
        StatisticUtils instance = new StatisticUtils();

        instance.numValues = 0;

        Object expResult = true;
        Object result = instance.getMin();

        //Check Return value
        assertEquals(expResult, result);
    }

    /*
     * Testing Conditon(s): else: Not (numValues == 0)
     */
    @Test
    public void test_method_getMin_5_branch_1()
    {
        System.out.println("Now Testing Method:getMin Branch:0");

        //Constructor
        StatisticUtils instance = new StatisticUtils();

        instance.add(2.0);
        instance.add(4.0);

        //Check Test Verification Points
        assertEquals(2, 2, instance.numValues);

        //Get expected result and result
        Object expResult = 2.0;
        Object result = instance.getMin();

        //Check Return value
        assertEquals(expResult, result);
    }

    /*
     * Testing Conditon(s): Default
     */
    @Test
    public void test_method_getNumValues_6_branch_0()
    {
        System.out.println("Now Testing Method:getNumValues Branch:0");

        //Constructor
        StatisticUtils instance = new StatisticUtils();

        instance.numValues = 1;

        //Check Test Verification Points
        assertEquals(1, 1, instance.numValues);

        //Get expected result and result
        Object expResult = 1;
        Object result = instance.getNumValues();

        //Check Return value
        assertEquals(expResult, result);
    }
}
