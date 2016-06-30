package resources.Feed.element;

public class Language {
    private String _content;

    /**
     * CONSTRUCTORS
     */
    public Language() {
    }

    public Language(String _content) {
        this._content = _content;
    }

    /**
     * GETTER / SETTER
     */
    public String get_content() {
        return _content;
    }

    public void set_content(String _content) {
        this._content = _content;
    }
}
