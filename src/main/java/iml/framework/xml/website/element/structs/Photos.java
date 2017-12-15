package iml.framework.xml.website.element.structs;

import java.util.LinkedHashMap;

public class Photos {
    private String _nodeName = "";
    public LinkedHashMap<String, Photo> photo;

    /**
     * GETTER / SETTER
     */
    public String get_nodeName() {
        return this._nodeName;
    }

    /**
     * CONSTRUCTORS
     */
    public Photos() {
        this._nodeName = "photos";
        photo = new LinkedHashMap<String, Photo>();
    }

    /**
     *
     * @param sKey
     * @param photo
     */
    public void addPhoto(String sKey, Photo photo){
        this.photo.put(sKey, photo);
    }
}
