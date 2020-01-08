package iml.framework.xml.website.feed.struct;

import iml.framework.xml.website.feed.base.ElementStringBase;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Title extends ElementStringBase {
    final static Logger logger = LogManager.getLogger(Title.class);

    /**
     * CONSTRUCTORS
     */
    public Title() {
        super();
    }

    public Title(String content) {
        super(content);
        this.set_nodeName("title");
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
