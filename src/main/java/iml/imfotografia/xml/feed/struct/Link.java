package iml.imfotografia.xml.feed.struct;

import iml.imfotografia.xml.feed.base.ElementBase;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Link extends ElementBase {
    final static Logger logger = Logger.getLogger(Link.class);

    /**
     * CONSTRUCTORS
     */
    public Link() {
        super();
    }

    public Link(String content) {
        super();
        this.set_nodeName("link");
        this.set_content(content);
    }

    /**
     *
     * @param document
     * @param parentNode
     */
    public void toXml(Document document, Element parentNode){
        logger.debug("Begin");

        Element linkNode = document.createElement(this.get_nodeName());
        linkNode.appendChild(document.createTextNode(this.get_content()));
        parentNode.appendChild(linkNode);

        logger.debug("End");
    }
}
