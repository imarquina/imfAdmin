package iml.framework.xml.website.feed.struct;

import iml.framework.xml.website.feed.base.ElementStringBase;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Created by inaki.marquina on 06/07/2016.
 */
public class Category extends ElementStringBase {
    final static Logger logger = Logger.getLogger(Category.class);

    /**
     * CONSTRUCTORS
     */
    public Category() {
        super();
    }

    public Category(String content) {
        super(content);
        this.set_nodeName("category");
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
