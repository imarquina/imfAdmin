package iml.imfotografia.xml.feed.struct;

import iml.imfotografia.xml.feed.base.ElementStringBase;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Created by inaki.marquina on 06/07/2016.
 */
public class Width extends ElementStringBase {
    final static Logger logger = Logger.getLogger(Width.class);

    /**
     * CONSTRUCTORS
     */
    public Width() {
        super();
    }

    public Width(String content) {
        super(content);
        this.set_nodeName("width");
    }

    /**
     *
     * @param document
     * @param parentNode
     */
    public void toXml(Document document, Element parentNode){
        logger.debug("Begin");

        Element titleNode = document.createElement(this.get_nodeName());
        titleNode.appendChild(document.createTextNode(this.get_content()));
        parentNode.appendChild(titleNode);

        logger.debug("End");
    }
}
