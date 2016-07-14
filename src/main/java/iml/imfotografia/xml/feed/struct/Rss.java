package iml.imfotografia.xml.feed.struct;

public class Rss {
    private String _version;
    public Channel chanel;

    /**
     * CONSTRUCTORS
     */
    public Rss() {
        chanel = new Channel();
    }

    public Rss(String _version) {
        this._version = _version;
        this.chanel = new Channel();
    }

    /**
     * GETTER / SETTER
     */
    public String get_version() {
        return this._version;
    }

    public void set_version(String version) {
        this._version = version;
    }

    public void addChanel(Channel chanel){
        this.chanel = chanel;
    }
}
