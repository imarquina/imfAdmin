package iml.imfotografia.xml.config.structs;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import java.text.ParseException;

/**
 * ELEMENTS
 */
public class Image {
    private String _nodeName = "";
    private String _id;

    final static Logger logger = Logger.getLogger(Image.class);

    /**
     * CONSTANTS
     */
    private static final String ATTRIBUTE_ID = "id";

    /**
     * CONSTRUCTORS
     */
    public Image() {
        this._nodeName = "img";
    }

    public Image(String id) {
        this();
        this.set_id(id);
    }

    /**
     * GETTER / SETTER
     */
    public String get_id() {
        return this._id;
    }

    public void set_id(String id) {
        this._id = id;
    }

    public String get_nodeName() {
        return this._nodeName;
    }

    /**
     *
     * @param node
     * @return
     * @throws ParseException
     */
    public Image fromXml(Node node) throws ParseException {
        logger.debug("Begin");
        Image image = new Image();

        // get attributes names and values
        NamedNodeMap nodeMap = node.getAttributes();
        for (int i = 0; i < nodeMap.getLength(); i++) {
            Node tempNode = nodeMap.item(i);
            String sAttrName = tempNode.getNodeName();
            if (!sAttrName.equals(null)) sAttrName = sAttrName.trim().toUpperCase();
            String sAttrValue = tempNode.getNodeValue();
            if (!sAttrValue.equals(null)) sAttrValue = sAttrValue.trim();

            logger.info("    Attr name : " + sAttrName + "; Value = " + sAttrValue);

            if (sAttrName.equalsIgnoreCase(ATTRIBUTE_ID)) {
                image.set_id(sAttrValue);
            } else {
                logger.info("unknow Image property " + sAttrName + ":" + sAttrValue);
            }
            logger.debug("set Image property " + sAttrName + ":" + sAttrValue);
        }
        logger.debug("End");
        return image;
    }

    /**
     *
     * @param document
     * @param parentNode
     */
    public void toXml(Document document, Element parentNode){
        logger.debug("Begin");

        parentNode.setAttribute(ATTRIBUTE_ID, this.get_id());

        logger.debug("End");
    }
}
