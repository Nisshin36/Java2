import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;


/**
 * Apparently this class is supposed to provide some additional methods for the mesonetTimeFile class 
 * @author Anh-Tu Ngoc
 * @version 20 January 3050
 */
public abstract class CsAbstractFile implements  TimeComparable
{
    /** File, I guess */ 
    protected File file;
    
    /** This object is used to format the date */ 
    protected DateFormat dateFormat;
    
    /** Standardize format for strings */ 
    protected static String dateTimeFormat = "yyyy-MM-dd'T'HH:mm:ss z";
    
    /** Holds the file's name */ 
    protected String fileName;
     
  
    /**  
     * Constructor for the CsAbstractFile class 
     * @param inFileName : the directory of the file 
     */
    protected CsAbstractFile(String inFileName)  // RETURN AND CHECK IMPLEMENTATION
    {
        fileName = inFileName; 
        file = new File(inFileName);  
        
        
        
    }
    
    /**
     * @return file.exists() : a method call that determines whether or not a file directory exists 
     */
    public boolean exists()
    {
        return file.exists(); 
    }
    
    /**
     * @return file.lastModified() : a method call that returns a date 
     */
    public long getDateModified()
    {
        
        // From discussion board 
        return file.lastModified(); 
        
    } 
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()            // RETURN AND CHECK IMPLEMENTATION 
    {
        dateFormat = new SimpleDateFormat(dateTimeFormat);    
        return file.toString();
    }

    /**
     * Abstract method for CsFile
     * @param inDateTimeStr : a string expressing a date
     * @return inDateTimeStr  
     * @throws ParseException
     */
    public abstract  int compareWithTimeString(String inDateTimeStr) throws ParseException;
}
