package iml.imfotografia.xml.config.structs;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by imarquina on 2/7/16.
 */
public class Tracks {
    private Integer _iKey;
    private String _nodeName = "";
    private String _name;
    public Map<Integer, Track> track;

    /**
     * CONSTRUCTORS
     */
    public Tracks() {
        this._iKey = 0;
        this._nodeName = "tracks";
        this._name = "";
        this.track = new LinkedHashMap<Integer, Track>();
    }

    public Tracks(String name) {
        this();
        this.set_name(name);
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

    public String get_nodeName() {
        return this._nodeName;
    }

    /**
     *s
     * @return
     */
    public void addTrack(Track track){
        this.track.put(_iKey++, track);
    }
}
