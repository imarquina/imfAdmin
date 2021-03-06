package iml.framework.xml.website;

import iml.framework.xml.website.config.XmlConfig;
import iml.framework.xml.website.element.XmlPhotos;
import iml.framework.xml.website.feed.XmlFeed;
import iml.framework.xml.website.sitemap.XmlSitemap;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.text.ParseException;

/**
 * Created by inaki.marquina on 30/06/2016.
 */
public class Launcher {
    final static Logger logger = LogManager.getLogger(Launcher.class);
    final static String separatorLog = "++++++++++++++++++++++++++++++++++++++++";

    public static void main(String[] args) {
        logger.info("+ Begin +" + separatorLog);

        try {
            String photosXml = Property.readProperty("iml.xml.dir.in") + "photos.xml";
            String configXml = Property.readProperty("iml.xml.dir.in") + "config.xml";

            XmlPhotos photos = new XmlPhotos(photosXml);
            XmlConfig config = new XmlConfig(configXml);
            XmlFeed feed = new XmlFeed(configXml, photosXml);
            XmlSitemap  sitemap = new XmlSitemap(configXml, photosXml);

            photos.writeXml(XmlPhotos.writeMode.both);
            config.writeXml(XmlConfig.writeMode.both);
            feed.writeXml();
            sitemap.writeXml();
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
        } catch (TransformerConfigurationException e) {
            logger.error(e.getStackTrace());
        } catch (TransformerException e) {
            logger.error(e.getStackTrace());
        }

        logger.info("+ End +" + separatorLog);
    }
}
