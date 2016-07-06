package iml.imfotografia.xml.element.structs;

import java.util.LinkedHashMap;

/**
 * Created by inaki.marquina on 01/07/2016.
 */
public class Medias {
    public LinkedHashMap<String, Media> media;

    /**
     * CONSTRUCTORS
     */
    public Medias() {
        media = new LinkedHashMap<String, Media>();
    }

    /**
     *
     * @param sKey
     * @param media
     */
    public void addMedia(String sKey, Media media){
        this.media.put(sKey, media);
    }
}
