package iml.imfotografia.xml.config.structs;

import java.util.LinkedHashMap;

public class Galleries {
    private Integer _iKey;
    public LinkedHashMap<Integer, Object> elements;

    /**
     * CONSTRUCTORES
     */
    public Galleries() {
        _iKey = 0;
        elements = new LinkedHashMap<Integer, Object>();
    }

    /**
     *
     * @return
     */
    public void addGallery (Gallery gallery) {
        this.elements.put(_iKey++, gallery);
    }

    /**
     *
     * @return
     */
    public void addFolder(Folder folder){
        this.elements.put(_iKey++, folder);
    }

    /**
     *
     * @return
     */
    public void addMultimedia(Multimedia multimedia) {
        this.elements.put(_iKey++, multimedia);
    }
}
