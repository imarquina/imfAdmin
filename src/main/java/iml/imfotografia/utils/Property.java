package iml.imfotografia.utils;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by imarquina on 9/7/16.
 */
public class Property {
    private Properties _prop;
    final static Logger logger = Logger.getLogger(Property.class);

    /**
     * CONSTRUCTORS
     */
    public Property(String filename) {
        logger.debug("Begin");

        _prop = new Properties();
        InputStream input = null;

        try {
            input = getClass().getClassLoader().getResourceAsStream(filename);
            if(input==null){
                logger.error("Sorry, unable to find " + filename);
                return;
            }

            //load a properties file from class path, inside static method
            _prop.load(input);

        } catch (IOException ex) {
            logger.error(ex.getStackTrace());
        } finally{
            if(input!=null){
                try {
                    input.close();
                } catch (IOException e) {
                    logger.error(e.getStackTrace());
                }
            }
        }
        logger.debug("End");
    }

    /**
     *
     * @param key
     * @return
     */
    public String readProperty(String key){
        return _prop.getProperty(key);
    }

    /**
     *
     * @param key
     * @param value
     */
    public void writeProperty(String key, String value){
        _prop.setProperty(key, value);
    }
}
