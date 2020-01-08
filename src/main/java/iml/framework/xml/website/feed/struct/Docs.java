package iml.framework.xml.website.feed.struct;

import iml.framework.xml.website.feed.base.ElementStringBase;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Docs extends ElementStringBase {
    final static Logger logger = LogManager.getLogger(Docs.class);

    /**
     * CONSTRUCTORS
     */
    public Docs() {
        super();
    }

    public Docs(String content) {
        super(content);
        this.set_nodeName("docs");
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
