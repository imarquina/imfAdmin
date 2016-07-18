package iml.imfotografia.xml.feed.struct;

import iml.imfotografia.xml.feed.base.ElementBase;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class WebMaster extends ElementBase {
    final static Logger logger = Logger.getLogger(WebMaster.class);

    /**
     * CONSTRUCTORS
     */
    public WebMaster() {
        super();
    }

    public WebMaster(String content) {
        super();
        this.set_nodeName("webMaster");
        this.set_content(content);
    }

    /**
     *
     * @param document
     * @param parentNode
     */
    public void toXml(Document document, Element parentNode){
        logger.debug("Begin");

        Element webMasterNode = document.createElement(this.get_nodeName());
        webMasterNode.appendChild(document.createTextNode(this.get_content()));
        parentNode.appendChild(webMasterNode);

        logger.debug("End");
    }
}
