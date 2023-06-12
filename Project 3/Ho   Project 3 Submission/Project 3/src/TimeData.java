//import java.text.SimpleDateFormat;
//import java.util.Calendar;
import java.util.GregorianCalendar;
//import java.util.TimeZone;

/**
 * 
 * @author CS2334. Modified by: Rafal Jabrzemski
 *         <P>
 *         Date: 2018-02-01 <BR>
 *         Project 1
 *         <P>
 *         This class represents a summary of one time's data from a single
 *         Mesonet station.
 * @author Anh-Tu Ngoc 
 * @version 2018-01-01 
 * 
 *
 */
public class TimeData
{   
    // CHECK NOT IN UML
    private int year; 
    private int month;
    private int day;
    private int minute; 
    
    
    /**
     * measurementDateTimeUTC : The time in which the measurement was taken 
     * stationID : The station in which the measurement was taken from 
     * tair : The air temperature 
     * ta9m : The air temperature at 9 meters
     * solarRadiation: the solar radiation that was measured. 
     */
    private GregorianCalendar measurementDateTimeUTC; 
    /**
     * measurementDateTimeUTC : The time in which the measurement was taken 
     * stationID : The station in which the measurement was taken from 
     * tair : The air temperature 
     * ta9m : The air temperature at 9 meters
     * solarRadiation: the solar radiation that was measured. 
     */
    private String stationID;
    /**
     * measurementDateTimeUTC : The time in which the measurement was taken 
     * stationID : The station in which the measurement was taken from 
     * tair : The air temperature 
     * ta9m : The air temperature at 9 meters
     * solarRadiation: the solar radiation that was measured. 
     */
    private Measurement tair; 
    /**
     * measurementDateTimeUTC : The time in which the measurement was taken 
     * stationID : The station in which the measurement was taken from 
     * tair : The air temperature 
     * ta9m : The air temperature at 9 meters
     * solarRadiation: the solar radiation that was measured. 
     */
    private Measurement ta9m; 
    /**
     * measurementDateTimeUTC : The time in which the measurement was taken 
     * stationID : The station in which the measurement was taken from 
     * tair : The air temperature 
     * ta9m : The air temperature at 9 meters
     * solarRadiation: the solar radiation that was measured. 
     */
    private Measurement solarRadiation;
    
    
    
    /**
     * Constructor 
     * @param stationID :The ID of the station 
     * @param year :the year 
     * @param month :the month  
     * @param day :the day
     * @param minute :the minute
     * @param tair :The air temperature 
     * @param ta9m :The air temperature at 9 meters 
     * @param solarRadiation :The solar radiation 
     */
    public TimeData(String stationID, int year, int month, int day, int minute, Measurement tair, Measurement ta9m,
            Measurement solarRadiation)
    { 
      
         
        
        this.stationID = stationID;    
        setDateTimeComponents(year, month, day, minute); 
        setMeasurements( tair, ta9m, solarRadiation);  
        
        
    }
    
    
    
    
    /**
     * Constructor 
     * @param inStationID , ID of station 
     * @param dateTime , date and time of measurement 
     * @param tair , air temperature 
     * @param ta9m , air temperature at 9 meter 
     * @param solarRadiation , the amount of solar radiation measured at the time 
     */
    public TimeData( String inStationID, GregorianCalendar dateTime, Measurement tair, Measurement ta9m, 
            Measurement solarRadiation)
    {
        stationID = inStationID; 
        measurementDateTimeUTC = dateTime; // RETURN AND CHECK: Needs conversion?  
        setMeasurements( tair, ta9m, solarRadiation); 
        
        
        
    }
    
    
    /**
     * @return measurementDateTimeUTC : the time and date of the measurement 
     */ 
    public GregorianCalendar getMeasurementDateTime()
    {
        return measurementDateTimeUTC; 
    }
    
    
    /**
     * Sets a new date and time for the field variable of this measurement 
     * @param iyear : the year
     * @param imonth : the month 
     * @param iday : the day 
     * @param iminute : the minute 
     */
    private void setDateTimeComponents(int iyear, int imonth, int iday, int iminute)
    {
        this.year = iyear;
        this.month = imonth; 
        this.day = iday;  
        this.minute = iminute; 
    }
    
    
    /**
     * Sets the individual measurement fields for this time. 
     * @param itair : the air temperature 
     * @param ita9m : the air temperature at 9 metres 
     * @param isolarRadiation : the solar radiation of that measurement 
     */
    private void setMeasurements( Measurement itair, Measurement ita9m, Measurement isolarRadiation)
    {
        this.tair = itair; 
        this.ta9m = ita9m; 
        this.solarRadiation = isolarRadiation; 
    }
    
    
    /**
     * @return minute : the minute of this measurement 
     */
    public int getMinute()
    {
        
        return minute; 
    }
    
    /**
     * @return month : the month of this measurement 
     */
    public int getMonth()
    {
        return month; 
    }
    
    /**
     * @return day : the day that this measurement was taken 
     */
    public int getDay()
    {
        return day; 
    }
    
    /**
     * @return year : the year that this measurement was taken 
     */
    public int getYear()
    {
        return year; 
    }
    
    /**
     * @return stationID : the ID of the station 
     */
    public String getStationID()
    {
        return stationID; 
    }
    
    
    /**
     * @return ta9m : the air temperature at 9 metres 
     */
    public Measurement getTa9m()
    {
        return ta9m; 
    }
    
    /**
     * @return solarRadiation : the total radiation from the Chernobyl nuclear fallout 
     */
    public Measurement getSolarRadiation()
    {
        return solarRadiation; 
    }
    
    /**
     * @return tair : air temperature from Bernie Sander's climate change 
     */
    public Measurement getTair()
    {
        return tair;
    }
   
}
