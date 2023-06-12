/**
 * This class throws an exception for wrong copyright statements 
 * @author Anh-Tu Ngoc
 * @version March 29 2018 
 */
public class WrongCopyrightException extends Exception
{
    /**
     * I don't know what this is 
     */
    private static final long serialVersionUID = -3352808845495117276L;

    /**
     * Method to print error message 
     */
    public WrongCopyrightException()
    {
        super("Invalid copyright detected");
    }

}
