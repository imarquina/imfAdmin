package resources.Data.collection;

import resources.Data.element.Photo;

import java.util.Hashtable;

public class Photos {
    public Hashtable<String, Photo> photo;

    /**
     * CONSTRUCTORS
     */
    public Photos() {
        photo = new Hashtable<String, Photo>();
    }
}
