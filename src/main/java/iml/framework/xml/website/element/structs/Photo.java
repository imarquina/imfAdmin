package iml.framework.xml.website.element.structs;

import iml.framework.xml.website.element.base.ElementBase;
import org.apache.log4j.Logger;

import java.util.Date;

public class Photo extends ElementBase {
    final static Logger logger = Logger.getLogger(Photo.class);

    /**
     * CONSTRUCTORES
     */
    public Photo() {
        super();
        this.set_nodeName("img");
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
