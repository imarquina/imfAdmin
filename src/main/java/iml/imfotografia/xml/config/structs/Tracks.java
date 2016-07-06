package iml.imfotografia.xml.config.structs;

import java.util.LinkedHashMap;

/**
 * Created by imarquina on 2/7/16.
 */
public class Tracks {
    private Integer _iKey;
    private String _name;
    public LinkedHashMap<Integer, Track> track;

    /**
     * CONSTRUCTORS
     */
    public Tracks() {
        this._iKey = 0;
        track = new LinkedHashMap<Integer, Track>();
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
    public void addTrack(Track track){
        this.track.put(_iKey++, track);
    }
}
