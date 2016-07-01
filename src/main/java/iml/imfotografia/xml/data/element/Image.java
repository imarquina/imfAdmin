package iml.imfotografia.xml.data.element;

/**
 * ELEMENTS
 */
public class Image {
    private String _id;

    /**
     * CONSTRUCTORS
     */
    public Image() {

    }

    public Image(String id) {
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
}
