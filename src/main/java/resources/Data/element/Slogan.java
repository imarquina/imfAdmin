package resources.Data.element;

public class Slogan {
    private String _content;

    /**
     * CONSTRUCTORS
     */
    public Slogan() {
    }

    public Slogan(String content) {
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
