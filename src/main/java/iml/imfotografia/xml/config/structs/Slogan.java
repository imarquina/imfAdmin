package iml.imfotografia.xml.config.structs;

public class Slogan {
    private String _nodeName = "";
    private String _content;

    /**
     * CONSTRUCTORS
     */
    public Slogan() {
        this._nodeName = "slogan";
        this._content = "";
    }

    public Slogan(String content) {
        this();
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

    public String get_nodeName() {
        return this._nodeName;
    }
}
