package util;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

public class PropertiesReader {
    final static Logger logger = Logger.getLogger(String.valueOf(PropertiesReader.class));
    Properties property = new Properties();

    public PropertiesReader() {
        FileInputStream fis;
        try {
            fis = new FileInputStream("src/main/resources/config.properties");
            property.load(fis);
            fis.close();
        } catch (IOException e) {
            logger.info("Properties file is not exist!");
            System.err.println("ERROR:Properties file is not exist!");
        }
    }

    public String getURL() {
        String URL = property.getProperty("URL");
        return URL;
    }

    public String getDriverName() {
        String CHROME_DRIVER_NAME = property.getProperty("CHROME_DRIVER_NAME");
        return CHROME_DRIVER_NAME;
    }

    public String getDriverLocation() {
        String CHROME_DRIVER_LOCATION = property.getProperty("CHROME_DRIVER_LOCATION");
        return CHROME_DRIVER_LOCATION;
    }
}