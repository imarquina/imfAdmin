package iml.imfotografia.xml.feed.struct;

import iml.imfotografia.xml.feed.XmlFeed;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Created by inaki.marquina on 06/07/2016.
 */
public class Guid {
    private String _nodeName;
    private String _content;

    final static Logger logger = Logger.getLogger(Guid.class);

    /**
     * CONSTRUCTORS
     */
    public Guid() {
        this._nodeName = "guid";
        this._content = "";
    }

    public Guid(String content) {
        this();
        this.set_content(content);
    }

    /**
     * GETTER / SETTER
     */
    public String get_content() {
        return this._content;
    }

    public void set_content(String content) {
        this._content = content;
    }

    public String get_nodeName() {
        return _nodeName;
    }

    public void toXml(Document document, Element parentNode){
        logger.debug("Begin");

        Element docsNode = document.createElement(this.get_nodeName());
        docsNode.appendChild(document.createTextNode(this.get_content()));
        parentNode.appendChild(docsNode);

        logger.debug("End");
    }
}
