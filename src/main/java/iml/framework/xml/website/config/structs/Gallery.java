package iml.framework.xml.website.config.structs;

import iml.framework.xml.website.config.base.CollectionBase;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import java.text.ParseException;

public class Gallery extends CollectionBase {
    final static Logger logger = LogManager.getLogger(Gallery.class);

    /**
     * CONSTRUCOTRS
     */
    public Gallery(){
        super();
        super._nodeName = "gallery";
    }

    /**
     *
     * @return
     */
    public void addImage(Image image) {
        super.addElement(image);
    }

    /**
     *
     * @param node
     * @return
     * @throws ParseException
     */
    public Gallery fromXml(Node node) throws ParseException {
        logger.debug("Begin");
        Gallery collection = new Gallery();

        // get attributes names and values
        NamedNodeMap nodeMap = node.getAttributes();
        for (int i = 0; i < nodeMap.getLength(); i++) {
            Node tempNode = nodeMap.item(i);
            String sAttrName = tempNode.getNodeName();
            if (!sAttrName.equals(null)) sAttrName = sAttrName.trim().toUpperCase();
            String sAttrValue = tempNode.getNodeValue();
            if (!sAttrValue.equals(null)) sAttrValue = sAttrValue.trim();

            logger.info("    Attr name : " + sAttrName + "; Value = " + sAttrValue);

            if (sAttrName.equalsIgnoreCase(ATTRIBUTE_NAME)) {
                collection.set_name(sAttrValue);
            } else if (sAttrName.equalsIgnoreCase(ATTRIBUTE_SRC)) {
                collection.set_src(sAttrValue);
            } else if (sAttrName.equalsIgnoreCase(ATTRIBUTE_TITLE)) {
                collection.set_title(sAttrValue);
            } else if (sAttrName.equalsIgnoreCase(ATTRIBUTE_INFOTEXT)) {
                collection.set_infoText(sAttrValue);
            } else if (sAttrName.equalsIgnoreCase(ATTRIBUTE_KEYWORDS)) {
                collection.set_keywords(sAttrValue);
            } else if (sAttrName.equalsIgnoreCase(ATTRIBUTE_UPDATE)) {
                collection.set_update(sAttrValue);
            } else {
                logger.info("unknow Multimedia property " + sAttrName + ":" + sAttrValue);
            }

            logger.debug("set Multimedia property " + sAttrName + ":" + sAttrValue);
        }

        logger.debug("End");
        return collection;
    }
}
