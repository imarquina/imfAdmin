package iml.imfotografia.xml.sitemap.element;

import iml.imfotografia.xml.interfaces.IXmlNode;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Loc implements IXmlNode {
    private String _nodeName;
    private String _content;

    final static Logger logger = Logger.getLogger(Loc.class);

    /**
     * CONSTRUCTORS
     */
    public Loc() {
        this._nodeName = "loc";
        this._content = "";
    }

    public Loc(String content) {
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

        Element pubDateNode = document.createElement(this.get_nodeName());
        pubDateNode.appendChild(document.createTextNode(this.get_content()));
        parentNode.appendChild(pubDateNode);

        logger.debug("End");
    }
}
