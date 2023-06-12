/**
 * This class creates an object that holds the information every 5 minutes of
 * the day (or every line of the file)
 * 
 * @author Anh-Tu Ngoc
 * @version 10 February 2018
 */

public class TimeData
{
    /**
     * For this project, we are only focusing on one station on one day. So although
     * the values of these variables depends on certain instances, We can expect the
     * StationID, Year, Month, and Day to stay the same. The only variable subjected
     * to change in this project is the minute variable, Since it changes after
     * every line in the file.
     */
    private int minute;
    private int month;
    private int day;
    private int year;
    private String stationID;

    /**
     * These are the Air Temperature at ground and at 9 meters Also includes Solar
     * Radiation measurement. In order to access the values of these variables, We
     * have to use the methods from DayData class.
     */
    private Measurement tair;
    private Measurement ta9m;
    private Measurement solarRadiation;

    /**
     * Creates the TimeDataObject, these TimeData objects will store the day's
     * information
     * 
     * @param stationID does not change
     * @param year does not change  
     * @param month does not change 
     * @param day does not change 
     * @param minute does change 
     * @param tair Air Temperature at ground level
     * @param ta9m Air Temperature at 9 meters
     * @param solarRadiation 
     */
    public TimeData(String stationID, int year, int month, int day, int minute, Measurement tair, Measurement ta9m,
            Measurement solarRadiation)
    {
        this.minute = minute;
        this.month = month;
        this.day = day;
        this.year = year;
        this.stationID = stationID;
        this.tair = tair;
        this.ta9m = ta9m;
        this.solarRadiation = solarRadiation;
    }

    /**
     * @return The minute or timeframe
     */
    public int getMinute()
    {
        return minute;
    }

    /**
     * @return The day
     */
    public int getDay()
    {
        return day;
    }

    /**
     * @return the month
     */
    public int getMonth()
    {
        return month;
    }

    /**
     * @return the year
     */
    public int getYear()
    {
        return year;
    }

    /**
     * @return the station ID
     */
    public String getStationID()
    {
        return stationID;
    }

    /**
     * @return The air temperature at 9 meters
     */
    public Measurement getTa9m()
    {
        return ta9m;
    }

    /**
     * @return The nuclear fallout
     */
    public Measurement getSolarRadiation()
    {
        return solarRadiation;
    }

    /**
     * @return The air temperature
     */
    public Measurement getTair()
    {
        return tair;
    }

    /**
     * @return output all measurements on a separate line Remark: The information being
     *            printed out is only the information of the TimeData object, in
     *            other words, its the information of that specific time frame, the
     *            information on that specific line in the file.
     */
    public String toString() 
    {
        String output = String.format("Air temperature[1.5m] = %s, Air temperature[9m] = %s, Solar Radiation = %s",
                tair.getValue(), ta9m.getValue(), solarRadiation.getValue());
        return output;  

    }
}
