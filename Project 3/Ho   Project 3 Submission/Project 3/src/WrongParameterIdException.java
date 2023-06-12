
/**
 * Throws Wrong Parameter ID 
 * @author Anh-Tu Ngoc
 * @version March 29 2018 
 */
public class WrongParameterIdException extends Exception
{
    /**
     * Long Number 
     */
    private static final long serialVersionUID = 7394973112258653626L;

    /**
     * Error to print out 
     */
    public WrongParameterIdException()
    {
        super("Invalid parameterID detected");
    } 
    
    /**
     * Print out error message 
     * @param msg error mssg
     */
    public WrongParameterIdException(String msg)
    {
        super(msg + " Invalid parameterID detected");
    }
}
