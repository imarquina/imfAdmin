package iml.imfotografia.xml.feed.struct;

import iml.imfotografia.xml.feed.XmlFeed;
import iml.imfotografia.xml.feed.base.ElementDateBase;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class PubDate extends ElementDateBase {
    final static Logger logger = Logger.getLogger(PubDate.class);

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
