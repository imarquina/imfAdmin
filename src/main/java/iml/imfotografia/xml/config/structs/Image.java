package iml.imfotografia.xml.config.structs;

import iml.imfotografia.xml.config.base.ElementBase;
import iml.imfotografia.xml.config.interfaces.IElement;
import org.apache.log4j.Logger;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import java.text.ParseException;

/**
 * ELEMENTS
 */
public class Image extends ElementBase implements IElement {
    final static Logger logger = Logger.getLogger(Image.class);

    public Image() {
        super();
        this.set_nodeName("img");
    }

    /**
     *
     * @param node
     * @return
     * @throws ParseException
     */
    public Image fromXml(Node node) throws ParseException {
        logger.debug("Begin");
        Image element = new Image();

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
                element.set_id(sAttrValue);
            } else {
                logger.info("unknow Image property " + sAttrName + ":" + sAttrValue);
            }
            logger.debug("set Image property " + sAttrName + ":" + sAttrValue);
        }
        logger.debug("End");
        return element;
    }
}
