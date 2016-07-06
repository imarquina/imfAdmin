package iml.imfotografia.xml.element.structs;

import java.util.LinkedHashMap;

public class Photos {
    public LinkedHashMap<String, Photo> photo;

    /**
     * CONSTRUCTORS
     */
    public Photos() {
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
