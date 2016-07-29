package iml.imfotografia.xml.config.structs;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class Title {
    private String _nodeName = "";
    private String _content;

    final static Logger logger = Logger.getLogger(Title.class);

    /**
     * CONSTRUCTORS
     */
    public Title() {
        this._nodeName = "title";
        this._content = "";
    }

    public Title(String content) {
        this();
        this._content = content;
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
    public Title fromXml(Node node) {
        logger.debug("Begin");
        Title title = new Title();

        // get attributes names and values
        NamedNodeMap nodeMap = node.getAttributes();
        for (int i = 0; i < nodeMap.getLength(); i++) {
            Node tempNode = nodeMap.item(i);
            String sAttrName = tempNode.getNodeName();
            if (!sAttrName.equals(null)) sAttrName = sAttrName.trim().toUpperCase();
            String sAttrValue = tempNode.getNodeValue();
            if (!sAttrValue.equals(null)) sAttrValue = sAttrValue.trim();

            logger.info("    Attr name : " + sAttrName + "; Value = " + sAttrValue);

            logger.debug("set Title property " + sAttrName + ":" + sAttrValue);
        }

        title.set_content(node.getFirstChild().getTextContent());

        logger.debug("End");
        return title;
    }

    /**
     *
     * @param document
     * @param parentNode
     */
    public void toXml(Document document, Element parentNode){
        logger.debug("Begin");

        parentNode.appendChild(document.createTextNode(this.get_content()));

        logger.debug("End");
    }
}
