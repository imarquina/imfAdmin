package iml.imfotografia.xml.config.structs;

/**
 * Created by imarquina on 1/7/16.
 */
public class Video {
    private String _nodeName = "";
    private String _id;

    /**
     * CONSTRUCTORS
     */
    public Video() {
        this._nodeName = "vid";
        this._id = "";
    }

    public Video(String id) {
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
