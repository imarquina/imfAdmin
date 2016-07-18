package iml.imfotografia.xml.feed.struct;

import iml.imfotografia.xml.feed.base.ElementBase;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Title extends ElementBase {
    final static Logger logger = Logger.getLogger(Title.class);

    /**
     * CONSTRUCTORS
     */
    public Title() {
        super();
    }

    public Title(String content) {
        super();
        this.set_nodeName("title");
        this.set_content(content);
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
