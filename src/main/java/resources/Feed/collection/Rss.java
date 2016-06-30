package resources.Feed.collection;

public class Rss {
    private String _version;
    private Channel chanel;

    /**
     * CONSTRUCTORS
     */
    public Rss() {
        chanel = new Channel();
    }

    public Rss(String _version) {
        this._version = _version;
        chanel = new Channel();
    }

    /**
     * GETTER / SETTER
     */
    String get_version() {
        return _version;
    }

    void set_version(String _version) {
        this._version = _version;
    }
}
