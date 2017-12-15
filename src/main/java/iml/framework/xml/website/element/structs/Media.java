package iml.framework.xml.website.element.structs;

import iml.framework.xml.website.element.base.ElementBase;
import org.apache.log4j.Logger;

import java.util.Date;

public class Media extends ElementBase {
    final static Logger logger = Logger.getLogger(Media.class);

    /**
     * CONSTANTS
     */
    private static final String ATTRIBUTE_INFOTEXT = "infotext";

    /**
     * CONSTRUCTORES
     */
    public Media() {
        super();
        this.set_nodeName("vid");
    }

    public Media(String id, Integer width, Integer height, String caption, String src,
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
