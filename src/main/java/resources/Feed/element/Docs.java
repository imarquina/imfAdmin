package resources.Feed.element;

public class Docs {
    private String _content;

    /**
     * CONSTRUCTORS
     */
    public Docs() {
    }

    public Docs(String content) {
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
