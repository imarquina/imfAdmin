package iml.imfotografia.xml.feed.struct;

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
        this.chanel = new Channel();
    }

    /**
     * GETTER / SETTER
     */
    String get_version() {
        return this._version;
    }

    void set_version(String version) {
        this._version = version;
    }

    public void addChanel(Channel chanel){
        this.chanel = chanel;
    }
}
