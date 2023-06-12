 import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Calendar;
//import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * @author rafal, Anh-Tu Ngoc
 * @version 12th Century BC
 */
public class MesonetTimeFile extends CsFile
{
    /** Arrayist that stores each line of data from a file */ 
    private ArrayList<TimeData> data;
    
    /** No idea what this is */ 
    // private ArrayList<String> paramIds;
    
    /** an object to extract and assign the date from the file */ 
    private HeaderDateTime headerDateTime;

    /** NO idea what this is */ 
    // private static final int NUMBER_OF_PARAMETERS = 0;
    
    /** Positions within the String Array after the line reaches the date header. */ 
    private static final int YEAR = 1;
    
    /** Month is at 2nd index */ 
    private static final int MONTH = 2;
    
    /** Day is at third index */ 
    private static final int DAY = 3;
    
    /** Hour is at fourth index */ 
    private static final int HOUR = 4;
    
    /** Minute is at 5th index */ 
    private static final int MINUTE = 5; 
    
    /** second is at 6th index */ 
    private static final int SECOND = 6;

    /** ParamIDs of the measurements */ 
    private static final String TAIR = "TAIR";
    /** paramID */ 
    private static final String TA9M = "TA9M";
    /** paramID */ 
    private static final String SRAD = "SRAD";
    /** ParamID */ 
    private static final String TIME = "TIME";
    /** paramID */ 
    private static final String STID = "STID";

    /** Initital values of their positions within the string array */ 
    private int tairPosition = -1;
    /** Initual value */ 
    private int ta9mPosition = -1; 
    /** Initial Value */ 
    private int sradPosition = -1; 
    /** Initial Value */ 
    private int minutePosition = -1;
    /** Initial Value */ 
    private int stidPosition = -1;

    
    /** Variable that holds the file's date after it has been constructed by HeaderDateTime */ 
    private GregorianCalendar dateTime;

    
    /** The Internal HeaderDateTime class
     * Assign the dates
     * @author Anh-Tu Ngoc
     *
     */
    class HeaderDateTime
    {
        /** The values assigned by the HeaderDateTime */ 
        public int year;
        /** the month */ 
        public int month;
        /** da day */ 
        public int day;
        /** minute */ 
        public int minute;

        /**
         * The Constructor 
         * @param inYear : the year
         * @param inMonth : the month
         * @param inDay : the day
         * @param inMinute  : the minute 
         */
        HeaderDateTime(int inYear, int inMonth, int inDay, int inMinute)
        {
            year = inYear; 
            month = inMonth;
            day = inDay; 
            minute = inMinute;
        }
    }

    /**
     * Constructor 
     * @param inFileName : the file directory 
     * @throws IOException
     * @throws WrongCopyrightException
     */
    MesonetTimeFile(String inFileName) throws IOException, WrongCopyrightException
    {
        super(inFileName); 
    }
 
    /**
     * Parsing the file 
     * @return data : an ArrayList of TimeData Objects; Each line of data from the file 
     * @throws IOException
     * @throws WrongCopyrightException
     * @throws NumberFormatException
     */
    public ArrayList<TimeData> parseFile() throws IOException, WrongCopyrightException, NumberFormatException 
    { 
        data = new ArrayList<TimeData>();
        
        BufferedReader br = new BufferedReader(new FileReader(super.file));
        String line = br.readLine(); // At this point, 'line' currently holds the header. 
       
        try 
        {
            copyrightIsCorrect(line); 
        }
        catch ( WrongCopyrightException e)
        {
            br.close(); 
            throw new WrongCopyrightException(); 
        }
        
        line = br.readLine();               // At this point, line holds the date header
        parseDateTimeHeader(line); 
       
        
        line = br.readLine();               // At this point, we now reached the heading labels
        parseParamHeader(line); 
        
        
        try 
        {
            line = br.readLine();           // At this point, we finally reach the first row of data 
        }
        catch ( NumberFormatException e)
        {
            br.close(); 
            throw new NumberFormatException(); 
        }
          
        
        while ( line != null )
        {
            parseData(line);   
            line = br.readLine();
        }
         
        br.close(); 
        
        return data; 
        
    } 
    
    
    /**
     * Parsing each line of data in the file 
     * @param line : the line of data 
     */
    private void parseData(String line)
    {
        if (line != null)
        {
            String[] input = line.trim().split("\\s+"); 
 
            
            TimeData timeFrame = new TimeData(input[stidPosition], 
                    headerDateTime.year, 
                    headerDateTime.month, headerDateTime.day,
                    Integer.parseInt(input[minutePosition]),
                    new Measurement(Double.parseDouble(input[tairPosition])),
                    new Measurement(Double.parseDouble(input[ta9mPosition])),
                    new Measurement(Double.parseDouble(input[sradPosition])));
            
           
            data.add(timeFrame); 
  
        }

        
    }

    /**
     * Parsing the labels 
     * @param inParamStr : tair srad or ta9m 
     */
    private void parseParamHeader(String inParamStr)
    {
        String[] input = inParamStr.trim().split("\\s+");
        
        for ( int index = 0; index < input.length; ++index)
        {
            if (input[index].equalsIgnoreCase(TAIR))
            {
                tairPosition = index; 
            }
            if (input[index].equalsIgnoreCase(TA9M))
            {
                ta9mPosition = index; 
            }
            if (input[index].equalsIgnoreCase(SRAD))
            {
                sradPosition = index; 
            }
            if (input[index].equalsIgnoreCase(TIME))
            {
                minutePosition = index; 
            }
            if (input[index].equalsIgnoreCase(STID))
            {
                stidPosition = index; 
            }
        }
     
    }

    /**
     * Checking if the copyright signature is correct 
     * @param inCopyrightStr : the line header
     * @return
     */
    private void copyrightIsCorrect(String inCopyrightStr) throws WrongCopyrightException
    {
        if ( !inCopyrightStr.startsWith("101" )) 
        {
            if ( !inCopyrightStr.startsWith("  101" ) )
            {
                throw new WrongCopyrightException();                 
            } 

        }
    }

    /**
     * Extract and assign date values to the HeaderDateTime constructor 
     * @param inHeader , a string date 
     */
    void parseDateTimeHeader(String inHeader)
    {
        String[] timeHeader = inHeader.trim().split("\\s"); 
        int year = Integer.parseInt(timeHeader[YEAR]); 
        int month = Integer.parseInt(timeHeader[MONTH]); 
        int day =  Integer.parseInt(timeHeader[DAY]); 
        int hour = Integer.parseInt(timeHeader[HOUR]);
        int minute = Integer.parseInt(timeHeader[MINUTE]);
        int second = Integer.parseInt(timeHeader[SECOND]);
        headerDateTime = new HeaderDateTime(year, month, day, minute); 
        
        dateTime = new GregorianCalendar(year, month, day, hour, minute, second); 
    }

    /**
     * @return formattedData : String Date from file without conversion 
     */
    String getStarDateTimeStringFromFile()
    { 
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss z");
        String formattedDate = sdf.format(dateTime.getTime()); 
        return formattedDate; 
    }

    /**
     * @return formattedData : String Date converted 
     */
    String getDateTimeString() 
    {
        
        dateTime.setTimeZone(TimeZone.getTimeZone("UTC"));  
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss z");
       
        String formattedDate = sdf.format(dateTime.getTime()); 
       
        return formattedDate; 
    }

}
