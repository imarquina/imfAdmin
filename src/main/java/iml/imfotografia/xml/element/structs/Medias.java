package iml.imfotografia.xml.element.structs;

import java.util.LinkedHashMap;

/**
 * Created by inaki.marquina on 01/07/2016.
 */
public class Medias {
    private String _nodeName = "";
    public LinkedHashMap<String, Media> media;

    /**
     * GETTER / SETTER
     */
    public String get_nodeName() {
        return this._nodeName;
    }

    /**
     * CONSTRUCTORS
     */
    public Medias() {
        this._nodeName = "videos";
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
