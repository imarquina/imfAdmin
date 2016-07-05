package iml.imfotografia.xml.data.element;

/**
 * Created by imarquina on 1/7/16.
 */
public class Video {
    private String _id;

    /**
     * CONSTRUCTORS
     */
    public Video() {

    }

    public Video(String id) {
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
