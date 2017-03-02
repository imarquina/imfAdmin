package iml.imfotografia.arq.utils;

import org.apache.log4j.Logger;

import java.io.*;
import java.util.Properties;

/**
 * Created by imarquina on 9/7/16.
 */
public class Property {
    private static Properties _prop;
    final static Logger logger = Logger.getLogger(Property.class);

    /**
     * CONSTRUCTORS
     */
    public Property(String filename) {
        logger.debug("Begin");

        _prop = new Properties();
        InputStreamReader input = null;

        try {
            InputStream stream = getClass().getClassLoader().getResourceAsStream(filename);
            input = new InputStreamReader(stream, "UTF-8");
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
