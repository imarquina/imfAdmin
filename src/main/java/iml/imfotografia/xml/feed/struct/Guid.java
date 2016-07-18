package iml.imfotografia.xml.feed.struct;

import iml.imfotografia.xml.feed.base.ElementBase;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Created by inaki.marquina on 06/07/2016.
 */
public class Guid extends ElementBase {
    final static Logger logger = Logger.getLogger(Guid.class);

    /**
     * CONSTRUCTORS
     */
    public Guid() {
        super();
    }

    public Guid(String content) {
        super();
        this.set_nodeName("guid");
        this.set_content(content);
    }

    /**
     *
     * @param document
     * @param parentNode
     */
    public void toXml(Document document, Element parentNode){
        logger.debug("Begin");

        Element docsNode = document.createElement(this.get_nodeName());
        docsNode.appendChild(document.createTextNode(this.get_content()));
        parentNode.appendChild(docsNode);

        logger.debug("End");
    }
}
