import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
//import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
//import java.util.TimeZone;

/**
 * Calculates the statistics for each measurement for a given day. 
 * @author Anh-Tu Ngoc
 * @version Pre Big Bang 
 *
 */
public class DayDataStatistics
{
    // HashMap that stores an Enum Map,
    // The HashMap is sorted by the paramId while the Enum Map is sorted by the statType 
    private HashMap<String , EnumMap<StatType , StatMeasurement>> paramStats ;

    
    /** The set of data. */ 
    private ArrayList<TimeData> data;

    /** Minimum tair across day. */ 
    private StatMeasurement tairMin;
    /** Maximum tair across day. */
    private StatMeasurement tairMax;
    /** Average tair across the days. */
    private StatMeasurement tairAverage;
    
    
    

    /** Minimum ta9m across day. */
    private StatMeasurement ta9mMin;
    /** Maximum ta9m across day. */
    private StatMeasurement ta9mMax;
    /** Average ta9m across day. */
    private StatMeasurement ta9mAverage;

    /** Minimum solar radiation across day. */
    private StatMeasurement solarRadiationMin;
    /** Maximum solar radiation across day. */
    private StatMeasurement solarRadiationMax;
    /** Average solar radiation. */
    private StatMeasurement solarRadiationAverage;

    /** Total solarRadiation */
    private StatMeasurement solarRadiationTotal;

    private String stationId = "nada";  
  
    /**
     * Constructor for the DayDatastatistics class 
     * @param inData : an arraylist containing TimeData objects 
     */
    public DayDataStatistics(ArrayList<TimeData> inData) 
    { 
        // Assigning the arraylist from the parameter to the private field 
        data = inData;
       
        paramStats = new HashMap<String, EnumMap<StatType, StatMeasurement>>(); 
        
        // These methods below calculates and assigns values to the rest of the private fields 
        calculateAirTemperatureStatistics("tair"); 
        calculateAirTemperatureStatistics("ta9m");  
        calculateSolarRadiationStatistics();

    }
 
    
    
    /**
     * Calculates air temperature 
     * @param tairName
     */
    private void calculateAirTemperatureStatistics(String tairName)
    { 
        
        ArrayList<Double> listOfValues = new ArrayList<>(); 
        ArrayList<TimeData> listOfValidObjects = new ArrayList<>(); 
        
        // These variables represent the "best so far" for min and max.
        // By setting these these to the largest and smallest possible 
        // values, we ensure that the first time a valid Measurement is
        // found, it will replace these values
        double max;
        double min;
        double average; 
        double sum = 0;

        GregorianCalendar minCalendar = new GregorianCalendar();
        GregorianCalendar maxCalendar = new GregorianCalendar();
        
        // FINISH IMPLEMENTATION

        
        if ( tairName.equalsIgnoreCase("tair"))
        {
            
            // Iterating through arrayList of Time Data 
            for (int index = 0; index < data.size(); ++index)  
            {
                
                if (data.get(index).getTair().isValid())
                {
                    sum = sum + data.get(index).getTair().getValue();
                    listOfValues.add(data.get(index).getTair().getValue()); 
                    listOfValidObjects.add(data.get(index)); 
                }    
            } 
            
            // Computing tairMin, tairMax, and its time of occurence. 
            min = Collections.min(listOfValues);
            max = Collections.max(listOfValues); 
            int minAddress = listOfValues.indexOf(min); 
            int maxAddress = listOfValues.indexOf(max); 
                        
            minCalendar = listOfValidObjects.get(minAddress).getMeasurementDateTime(); 
            maxCalendar = listOfValidObjects.get(maxAddress).getMeasurementDateTime();
            
            
            stationId = data.get(0).getStationID();
            
            
            tairMax = new StatMeasurement( max, maxCalendar, stationId, "TAIR", StatType.MAX);
            tairMin = new StatMeasurement( min, minCalendar, stationId, "TAIR", StatType.MIN);
            average = sum / listOfValues.size();    
            
            
            // RETURN AND CHECK: obsDateTime and StationID
            tairAverage = new StatMeasurement(average, new GregorianCalendar(), stationId, "TAIR", StatType.AVG);
            
            
            // New Implemenation 
            EnumMap<StatType , StatMeasurement> eMap = new EnumMap<StatType, StatMeasurement>(StatType.class); 
            eMap.put(StatType.MIN, tairMin); 
            eMap.put(StatType.MAX, tairMax); 
            eMap.put(StatType.AVG, tairAverage); 
            paramStats.put("tair", eMap); 
            
            
        }
        
        if ( tairName.equalsIgnoreCase("ta9m"))
        {     
            
            // Iterating through arrayList of Time Data 
            for (int index = 0; index < data.size(); ++index)
            {
                
                if (data.get(index).getTa9m().isValid())
                {
                    sum = sum + data.get(index).getTa9m().getValue();
                    listOfValues.add(data.get(index).getTa9m().getValue()); 
                }           
            }
            
            // Computing ta9mMin and ta9mMax
            min = Collections.min(listOfValues);
            max = Collections.max(listOfValues);
            
            stationId = data.get(0).getStationID(); 

            
            ta9mMax = new StatMeasurement( max, maxCalendar, stationId, "TA9M", StatType.MAX);
            ta9mMin = new StatMeasurement( min, minCalendar, stationId, "TA9M", StatType.MIN);
            average = sum / listOfValues.size();
            
            // RETURN AND CHECK: obsDateTime and StationID
            ta9mAverage = new StatMeasurement(average, new GregorianCalendar(), stationId, "TA9M", StatType.AVG);
            
            
            // New Implementation 
            
            EnumMap<StatType , StatMeasurement> eMap = new EnumMap<StatType, StatMeasurement>(StatType.class); 
            eMap.put(StatType.MIN, ta9mMin); 
            eMap.put(StatType.MAX, ta9mMax); 
            eMap.put(StatType.AVG, ta9mAverage); 
            paramStats.put("ta9m", eMap); 
             
        }
        
        // RETURN AND CHECK: See if there needs to be an exception thrown 
        
    }

    /**
     * Compute and fill in the solar radiation-related statistics
     * (solarRadiationMin, solarRadiationMax, solarRadiationAverage, and
     * solarRadiationTotal).
     * <P>
     * Notes:
     * <UL>
     * <LI>Only valid Measurements can be used in these computations
     * <LI>You may assume that every month has at least one valid Measurement
     * </UL>
     */
    private void calculateSolarRadiationStatistics()
    {
        
        ArrayList<Double> listOfValues = new ArrayList<>(); 
        
        // These variables represent the "best so far" for min and max.
        // By setting these these to the largest and smallest possible
        // values, we ensure that the first time a valid Measurement is
        // found, it will replace these values
        double max;
        double min;
        double average; 

        // Accumulator and counter for computing average
        double sum = 0;

        GregorianCalendar minCalendar = new GregorianCalendar();
        GregorianCalendar maxCalendar = new GregorianCalendar();
        
        // FINISH IMPLEMENTATION
        
        
        for (int index = 0; index < data.size(); ++index)
        { 
            
            if (data.get(index).getSolarRadiation().isValid())
            {
                sum = sum + data.get(index).getSolarRadiation().getValue();
                listOfValues.add(data.get(index).getSolarRadiation().getValue());
            }
            
        }
        
        
        // Computing radiationMin and radiationMax
        min = Collections.min(listOfValues); 
        max = Collections.max(listOfValues); 
        average = sum / listOfValues.size();  
        
        stationId = data.get(0).getStationID(); 
        solarRadiationMax = new StatMeasurement( max, maxCalendar, stationId, "SRAD", StatType.MAX );
        solarRadiationMin = new StatMeasurement( min, minCalendar, stationId, "SRAD", StatType.MIN );
        solarRadiationTotal = new StatMeasurement( sum, new GregorianCalendar(), stationId, "SRAD", StatType.TOT); 
        solarRadiationAverage = new StatMeasurement( average, new GregorianCalendar(), 
                                stationId, "SRAD", StatType.AVG); 
        
        
        // New Implementation 
        
        EnumMap<StatType , StatMeasurement> eMap = new EnumMap<StatType, StatMeasurement>(StatType.class); 
        eMap.put(StatType.MIN, solarRadiationMin); 
        eMap.put(StatType.MAX, solarRadiationMax); 
        eMap.put(StatType.AVG, solarRadiationAverage); 
        eMap.put(StatType.TOT, solarRadiationTotal); 
        paramStats.put("srad", eMap); 
        


    }
     

    /**
     * @return solarRadiationAverage : average of solar radiation
     */
    public StatMeasurement getSolarRadiationAverage()
    {
        return paramStats.get("srad").get(StatType.AVG); 
    }

    /** 
     * @return solarRadiationMax : maximum value of solar radiation
     */
    public StatMeasurement getSolarRadiationMax()
    {
        return paramStats.get("srad").get(StatType.MAX); 
    }

    /**
     * @return solarRadiationMin : minimum value of solar radiation
     */
    public StatMeasurement getSolarRadiationMin()
    {
        return paramStats.get("srad").get(StatType.MIN); 
    }

    /**
     * @return solarRadiationTotal : total value of solar radiation
     */
    public StatMeasurement getSolarRadiationTotal() 
    {
        return paramStats.get("srad").get(StatType.TOT); 
    }

    /**
     * @return stationId : the id of the station 
     */
    public String getStationID()
    {
        return stationId; 
    }

    /**
     * @return ta9mAverage : average value of air temperature at 9m
     */
    public StatMeasurement getTa9mAverage()
    {
        return paramStats.get("ta9m").get(StatType.AVG);    
    }
 
    /**
     * @return ta9mMax : maximum value of air temperature at 9m
     */ 
    public StatMeasurement getTa9mMax()
    {
        return paramStats.get("ta9m").get(StatType.MAX);
    }

    /**
     * @return ta9mMin : minimum value of air temperature at 9m
     */
    public StatMeasurement getTa9mMin()
    {
        return paramStats.get("ta9m").get(StatType.MIN);
    }

    /**
     * @return tairAverage : average value of air temperature at 9m
     */
    public StatMeasurement getTairAverage()
    {
        return paramStats.get("tair").get(StatType.AVG);
    }

    /**
     * @return tairMax : maximum value of air temperature
     */
    public StatMeasurement getTairMax()
    {
        return paramStats.get("tair").get(StatType.MAX);
    }

    /**
     * @return tairMin : minimum value of air temperature
     */
    public StatMeasurement getTairMin()
    {
        return paramStats.get("tair").get(StatType.MIN);
    }

 

    /**
     * Describe DayStatistics
     * 
     * @return A string describing the statistics for the day
     */
    public String toString() 
    {
        String tairString = tairMin.getValue() + "\t" + tairMax.getValue() 
                            + "\t" + tairAverage.getValue() + "\n"; 
        String ta9mString = ta9mMin.getValue() + "\t" + ta9mMax.getValue() 
                            + "\t" + ta9mAverage.getValue() + "\n"; 
        String solarString = solarRadiationMin.getValue() + "\t" + solarRadiationMax.getValue() 
                            + "\t" + solarRadiationAverage.getValue() + "\t" + solarRadiationTotal.getValue() + "\n"; 
        String header = "Min" + "\t" + "Max" + "\t" + "Average" + "\t" + "Total" + "\n"; 
        
        String output = header + tairString + ta9mString + solarString;  
        
        return output; 

    }
}
