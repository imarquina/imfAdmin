/**
 * Created by inaki.marquina on 29/06/2016.
 */
public class Track {
    private String _src;
    private String _artist;
    private String _name;

    /**
     * CONSTRUCTORS
     */
    public Track() {
    }

    public Track(String _src, String _artist, String _name) {
        this._src = _src;
        this._artist = _artist;
        this._name = _name;
    }

    /**
     * GETTER / SETTER
     */
    public String get_src() {
        return _src;
    }

    public void set_src(String _src) {
        this._src = _src;
    }

    public String get_artist() {
        return _artist;
    }

    public void set_artist(String _artist) {
        this._artist = _artist;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }
}
