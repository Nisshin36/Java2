import java.io.IOException;
import java.text.ParseException;

public class Driver
{
 
    public static void main(String[] args) throws IOException, WrongCopyrightException, ParseException
    {
        
        String[] files = { "data/mesonet/20180102stil.mts",  
                "data/mesonet/20180102okcn.mts", "data/mesonet/20180102okce.mts"}; 
         
 
        try 
        {
            DaysStatistics stats = new DaysStatistics(files);
             stats.findStatistics();
            System.out.println(stats.toString()); 
 
        } 
        catch (Exception ex) 
        {
            System.out.println(ex.getMessage());
        } 

    }

}
