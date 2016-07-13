package iml.imfotografia;

import iml.imfotografia.xml.feed.XmlFeed;
import iml.imfotografia.xml.sitemap.XmlSitemap;
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
            String photosXml = "./data/xml/in/photos.xml";
            String configXml = "./data/xml/in/config.xml";

            //XmlPhotos photos = new XmlPhotos(photosXml);
            //XmlConfig config = new XmlConfig(configXml);
            XmlFeed feed = new XmlFeed(configXml, photosXml);
            XmlSitemap  sitemap = new XmlSitemap(configXml);

            System.out.println();
        } catch (ParserConfigurationException e) {
            logger.error(e.getStackTrace());
        } catch (ParseException e) {
            logger.error(e.getStackTrace());
        } catch (SAXException e) {
            logger.error(e.getStackTrace());
        } catch (XPathExpressionException e) {
            logger.error(e.getStackTrace());
        } catch (IOException e) {
            logger.error(e.getStackTrace());
        }

        logger.info("+ End +" + separatorLog);
    }
}
