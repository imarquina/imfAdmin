package iml.imfotografia.xml.data.collection;

import iml.imfotografia.xml.data.element.Media;

import java.util.Hashtable;

/**
 * Created by inaki.marquina on 01/07/2016.
 */
public class Medias {
    public Hashtable<String, Media> video;

    /**
     * CONSTRUCTORS
     */
    public Medias() {
        video = new Hashtable<String, Media>();
    }

    /**
     *
     * @return
     */
    public Media addVideo(String sKey){
        Media video = new Media();
        this.video.put(sKey, video);

        return video;
    }
}
