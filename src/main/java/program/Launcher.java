package program;

import resources.Data.collection.*;
import resources.Data.element.*;
import resources.Data.XmlConfig;
import resources.Data.XmlPhotos;

/**
 * Created by inaki.marquina on 30/06/2016.
 */
public class Launcher {
    public void main(String[] args){
        XmlPhotos photos = new XmlPhotos();

        photos.images.photo.put("01", new Photo());



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
    }
}
