package resources.Sitemap.element;

public class Loc {
    private String _content;

    /**
     * CONSTRUCTORS
     */
    public Loc() {
    }

    public Loc(String content) {
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
