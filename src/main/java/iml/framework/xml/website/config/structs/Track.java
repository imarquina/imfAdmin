package iml.framework.xml.website.config.structs;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class Track {
    private String _nodeName = "";
    private String _src;
    private String _artist;
    private String _name;

    final static Logger logger = Logger.getLogger(Track.class);

    /**
     * CONSTANTS
     */
    private static final String ATTRIBUTE_SRC = "src";
    private static final String ATTRIBUTE_ARTIST = "artist";
    private static final String ATTRIBUTE_NAME = "name";

    /**
     * CONSTRUCTORS
     */
    public Track() {
        this._nodeName = "track";
        this._src = "";
        this._artist = "";
        this._name = "";
    }

    public Track(String src, String artist, String name) {
        this();
        this._src = src;
        this._artist = artist;
        this._name = name;
    }

    /**
     * GETTER / SETTER
     */
    public String get_src() {
        return this._src;
    }

    public void set_src(String src) {
        this._src = src;
    }

    public String get_artist() {
        return this._artist;
    }

    public void set_artist(String artist) {
        this._artist = artist;
    }

    public String get_name() {
        return this._name;
    }

    public void set_name(String name) {
        this._name = name;
    }

    public String get_nodeName() {
        return this._nodeName;
    }

    /**
     *
     * @param node
     * @return
     */
    public Track fromXml(Node node) {
        logger.debug("Begin");
        Track track = new Track();

        // get attributes names and values
        NamedNodeMap nodeMap = node.getAttributes();
        for (int i = 0; i < nodeMap.getLength(); i++) {
            Node tempNode = nodeMap.item(i);
            String sAttrName = tempNode.getNodeName();
            if (!sAttrName.equals(null)) sAttrName = sAttrName.trim().toUpperCase();
            String sAttrValue = tempNode.getNodeValue();
            if (!sAttrValue.equals(null)) sAttrValue = sAttrValue.trim();

            logger.info("    Attr name : " + sAttrName + "; Value = " + sAttrValue);

            if (sAttrName.equalsIgnoreCase(ATTRIBUTE_SRC)) {
                track.set_src(sAttrValue);
            } else if (sAttrName.equalsIgnoreCase(ATTRIBUTE_ARTIST)) {
                track.set_artist(sAttrValue);
            } else if (sAttrName.equalsIgnoreCase(ATTRIBUTE_NAME)) {
                track.set_name(sAttrValue);
            } else {
                logger.info("unknow Track property " + sAttrName + ":" + sAttrValue);
            }

            logger.debug("set Track property " + sAttrName + ":" + sAttrValue);
        }

        logger.debug("End");
        return track;
    }

    /**
     *
     * @param document
     * @param parentNode
     */
    public void toXml(Document document, Element parentNode){
        logger.debug("Begin");

        parentNode.setAttribute(ATTRIBUTE_SRC, this.get_src());
        parentNode.setAttribute(ATTRIBUTE_ARTIST, this.get_artist());
        parentNode.setAttribute(ATTRIBUTE_NAME, this.get_name());

        logger.debug("End");
    }
}
