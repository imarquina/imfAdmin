package iml.imfotografia.xml.feed.element;

public class ManagingEditor {
    private String _content;

    /**
     * CONSTRUCTORS
     */
    public ManagingEditor() {
    }

    public ManagingEditor(String content) {
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
