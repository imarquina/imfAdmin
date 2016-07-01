package iml.imfotografia.xml.data.element;

public class Track {
    private String _src;
    private String _artist;
    private String _name;

    /**
     * CONSTRUCTORS
     */
    public Track() {
    }

    public Track(String src, String artist, String name) {
        this._src = src;
        this._artist = artist;
        this._name = name;
    }

    /**
     * GETTER / SETTER
     */
    public String get_src() {
        return this._src;
    }

    public void set_src(String src) {
        this._src = src;
    }

    public String get_artist() {
        return this._artist;
    }

    public void set_artist(String artist) {
        this._artist = artist;
    }

    public String get_name() {
        return this._name;
    }

    public void set_name(String name) {
        this._name = name;
    }
}
