package iml.imfotografia.xml.element.structs;

import iml.imfotografia.xml.element.base.ElementBase;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

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

    /**
     * PUBLIC METHODS
     */

    /**
     *
     * @param document
     * @param parentNode
     */
    public void toXml(Document document, Element parentNode){
        logger.debug("Begin");

        Element nodeImage = document.createElement(this.get_nodeName());

        nodeImage.setAttribute("width", this.get_width().toString());
        nodeImage.setAttribute("height", this.get_height().toString());
        nodeImage.setAttribute("caption", this.get_caption());
        nodeImage.setAttribute("src", this.get_src());
        nodeImage.setAttribute("dx", this.get_dx());
        nodeImage.setAttribute("dy", this.get_dy());
        nodeImage.setAttribute("linktext", this.get_linkText());
        nodeImage.setAttribute("linkurl", this.get_linkUrl());
        nodeImage.setAttribute(ATTRIBUTE_INFOTEXT, this.get_infoText());
        nodeImage.setAttribute("format", this.get_format());
        if (this.get_price()>0)
            nodeImage.setAttribute("price", this.get_price().toString());
        nodeImage.setAttribute("stock", this.get_stock());
        nodeImage.setAttribute("update", this.get_updateString());
        nodeImage.setAttribute("public", this.get_dPublicString());

        parentNode.appendChild(nodeImage);

        logger.debug("End");
    }
}
