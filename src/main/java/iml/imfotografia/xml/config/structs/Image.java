package iml.imfotografia.xml.config.structs;

/**
 * ELEMENTS
 */
public class Image {
    private String _nodeName = "";
    private String _id;

    /**
     * CONSTRUCTORS
     */
    public Image() {
        this._nodeName = "img";
    }

    public Image(String id) {
        this();
        this.set_id(id);
    }

    /**
     * GETTER / SETTER
     */
    public String get_id() {
        return this._id;
    }

    public void set_id(String id) {
        this._id = id;
    }

    public String get_nodeName() {
        return this._nodeName;
    }
}
