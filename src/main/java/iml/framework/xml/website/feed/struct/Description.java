package iml.framework.xml.website.feed.struct;

import iml.framework.xml.website.feed.base.ElementStringBase;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Description extends ElementStringBase {
    final static Logger logger = LogManager.getLogger(Description.class);

    /**
     * CONSTRUCTORS
     */
    public Description() {
        super();
    }

    public Description(String content) {
        super(content);
        this.set_nodeName("description");
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
