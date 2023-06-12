/**
 * Wrong Time Zone exception class
 * @author Anh-Tu Ngoc
 * @version 2010 January 15 
 */
public class WrongTimeZoneException extends Exception
{
    /**
     * Long Signature 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Prints out error message. 
     */
    public WrongTimeZoneException()
    {
        super("Invalid time zone detected, should be UTC");
        // default implementation ignored
    }
}
