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

        instance.getSum();
        instance.getSumOfSquares();
        instance.getNumValues();
        instance.getMin();
        instance.getMax();

        //Check Test Verification Points
        assertEquals(2.0, instance.getSum(), 0.01);
        assertEquals(4.0, instance.getSumOfSquares(), 0.01);
        assertEquals(1, instance.getNumValues(), 0.01);
        assertEquals(2.0, instance.getMin(), 0.01);
        assertEquals(2.0, instance.getMax(), 0.01);

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

        //Check Test Verification Points
        assertEquals(0, instance.getNumValues(), 0.01);
        assertEquals(0, instance.getSum(), 0.01);

        instance.getMean();
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

        instance.add(2.0);
        instance.add(4.0);

        //Check Test Verification Points
        assertEquals(2, instance.getNumValues(), 0.01);
        assertEquals(6.0, instance.getSum(), 0.01);

        //Get expected result
        double result = instance.getMean();

        //Check Return value
        assertEquals(3.0, result, 0.01);
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

        //Check Test Verification Points
        assertEquals(0, instance.getNumValues(), 0.01);
        assertEquals(0, instance.getSum(), 0.01);
        assertEquals(0, instance.getSumOfSquares(), 0.01);

        instance.getVariance();
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

        instance.add(2.0);
        instance.add(4.0);

        //Check Test Verification Points
        assertEquals(2, instance.getNumValues(), 0.01);
        assertEquals(6.0, instance.getSum(), 0.01);
        assertEquals(20.0, instance.getSumOfSquares(), 0.01);

        //Get expected result and result
        assertEquals(2.0, instance.getVariance(), 0.01);
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

        instance.add(2.0);
        instance.add(4.0);

        assertEquals(2, instance.getNumValues(), 0.01);
        assertEquals(6.0, instance.getSum(), 0.01);
        assertEquals(20.0, instance.getSumOfSquares(), 0.01);

        //Check Test Verification Points
        assertEquals(2.0, instance.getVariance(), 0.01);

        //Check Return value
        assertEquals(1.41, instance.getStandardDev(), 0.01);
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

        assertEquals(0, instance.getNumValues(), 0.01);

        instance.getMax();
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
        assertEquals(2, instance.getNumValues(), 0.01);

        //Check Return value
        assertEquals(4.0, instance.getMax(), 0.01);
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

        assertEquals(0, instance.getNumValues(), 0.01);

        instance.getMin();
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
        assertEquals(2, instance.getNumValues(), 0.01);

        //Check Return value
        assertEquals(2.0, instance.getMin(), 0.01);

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

        instance.add(1.0);

        //Check Test Verification Points
        assertEquals(1, instance.getNumValues(), 0.01);
    }
}
