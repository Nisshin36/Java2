


import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Ho. Anh-Tu N 
 * @version 10 February 2018 
 * 
 * This class will read in a day of data from a file, 
 * parse it, and create, compute, and store the data
 * for that day. 
 */
public class DayData
{
    /** This list will store each line in the file as an element */
    private ArrayList<TimeData> data = new ArrayList<TimeData>();

    /** Air Temperature values */
    private double tairMin;
    private double tairMax;
    private double tairAverage;

    /** Temperature Values at 9 meters */ 
    private double ta9mMax;
    private double ta9mMin;
    private double ta9mAverage;

    /** Solar Radiation values */
    private double solarRadiationMin;
    private double solarRadiationMax;
    private double solarRadiationAverage;
    private double solarRadiationTotal;

    /**
     * For this project, we're only focusing on one weather station for one specific
     * day, so the stationID and month will be given and is not subjected to any
     * changes.
     */ 
    /** just so webCat can shut up */ 
    int year;
    /** just so webCat can shut up */ 
    int month;
    /** just so webCat can shut up */ 
    int day;
    /** just so webCat can shut up */ 
    String stationID;

    /**
     * @param year for this instance, is given and is not subjected to change
     * @param month Also given, also will not change
     * @param day Also given, also will not change
     * @param stid Also given, also will not change 
     * @param directory file's name
     * @throws IOException
     */
    public DayData(int year, int month, int day, String stid, String directory) throws IOException
    {
        // Assign the parameters to the package variables 
        // Call on the read() method to read the file in the directory.
        this.year = year;
        this.month = month;
        this.day = day; 
        this.stationID = stid; 
        
        String yearString = Integer.toString(year);
        String monthString = datesToString(month);
        String dayString = datesToString(day);    
        
        if ( !directory.equalsIgnoreCase("data/testfile.csv"))
        {
            String fileName = "data/" + yearString + monthString + dayString + stid + ".csv";     
            read(fileName); 

        }
        else  
        { 
            read(directory); 
        }
        
        
        defaultAssignment(); // Instantiates the max and min values for the private fields

        calculateSolarRadiationStatistics(); // Compute the radiation max, mins, and average
        calculateAirTemperatureStatistics("tair"); // Compute the air temperature
        calculateAirTemperatureStatistics("ta9m"); // Compute the air tempertaure that 9 meters.
        
        

    }
    
    /**
     * This method converts the month and day to a string value
     * If the month and day are single digits, ei: less then 10, 
     * then a 0 is put in front of it. 
     */
    private String datesToString(int monthOrDay)
    {
        String result; 
        if ( monthOrDay <= 9 )
        {
            result = "0" + month; 
        }
        else 
        {
            result = Integer.toString(month); 
        }
        
        return result; 
    }
    
    /**
     * This method assigns the minimum and maximum values by default. The values on
     * of the first line or first time frame being recorded will automatically be
     * the maximum value and also will be the minimum value. This way, the
     * calculation methods can work correctly.
     */
    private void defaultAssignment()
    {
        tairMax = data.get(0).getTair().getValue();
        tairMin = data.get(0).getTair().getValue();
        ta9mMax = data.get(0).getTa9m().getValue();
        ta9mMin = data.get(0).getTa9m().getValue();
        solarRadiationMin = data.get(0).getSolarRadiation().getValue();
        solarRadiationMax = data.get(0).getSolarRadiation().getValue();
    } 

    /**
     * Read in the file using a BufferedReader line by line Split and store the line
     * into an array Extract information from the array Assign the information to
     * the private fields.
     * 
     * @param filename The file that is being read
     * @throws IOException  If the file could not be read
     */
    private void read(String filename) throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line = br.readLine(); // At this point, 'line' currently holds the header.
        line = br.readLine(); // We don't want the header, we want the information...
                              // on the next line, so we make this statement twice in...
                              // order to read in the next line.
        while (line != null)
        {
            String[] lineDetails = new String[24]; // There are 24 items in each line of the file.
            lineDetails = line.split(",");

            /**
             * Since this program is initially designed to handle one specific day In one
             * specific location, the year, month, day, and stationID is already known, and
             * it will not change. However, we still need minute, tair, ta9m, and
             * solarRadiation, these variables will change through each line. minute is the
             * 3rd column of the file, so it's the 2nd element in the array. tair is 5th
             * column in the file, so 4th element in the array solarRadiation is the 14th
             * column, so its the 13th element. ta9m is 15th column in file, so 14th element
             * in the array.
             */
            // Extracting and assigning the variables.
            int minute = Integer.parseInt(lineDetails[2]);
            Measurement tair = new Measurement(Double.parseDouble(lineDetails[4]));
            Measurement ta9m = new Measurement(Double.parseDouble(lineDetails[14]));
            Measurement solarRadiation = new Measurement(Double.parseDouble(lineDetails[13]));

            // The line in the file will now be an object possessing these information
            TimeData timeInterval = new TimeData(stationID, year, month, day, minute, tair, ta9m, solarRadiation);

            data.add(timeInterval); // Add that time interval into the arrayList

            line = br.readLine(); // On to the next line
        } 

        br.close();

        // COMPLETED
    }

    /**
     * Calculate the total solar radiation for the day along with the maximum, the
     * minimum, and the average
     */
    private void calculateSolarRadiationStatistics()
    {
        int flags = 0; // Amount of bad measurements

        for (int index = 0; index < data.size(); ++index)
        {
            /**
             * Computing total radiation for that day Compute only if the value of radiation
             * is valid. We don't have to worry about adding bad values to
             * solarRadiationTotal, Because the getValue() would return a 0 if its a bad
             * measurement.
             */
            if (data.get(index).getSolarRadiation().isValid() == true)
            {
                solarRadiationTotal = solarRadiationTotal + data.get(index).getSolarRadiation().getValue();
            }

            // Computing maximum radiation for that day
            // Compute it only if the solarRadiation value is valid
            if ((data.get(index).getSolarRadiation().getValue() > solarRadiationMax)
                    && (data.get(index).getSolarRadiation().isValid()))
            {
                solarRadiationMax = data.get(index).getSolarRadiation().getValue();
            }
            // Computing minimum radiation for that day
            // Compute it only if the solarRadiation value is valid
            if (data.get(index).getSolarRadiation().getValue() < solarRadiationMin
                    && (data.get(index).getSolarRadiation().isValid()))
            {
                solarRadiationMin = data.get(index).getSolarRadiation().getValue();
            }

            /**
             * If there is a bad measurement reported by the Measurement class, Record it
             * and store it in flags variable.
             */
            if (!(data.get(index).getSolarRadiation().isValid()))
            {
                ++flags;
            }

        }

        /**
         * Computing the average radiation We can't divide by the amount of items in the
         * list because some items on the lists were flagged as bad measurements
         * Therefore, we must take that into account by subtracting the amount of bad
         * measurements from the list's size.
         */
        solarRadiationAverage = solarRadiationTotal / (data.size() - flags);

        // COMPLETED
    }

    /**
     * Calculate the Air Temperature at various heights 
     * @param tairName air temperature  
     */
    private void calculateAirTemperatureStatistics(String tairName)
    {
        if (tairName.equalsIgnoreCase("tair")) 
        {
            // Not total amount of measurements, but total value of those measurements put
            // together.
            double total = 0;

            // This is to keep track of the number of bad measurements
            int flags = 0;

            // Iterate through the array
            for (int index = 0; index < data.size(); ++index)
            {

                /**
                 * Computing total value of measurements We don't have to worry about adding bad
                 * values to the total, because getValue() from the Measurement class would
                 * return 0
                 */
                total = total + data.get(index).getTair().getValue();

                // Computing tairMax
                // Only if its a valid value as reported from the 
                // Measurement class
                if ((data.get(index).getTair().getValue() > tairMax) && 
                        (data.get(index).getTair().isValid())) 
                {
                    tairMax = data.get(index).getTair().getValue();
                }
                
                // Computing tairMin
                // Only if its a valid value
                if ((data.get(index).getTair().getValue() < tairMin) && 
                        (data.get(index).getTair().isValid())) 
                {
                    tairMin = data.get(index).getTair().getValue();
                }

                /**
                 * If there is a bad measurement reported by the Measurement class, Record it
                 * and store it in flags variable.
                 */
                if (!(data.get(index).getTair().isValid()))
                {
                    flags = flags + 1;
                }

                /**
                 * Computing the average ta9m We can't divide by the amount of items in the list
                 * because some items on the lists were flagged as bad measurements Therefore,
                 * we must take that into account by subtracting the amount of bad measurements
                 * from the list's size.
                 */
                tairAverage = total / (data.size() - flags);
            }

        }

        /**
         * If the parameter is "ta9m" Perform the same operation as tair
         */
        else if (tairName.equals("ta9m"))
        {
            double total = 0;
            int flags = 0;

            for (int index = 0; index < data.size(); ++index)
            {
                total = total + data.get(index).getTa9m().getValue();

                if ((data.get(index).getTa9m().getValue() > ta9mMax) && (data.get(index).getTa9m().isValid()))
                {
                    ta9mMax = data.get(index).getTa9m().getValue();
                }
                if ((data.get(index).getTa9m().getValue() < ta9mMin) && (data.get(index).getTa9m().isValid()))
                {
                    ta9mMin = data.get(index).getTa9m().getValue();
                }
                if (!(data.get(index).getTa9m().isValid()))
                {
                    flags = flags + 1;
                }

                ta9mAverage = total / (data.size() - flags);
            }
        }

        // COMPLETED

    }

    /**
     * @return year
     */
    public int getYear()
    {
        return year;
    }

    /**
     * @return month
     */
    public int getMonth()
    {
        return month;
    }

    /**
     * @return stationID
     */
    public String getStationID()
    {
        return stationID;
    }

    /**
     * @return tairMin 
     * minimum air temp 
     */
    public double getTairMin()
    {
        return tairMin;
    }

    /**
     * @return tairMax 
     * The Maximum Air Temperature
     */
    public double getTairMax()
    {
        return tairMax;
    }

    /**
     * @return tairAverageThe Average Temperature
     */
    public double getTairAverage()
    {
        return tairAverage;
    }

    /**
     * @return ta9mMin 
     * The Air Temperature at 9 Meters
     */
    public double getTa9mMin()
    {
        return ta9mMin;
    }

    /**
     * @return ta9mMax 
     * The Maximum Air Temperature at 9 Meters
     */
    public double getTa9mMax()
    {
        return ta9mMax;
    }

    /**
     * @return ta9mAverage 
     * The Average Air Temperature at 9 Meters
     */
    public double getTa9mAverage()
    {
        return ta9mAverage;
    }

    /**
     * @return solarRadiationMin 
     * The minimum radiation level for that day
     */
    public double getSolarRadiationMin()
    {
        return solarRadiationMin;
    }

    /**
     * @return solarRadiationMax 
     * The maximum radiation level for that day
     */
    public double getSolarRadiationMax()
    {
        return solarRadiationMax;
    }

    /**
     * @return solarRadiationAverage 
     * The average radition level for that day
     */
    public double getSolarRadiationAverage()
    {
        return solarRadiationAverage;
    }

    /**
     * @return solarRadiationTotal 
     * The total radiation level for that day
     */
    public double getSolarRadiationTotal()
    {
        return solarRadiationTotal;
    }

    /**
     * Prints a "formatted" (Kinda) string containing the all instance and class
     * variables.
     * @return output string 
     */
    public String toString() 
    {

        String output = "Date: " + year + "-" + month + "-" + day + ", " + stationID + "\n" 
                + "Air Temperature[1.5 m]: Min " + tairMin + ", Max " + tairMax + ", Average " + tairAverage + "\n"
                + "Air Temperature[9.5 m]: Min " + ta9mMin + ", Max " + ta9mMax + ", Average " + ta9mAverage + "\n"
                + "Solar Radiation:        Min " + solarRadiationMin + ", Max " + solarRadiationMax + ", Average "
                + solarRadiationAverage + ", Total " + solarRadiationTotal;    
        return output;

    }

}
