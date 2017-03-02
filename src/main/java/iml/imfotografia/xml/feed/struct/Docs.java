package iml.imfotografia.xml.feed.struct;

import iml.imfotografia.xml.feed.base.ElementStringBase;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Docs extends ElementStringBase {
    final static Logger logger = Logger.getLogger(Docs.class);

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
