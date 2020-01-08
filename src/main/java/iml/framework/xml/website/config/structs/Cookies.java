package iml.framework.xml.website.config.structs;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class Cookies {
    private String _nodeName = "";
    private String _content;

    final static Logger logger = LogManager.getLogger(Cookies.class);

    /**
     * CONSTRUCTORS
     */
    public Cookies() {
        this._nodeName = "cookies";
        this._content = "";
    }

    public Cookies(String content) {
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
        return this._nodeName;
    }

    /**
     *
     * @param node
     * @return
     */
    public Cookies fromXml(Node node) {
        logger.debug("Begin");
        Cookies slogan = new Cookies();

        // get attributes names and values
        NamedNodeMap nodeMap = node.getAttributes();
        for (int i = 0; i < nodeMap.getLength(); i++) {
            Node tempNode = nodeMap.item(i);
            String sAttrName = tempNode.getNodeName();
            if (!sAttrName.equals(null)) sAttrName = sAttrName.trim().toUpperCase();
            String sAttrValue = tempNode.getNodeValue();
            if (!sAttrValue.equals(null)) sAttrValue = sAttrValue.trim();

            logger.info("    Attr name : " + sAttrName + "; Value = " + sAttrValue);

            logger.debug("set Cookies property " + sAttrName + ":" + sAttrValue);
        }

        slogan.set_content(node.getFirstChild().getTextContent());

        logger.debug("End");
        return slogan;
    }

    /**
     *
     * @param document
     * @param parentNode
     */
    public void toXml(Document document, Element parentNode){
        logger.debug("Begin");

        parentNode.appendChild(document.createCDATASection(this.get_content()));

        logger.debug("End");
    }
}
