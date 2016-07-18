package iml.imfotografia.xml.feed.struct;

import iml.imfotografia.xml.feed.XmlFeed;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Description {
    private String _nodeName;
    private String _content;

    final static Logger logger = Logger.getLogger(Description.class);

    /**
     * CONSTRUCTORS
     */
    public Description() {
        this._content = "";
        this._nodeName = "description";
    }

    public Description(String content) {
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

    /**
     *
     * @param document
     * @param parentNode
     */
    public void toXml(Document document, Element parentNode){
        logger.debug("Begin");

        Element descriptionNode = document.createElement(this.get_nodeName());
        descriptionNode.appendChild(document.createTextNode(this.get_content()));
        parentNode.appendChild(descriptionNode);

        logger.debug("End");
    }
}
