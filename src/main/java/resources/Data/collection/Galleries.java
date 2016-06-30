package resources.Data.collection;

import java.util.Hashtable;

public class Galleries {
    private Integer _iKey;
    public Hashtable<Integer, Object> elements;

    /**
     * CONSTRUCTORES
     */
    public Galleries() {
        _iKey = 0;
        elements = new Hashtable<Integer, Object>();
    }

    /**
     *
     * @return
     */
    public Gallery addGallery () {
        Gallery gallery = new Gallery();
        this.elements.put(_iKey++, gallery);

        return gallery;
    }

    /**
     *
     * @return
     */
    public Folder addFolder(){
        Folder folder = new Folder();
        this.elements.put(_iKey++, folder);

        return folder;
    }

    /**
     *
     * @return
     */
    public Multimedia addMultimedia() {
        Multimedia multimedia = new Multimedia();
        this.elements.put(_iKey++, multimedia);

        return multimedia;
    }
}
