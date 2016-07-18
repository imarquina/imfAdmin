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

public class LastBuildDate extends ElementDateBase {
    final static Logger logger = Logger.getLogger(LastBuildDate.class);

    /**
     * CONSTRUCTORS
     */
    public LastBuildDate() {
        super();
    }

    public LastBuildDate(String content) throws ParseException {
        super(content);
        this.set_nodeName("lastBuildDate");
    }

    public LastBuildDate(Date content) {
        super(content);
        this.set_nodeName("lastBuildDate");
    }

    /**
     *
     * @param document
     * @param parentNode
     */
    public void toXml(Document document, Element parentNode){
        logger.debug("Begin");

        Element lastBuildDateNode = document.createElement(this.get_nodeName());
        lastBuildDateNode.appendChild(document.createTextNode(this.get_content()));
        parentNode.appendChild(lastBuildDateNode);

        logger.debug("End");
    }
}
