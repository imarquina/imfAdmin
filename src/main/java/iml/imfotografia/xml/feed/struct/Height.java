package iml.imfotografia.xml.feed.struct;

/**
 * Created by inaki.marquina on 06/07/2016.
 */
public class Height {
    private String _content;

    /**
     * CONSTRUCTORS
     */
    public Height() {
    }

    public Height(String content) {
        this.set_content(content);
    }

    /**
     * GETTER / SETTER
     */
    public String get_content() {
        return this._content;
    }

    public void set_content(String content) {
        this._content = content;
    }
}
