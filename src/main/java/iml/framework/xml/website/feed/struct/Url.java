package iml.framework.xml.website.feed.struct;

import iml.framework.xml.website.feed.base.ElementStringBase;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Created by inaki.marquina on 06/07/2016.
 */
public class Url extends ElementStringBase {
    final static Logger logger = Logger.getLogger(Url.class);

    /**
     * CONSTRUCTORS
     */
    public Url() {
        super();
    }

    public Url(String content) {
        super(content);
        this.set_nodeName("url");
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
