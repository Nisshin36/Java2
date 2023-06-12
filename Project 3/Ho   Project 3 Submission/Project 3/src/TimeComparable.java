import java.text.ParseException;

/**
 * I have no idea what this does 
 * @author Anh-Tu Ngoc
 * @version March 24 2018 
 */
public interface TimeComparable 
{
    /** 
     * 
     * @param inDateTimeStr date string 
     * @return boolean true if it is 
     * @throws ParseException
     */
    boolean newerThan(String inDateTimeStr) throws ParseException;
    
    /** 
     * @param inDateTimeStr Date string 
     * @return boolean true if it is 
     * @throws ParseException
     */
    boolean olderThan(String inDateTimeStr) throws ParseException;
}