package iml.framework.xml.website.feed.struct;

import iml.framework.xml.website.feed.base.ElementDateBase;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.text.ParseException;
import java.util.Date;

public class PubDate extends ElementDateBase {
    final static Logger logger = LogManager.getLogger(PubDate.class);

    /**
     * CONSTRUCTORS
     */
    public PubDate() {
        super();
    }

    public PubDate(String content) throws ParseException {
        super(content);
        this.set_nodeName("pubDate");
    }

    public PubDate(Date content) {
        super(content);
        this.set_nodeName("pubDate");
    }

    /**
     *
     * @param document
     * @param parentNode
     */
    public void toXml(Document document, Element parentNode){
        logger.debug("Begin");

        Element pubDateNode = document.createElement(this.get_nodeName());
        pubDateNode.appendChild(document.createTextNode(this.get_content()));
        parentNode.appendChild(pubDateNode);

        logger.debug("End");
    }
}
