/**
 * Abtract class 
 * @author Anh-Tu Ngoc
 * @version March 10 2018 
 */
public abstract class StatisticsAbstract  
{    
    /** Abstract Measurement
     * 
     * @param inParamId : tair srad or ta9m 
     * @return StatMeasurement object
     * @throws WrongParameterIdException
     */
    public abstract StatMeasurement getMinimumDay(String inParamId) throws WrongParameterIdException;
    
    /** Abstract Measurement
     * 
     * @param inParamId : tair srad or ta9m 
     * @return StatMeasurement object 
     * @throws WrongParameterIdException
     */
    public abstract StatMeasurement getMaximumDay(String inParamId) throws WrongParameterIdException;   

}
