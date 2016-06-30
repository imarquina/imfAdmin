package resources.Feed.element;

public class Title {
    private String _content;

    /**
     * CONSTRUCTORS
     */
    public Title() {
    }

    public Title(String _content) {
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
