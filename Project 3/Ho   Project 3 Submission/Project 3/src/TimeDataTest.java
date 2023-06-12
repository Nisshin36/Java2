import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.Test;

/**
 * Testing the data parsing and whether the TimeData object receives the Correct
 * values.
 * 
 * @author Anh-Tu Ngoc
 * @version 15 February 2018
 */
public class TimeDataTest
{

    /**
     * Testing the constructor to see whether it receives the correct parameters,
     * Also, test whether it the Measurement class can work with the TimeData class.
     */
    @Test
    public void timeDataConstructorAndGetterTest()
    {
        // Constructing some measurement variables for tair, ta9m, and solar radiation
        Measurement tair = new Measurement(9.0);
        Measurement ta9m = new Measurement(-900.0);
        Measurement solar = new Measurement(400);

        // Creating TimeData object to test its conturctor.
        TimeData dehTime = new TimeData("MSKV", 2022, 10, 15, 50, tair, ta9m, solar);

        // These will test the class's constructor and getter methods
        Assert.assertTrue(dehTime.getStationID().equals("MSKV"));
        Assert.assertEquals(2022, dehTime.getYear());
        Assert.assertEquals(10, dehTime.getMonth());
        Assert.assertEquals(15, dehTime.getDay());
        Assert.assertEquals(50, dehTime.getMinute());

        // Testing the setter methods

        // This will test the class's getter methods and also the Measurement
        // constructor
        // Especially when a value should be flagged
        Assert.assertEquals(9.0, dehTime.getTair().getValue(), 0.01);
        Assert.assertEquals(-900, dehTime.getTa9m().getValue(), 0.01);
        Assert.assertEquals(400, dehTime.getSolarRadiation().getValue(), 0.01);
    }

    /**
     * Testing the constructor to see whether it receives the correct parameters,
     * Also, test whether it the Measurement class can work with the TimeData class.
     */
    @Test
    public void timeDataConstructorAndGetterTest2()
    {
        GregorianCalendar gc = new GregorianCalendar();

        // Constructing some measurement variables for tair, ta9m, and solar radiation
        Measurement tair = new Measurement(9.0);
        Measurement ta9m = new Measurement(-900.0);
        Measurement solar = new Measurement(400);

        TimeData daday = new TimeData("1133", gc, tair, ta9m, solar);

        Assert.assertEquals(gc, daday.getMeasurementDateTime());
        Assert.assertEquals(400.0, daday.getSolarRadiation().getValue(), 0.001);

    }

    /**
     * Just another test
     */
    @Test
    public void anotherTimeDataConstructorAndGetterTest()
    {
        String[] input = { "STIL", "89", "0", "41", "-8.9", "3.2", "3.1", "56", "10.6", "0.4", "4.2", "0.00", "1007.74",
                "0", "-8.8", "2.5", "1.1", "0.3", "0.7", "3.4", "8.2", "-999", "2.66", "1.48" };

        int tairPosition = 4;
        int ta9mPosition = 14;
        int sradPosition = 13;
        int stidPosition = 0;

        /** To help assign dates */
        class HeaderDateTime
        {
            public int year;
            public int month;
            public int day;
            public int minute;

            /**
             * 
             * @param inYear
             *            year
             * @param inMonth
             *            month
             * @param inDay
             *            day
             * @param inMinute
             *            minute
             */
            HeaderDateTime(int inYear, int inMonth, int inDay, int inMinute)
            {
                year = inYear;
                month = inMonth;
                day = inDay;
                minute = inMinute;
            }
        }

        HeaderDateTime headerDateTime = new HeaderDateTime(2017, 5, 3, 89);

        TimeData values = new TimeData(input[stidPosition], headerDateTime.year, headerDateTime.month,
                headerDateTime.day, headerDateTime.minute, new Measurement(Double.parseDouble(input[tairPosition])),
                new Measurement(Double.parseDouble(input[ta9mPosition])),
                new Measurement(Double.parseDouble(input[sradPosition])));

        Assert.assertEquals(2017, values.getYear());
    }

}