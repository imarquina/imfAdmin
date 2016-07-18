package iml.imfotografia.xml.feed.struct;

import iml.imfotografia.xml.feed.base.ElementBase;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Description extends ElementBase {
    final static Logger logger = Logger.getLogger(Description.class);

    /**
     * CONSTRUCTORS
     */
    public Description() {
        super();
    }

    public Description(String content) {
        super();
        this.set_nodeName("description");
        this.set_content(content);
    }

    /**
     *
     * @param document
     * @param parentNode
     */
    public void toXml(Document document, Element parentNode){
        logger.debug("Begin");

        Element descriptionNode = document.createElement(this.get_nodeName());
        descriptionNode.appendChild(document.createTextNode(this.get_content()));
        parentNode.appendChild(descriptionNode);

        logger.debug("End");
    }
}
