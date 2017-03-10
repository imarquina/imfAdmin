package iml.framework.xml.website.feed.struct;

import iml.framework.xml.website.feed.base.ElementStringBase;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class WebMaster extends ElementStringBase {
    final static Logger logger = Logger.getLogger(WebMaster.class);

    /**
     * CONSTRUCTORS
     */
    public WebMaster() {
        super();
    }

    public WebMaster(String content) {
        super(content);
        this.set_nodeName("webMaster");
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
