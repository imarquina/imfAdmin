package program;

import resources.Data.element.Photo;
import resources.Data.XmlConfig;
import resources.Data.XmlPhotos;

/**
 * Created by inaki.marquina on 30/06/2016.
 */
public class Launcher {
    public void main(String[] args){
        XmlPhotos photos = new XmlPhotos();
        XmlConfig config = new XmlConfig();

        photos.images.photo.put("01", new Photo());
    }
}
