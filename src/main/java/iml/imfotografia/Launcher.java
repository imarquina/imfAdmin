package iml.imfotografia;

import iml.imfotografia.xml.data.XmlConfig;
import org.xml.sax.SAXException;
import iml.imfotografia.xml.data.XmlPhotos;

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
        } catch (ParseException e) {
            e.printStackTrace();
        }

        /*
        XmlConfig config = new XmlConfig("title", "infotext", "keywords");

        Galleries cnfGll1 = config.addGalleries();
        Folder gll1Fld1 = cnfGll1.addFolder();
        Gallery gll1Gal1 = cnfGll1.addGallery();
        Folder gll1Fld2 = cnfGll1.addFolder();
        Folder gll1Fld3 = cnfGll1.addFolder();
        Multimedia gll1Mul1 = cnfGll1.addMultimedia();

        Folder cnfFld1 = config.addFolder();
        Gallery fld1Gal1 = cnfFld1.addGallery();

        Section cnfSec1 = config.addSection();
        Section cnfSec2 = config.addSection();
        Section cnfSec3 = config.addSection();

        config.elements.put(6, new Title("iñaki marquina"));
        config.elements.put(6, new Slogan("fotografia"));

        Track cnfTrc1 = config.addTrack();

        config.elements.put(8, new ContactForm("cuenta@dominio.es", "THIS MESSAGE HAS BEEN SENT FROM YOUR WEBSITE: "));
        */
    }
}
