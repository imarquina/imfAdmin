package iml.imfotografia.xml.data.collection;

import iml.imfotografia.xml.data.element.Video;

import java.util.Hashtable;

/**
 * Created by inaki.marquina on 01/07/2016.
 */
public class Videos {
    public Hashtable<String, Video> video;

    /**
     * CONSTRUCTORS
     */
    public Videos() {
        video = new Hashtable<String, Video>();
    }

    /**
     *
     * @return
     */
    public Video addVideo(String sKey){
        Video video = new Video();
        this.video.put(sKey, video);

        return video;
    }
}
