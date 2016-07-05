package iml.imfotografia;

import iml.imfotografia.xml.data.XmlConfig;
import iml.imfotografia.xml.data.XmlPhotos;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.text.ParseException;

/**
 * Created by inaki.marquina on 30/06/2016.
 */
public class Launcher {
    final static Logger logger = Logger.getLogger(Launcher.class);
    final static String separatorLog = "++++++++++++++++++++++++++++++++++++++++";

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
        try {
            logger.info("+ Begin +" + separatorLog);

            XmlPhotos photos = new XmlPhotos("./data/xml/in/photos.xml");
            XmlConfig config = new XmlConfig("./data/xml/in/config.xml");
            //HtmlConfig config = new HtmlConfig("./data/xml/in/config.xml");

            logger.info("+ End +" + separatorLog);
        } catch (ParseException e) {
            logger.error("Error: " + e.getStackTrace());
            e.printStackTrace();
        }
    }
}
