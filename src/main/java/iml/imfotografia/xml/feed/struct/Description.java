package iml.imfotografia.xml.feed.struct;

public class Description {
    private String _content;

    /**
     * CONSTRUCTORS
     */
    public Description() {
    }

    public Description(String content) {
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
