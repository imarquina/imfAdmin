package iml.imfotografia.xml.config.prueba;

import iml.imfotografia.xml.config.XmlConfig;
import iml.imfotografia.xml.config.interfaces.IConfig;

import org.apache.log4j.Logger;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/**
 * Created by imarquina on 6/7/16.
 */
public class Config implements IConfig {
    private String _title;
    private String _infoText;
    private String _keywords;

    final static Logger logger = Logger.getLogger(XmlConfig.class);

    /**
     * CONSTANTS
     */
    private static final String ATTRIBUTE_TITLE = "TITLE";
    private static final String ATTRIBUTE_INFOTEXT = "INFOTEXT";
    private static final String ATTRIBUTE_KEYWORDS = "KEYWORDS";

    /**
     * CONSTRUCTORS
     */
    public Config() {
        this._title = "";
        this._infoText = "";
        this._keywords = "";
    }

    public Config (Node node){
        this();
        getAttrConfig(node);
    }

    /**
     * GETTER / SETTER
     */
    public String get_infoText() {
        return this._infoText;
    }

    public void set_infoText(String infoText) {
        this._infoText = infoText;
    }

    public String get_keywords() {
        return this._keywords;
    }

    public void set_keywords(String keywords) {
        this._keywords = keywords;
    }

    public String get_title() {
        return this._title;
    }

    public void set_title(String title) {
        this._title = title;
    }

    private void getAttrConfig(Node node){
        // get attributes names and values
        NamedNodeMap nodeMap = node.getAttributes();
        for (int i = 0; i < nodeMap.getLength(); i++) {
            Node tempNode = nodeMap.item(i);
            String sAttrName = tempNode.getNodeName();
            if (!sAttrName.equals(null)) sAttrName = sAttrName.trim().toUpperCase();
            String sAttrValue = tempNode.getNodeValue();
            if (!sAttrValue.equals(null)) sAttrValue = sAttrValue.trim();

            logger.info("    Attr name : " + sAttrName + "; Value = " + sAttrValue);

            if (sAttrName.equalsIgnoreCase(ATTRIBUTE_TITLE)) {
                this.set_title(sAttrValue);
            } else if (sAttrName.equalsIgnoreCase(ATTRIBUTE_INFOTEXT)) {
                this.set_infoText(sAttrValue);
            } else if (sAttrName.equalsIgnoreCase(ATTRIBUTE_KEYWORDS)) {
                this.set_keywords(sAttrValue);
            } else {
                logger.info("unknow Config property " + sAttrName + ":" + sAttrValue);
            }

            logger.debug("set Config property " + sAttrName + ":" + sAttrValue);
        }

        logger.debug("End");
    }

}
