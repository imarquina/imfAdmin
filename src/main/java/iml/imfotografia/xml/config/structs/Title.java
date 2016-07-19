package iml.imfotografia.xml.config.structs;

public class Title {
    private String _nodeName = "";
    private String _content;

    /**
     * CONSTRUCTORS
     */
    public Title() {
        this._nodeName = "title";
        this._content = "";
    }

    public Title(String content) {
        this();
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

    public String get_nodeName() {
        return this._nodeName;
    }
}
