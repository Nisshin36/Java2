import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
//import java.util.Formatter;
import java.util.GregorianCalendar;
import java.util.TimeZone;


/**
 * @author rafal
 * @version 20 March 2018 
 * Facilitating the comparison between StatMeasurement Objects 
 *
 */
public class StatMeasurement extends Measurement implements TimeComparable
{
    private GregorianCalendar dateTimeOfMeasurment;
    private String paramId;
    private StatType statType;
    private String stationId;
    // private static final String NADA = "nada";

    /**
     * constructor
     */
    public StatMeasurement()
    { 
        super();  
    }

    /**
     * @param inValue
     *            value of the measurement
     * @param obsDateTime date observed 
     * @param inStationId station observed 
     * @param inParamId measurement obersved 
     * @param inStatType what the measurement was 
     */
    public StatMeasurement(double inValue, GregorianCalendar obsDateTime, String inStationId, 
            String inParamId, StatType inStatType)  
    {
        super(inValue);
        dateTimeOfMeasurment = obsDateTime; 
        paramId = inParamId;   
        statType = inStatType;   
        stationId = inStationId; 
          
         
    }
    
    /**
     * @return dateTimeOfMeasurment , the time that the measurement was recorded 
     */
    public GregorianCalendar getDateTimeOfMeasurment()
    {
        return dateTimeOfMeasurment; 
    }
    
    /**
     * @param inParamId , the new Parameter Id to be assigned to the field variable 
     */
    public void setParamId(String inParamId)
    {
        paramId = inParamId; 
    }
    
    
    /**
     * @return paramId , the parameter ID 
     */
    public String getParamId()
    {
        return paramId; 
    }
    
    
    /**
     * @param type , new type to be assigned to field variable 
     */
    public void setStatType(StatType type)
    {
        statType = type; 
    }
    
    
    /**
     * @return statType, the type of the statistics, man 
     */
    public StatType getStatType()
    {
        return statType;
    }

    /**
     * Compare this Measurement with another Measurement
     * 
     * @param compareWith
     *            Measurement to compare with
     * @return true if both Measurements are valid AND this is strictly smaller than s OR
     *         if this is valid and s is not valid. False if otherwise      
     */
    public boolean isLessThan(StatMeasurement compareWith)
    {
        if ( (this.isValid()) && (compareWith.isValid()) )  
        {
            return (this.getValue() < compareWith.getValue());
        }
        else 
        {
            if ((this.isValid()) && (!compareWith.isValid()))   
            {
                return true;  
            }
            else 
            {
                return (!this.isValid()) && (!compareWith.isValid());
            }
            
        } 
    }

    /**
     * Compare this Measurement with another Measurement
     * 
     * @param compareWith
     *            Measurement to compare with
     * @return true if both Measurements are valid AND this is strictly larger than s OR
     *         if this is valid and s is not valid. False if otherwise 
     */
    public boolean isGreaterThan(StatMeasurement compareWith)
    {
        if ( (this.isValid()) && (compareWith.isValid()) )
        {
            return this.getValue() > compareWith.getValue(); 
           
        }
        else 
        {
            if ( (this.isValid()) && (!compareWith.isValid()) )
            {
                return true; 
            }
            else 
            {
                return (!this.isValid()) && (!compareWith.isValid());
            }

        }
       
    }

    /**
     * (non-Javadoc)
     * Helped from Choung Le 
     * @see TimeComparable#newerThan(java.lang.String)
     * @param inDateTime string date 
     * @return true if this is newer than the parameter. False if otherwise 
     */
    public boolean newerThan(String inDateTime) throws ParseException
    {
        return  compareWithTimeString(inDateTime) > 0;
       
    }

    /**
     * (non-Javadoc)
     * @param inDateTime string date 
     * @see TimeComparable#olderThan(java.lang.String)
     * @return ( compareWithTimeString(inDateTime) < 0) true or false 
     */
    public boolean olderThan(String inDateTime) throws ParseException
    {
        return ( compareWithTimeString(inDateTime) < 0);
        
    }


    
    /**
     * @param inDateTime string date 
     * @return date.compareTo(other) a number 
     * @throws ParseException
     */
    public int compareWithTimeString(String inDateTime) throws ParseException
    {
        dateTimeOfMeasurment.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = dateTimeOfMeasurment.getTime();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss z");
       
        Date other = df.parse(inDateTime);
        return date.compareTo(other); 
    } 

    /**
     * (non-Javadoc)
     * @see Measurement#toString()
     * @return output : a string format 
     */
    @Override
    public String toString() 
    {
        String output = paramId + "\t" + statType.toString() + "\t" + stationId + "\t" + super.getValue();
        return output;     
           
    }
    
    /**
     * Added in 
     * @return stationId 
     */
    public String getStationId()
    {
        return stationId; 
    }
    
}
