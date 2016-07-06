package iml.imfotografia;

import iml.imfotografia.xml.config.XmlConfig;
import iml.imfotografia.xml.element.XmlPhotos;
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

    public static void main(String[] args) {
        logger.info("+ Begin +" + separatorLog);

        try {
            XmlPhotos photos = new XmlPhotos("./data/xml/in/photos.xml");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //xborrar_XmlConfig config = new xborrar_XmlConfig("./data/xml/in/config.xml");
        try {
            XmlConfig config = new XmlConfig("./data/xml/in/config.xml");
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        logger.info("+ End +" + separatorLog);

        ////HtmlConfig config = new HtmlConfig("./config/xml/in/config.xml");
        //logger.info(Crypto.getMD5("Bilbao"));
    }
}
