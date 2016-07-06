package iml.imfotografia.xml.feed.struct;

public class Link {
    private String _content;

    /**
     * CONSTRUCTORS
     */
    public Link() {
    }

    public Link(String content) {
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
