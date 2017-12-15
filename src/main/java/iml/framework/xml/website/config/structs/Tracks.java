package iml.framework.xml.website.config.structs;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by imarquina on 2/7/16.
 */
public class Tracks {
    private Integer _iKey;
    private String _nodeName = "";
    private String _name;
    public Map<Integer, Track> track;

    final static Logger logger = Logger.getLogger(Tracks.class);

    /**
     * CONSTANTS
     */
    private static final String ATTRIBUTE_NAME = "name";

    /**
     * CONSTRUCTORS
     */
    public Tracks() {
        this._iKey = 0;
        this._nodeName = "tracks";
        this._name = "";
        this.track = new LinkedHashMap<Integer, Track>();
    }

    public Tracks(String name) {
        this();
        this.set_name(name);
    }

    /**
     * GETTER / SETTER
     */
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
     *s
     * @return
     */
    public void addTrack(Track track){
        this.track.put(_iKey++, track);
    }

    /**
     *
     * @param node
     * @return
     */
    public Tracks fromXml(Node node) {
        logger.debug("Begin");
        Tracks tracks = new Tracks();

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
                tracks.set_name(sAttrValue);
            } else {
                logger.info("unknow Tracks property " + sAttrName + ":" + sAttrValue);
            }

            logger.debug("set Tracks property " + sAttrName + ":" + sAttrValue);
        }

        logger.debug("End");
        return tracks;
    }
    /**
     *
     * @param document
     * @param parentNode
     */
    public void toXml(Document document, Element parentNode){
        logger.debug("Begin");

        parentNode.setAttribute(ATTRIBUTE_NAME, this.get_name());

        for (Map.Entry<Integer, Track> entry1 : this.track.entrySet()) {
            Integer key = entry1.getKey();
            Track value = entry1.getValue();

            Element trackNode = document.createElement(value.get_nodeName());
            value.toXml(document, trackNode);

            parentNode.appendChild(trackNode);
        }

        logger.debug("End");
    }
}
