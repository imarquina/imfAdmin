package resources.Feed.element;

public class Description {
    private String _content;

    /**
     * CONSTRUCTORS
     */
    public Description() {
    }

    public Description(String _content) {
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
