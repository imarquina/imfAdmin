package iml.imfotografia.xml.data.element;

public class Title {
    private String _content;

    /**
     * CONSTRUCTORS
     */
    public Title() {
    }

    public Title(String content) {
        this._content = content;
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
