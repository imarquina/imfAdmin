package iml.imfotografia.xml.feed.struct;

public class Language {
    private String _content;

    /**
     * CONSTRUCTORS
     */
    public Language() {
    }

    public Language(String content) {
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