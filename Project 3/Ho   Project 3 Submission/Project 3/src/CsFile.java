import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;


/**
 * Honestly I have no idea that this class even do different than the base class. 
 * @author Anh-Tu Ngoc
 * @version 340 A.D 
 */
public class CsFile extends CsAbstractFile
{
    /** 
     * @param inFileName , the file name I guess 
     */
    public CsFile(String inFileName)
    { 
        super(inFileName);  
    } 
    
    /**
     * @return super.toString() : I guess it returns the file name. 
     * Or at least it's supposed to. 
     */
    public String getFileName()   // RETURN AND CHECK IMPLEMENTATION 
    { 
        return super.toString();   
    } 


    /**
     * Method to determine whether a file is newer than another 
     * @param inDateTime string representation of a date 
     * @return true if inner method call returns an integer that's not -1. False if otherwise. 
     * @see TimeComparable#newerThan(java.lang.String)
     */
    public boolean newerThan(String inDateTime) throws ParseException
    {
        return compareWithTimeString(inDateTime) > 0;  
    }

    /**
     * @param inDateTime 
     * Compares the file's date with another date 
     * @return true : if compareWithTimeString() returns a negative number. False if otherwise. 
     */
    public boolean olderThan(String inDateTime) throws ParseException
    {
        return compareWithTimeString(inDateTime) == -1 ? true : false;
    }

    /**
     * Determines if a file's date goes before or after another date. \
     * Format the date of the file. 
     * @param inDateTime 
     * @return date.compareTo(other) : A negative or positive number 
     */
    @Override
    public int compareWithTimeString(String inDateTime) throws ParseException
    {
        Date date = new Date(); 
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss z");
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date other = format.parse(inDateTime); 
        return date.compareTo(other);
    }
    
}