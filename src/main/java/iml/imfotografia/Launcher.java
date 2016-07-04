package iml.imfotografia;

import iml.imfotografia.xml.data.XmlConfig;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.ParseException;

/**
 * Created by inaki.marquina on 30/06/2016.
 */
public class Launcher {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        /*
        try {
            XmlPhotos photos = new XmlPhotos("./data/xml/in/photos.xml");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        */


        try {
            XmlConfig config = new XmlConfig("./data/xml/in/config.xml");
            //HtmlConfig config = new HtmlConfig("./data/xml/in/config.xml");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
