package iml.imfotografia.xml.data.collection;

import iml.imfotografia.xml.data.element.Track;

import java.util.Hashtable;

/**
 * Created by imarquina on 2/7/16.
 */
public class Tracks {
    private String _name;
    public Hashtable<String, Track> track;

    /**
     * CONSTRUCTORS
     */
    public Tracks() {
        track = new Hashtable<String, Track>();
    }

    /**
     * GETTER / SETTER
     */
    public String get_name() {
        return this._name;
    }

    public void set_name(String name) {
        this._name = name;
    }

    /**
     *s
     * @return
     */
    public Track addTrack(String sKey){
        Track track = new Track();
        this.track.put(sKey, track);

        return track;
    }
}
