package iml.imfotografia.xml.element.structs;

import iml.imfotografia.xml.element.base.ElementBase;

import java.util.Date;

public class Photo extends ElementBase {
    /**
     * CONSTRUCTORES
     */
    public Photo() {
        super();
    }

    public Photo(String id, Integer width, Integer height, String caption, String src,
                    Date update, Date dPublic) {
        this();

        this.set_id(id);
        this.set_width(width);
        this.set_height(height);
        this.set_caption(caption);
        this.set_src(src);
        this.set_update(update);
        this.set_dPublic(dPublic);
    }
}
