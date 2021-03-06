package iml.framework.xml.website.config.structs;

import iml.framework.xml.website.config.base.ElementBase;
import iml.framework.xml.website.config.interfaces.IElement;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import java.text.ParseException;

/**
 * Created by imarquina on 1/7/16.
 */
public class Video extends ElementBase implements IElement {
    final static Logger logger = LogManager.getLogger(Video.class);

    public Video() {
        super();

        this.set_nodeName("vid");
    }

    /**
     *
     * @param node
     * @return
     * @throws ParseException
     */
    public Video fromXml(Node node) throws ParseException {
        logger.debug("Begin");
        Video element = new Video();

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
