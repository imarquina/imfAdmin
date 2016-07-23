package iml.imfotografia.xml.config.structs;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import java.text.ParseException;

/**
 * Created by imarquina on 1/7/16.
 */
public class Video {
    private String _nodeName = "";
    private String _id;

    final static Logger logger = Logger.getLogger(Video.class);

    /**
     * CONSTANTS
     */
    private static final String ATTRIBUTE_ID = "id";

    /**
     * CONSTRUCTORS
     */
    public Video() {
        this._nodeName = "vid";
        this._id = "";
    }

    public Video(String id) {
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
    public Video fromXml(Node node) throws ParseException {
        logger.debug("Begin");
        Video video = new Video();

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
                video.set_id(sAttrValue);
            } else {
                logger.info("unknow Video property " + sAttrName + ":" + sAttrValue);
            }

            logger.debug("set Video property " + sAttrName + ":" + sAttrValue);
        }
        logger.debug("End");
        return video;
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
