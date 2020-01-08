package iml.framework.xml.website.config.structs;

import iml.framework.xml.website.config.base.CollectionBase;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import java.text.ParseException;

public class Multimedia extends CollectionBase {
    final static Logger logger = LogManager.getLogger(Multimedia.class);

    /**
     * CONSTRUCOTRS
     */
    public Multimedia(){
        super();
        super._nodeName = "multimedia";
    }

    /**
     *
     * @return
     */
    public void addVideo (Video video) {
        super.addElement(video);
    }

    /**
     *
     * @param node
     * @return
     * @throws ParseException
     */
    public Multimedia fromXml(Node node) throws ParseException {
        logger.debug("Begin");
        Multimedia collection = new Multimedia();

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
