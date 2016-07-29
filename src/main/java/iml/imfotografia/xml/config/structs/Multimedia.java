package iml.imfotografia.xml.config.structs;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class Multimedia {
    private Integer _iKey;
    private String _nodeName = "";
    private String _name;
    private String _src;
    private String _title;
    private String _infoText;
    private String _keywords;
    private Date _update;
    private DateFormat _dateFormatIn = new SimpleDateFormat("yyyymmdd");
    private DateFormat _dateFormatOut = new SimpleDateFormat("yyyymmdd");

    public Map<Integer, Object> elements;

    final static Logger logger = Logger.getLogger(Multimedia.class);

    /**
     * CONSTANTS
     */
    private static final String ATTRIBUTE_NAME = "name";
    private static final String ATTRIBUTE_SRC = "src";
    private static final String ATTRIBUTE_TITLE = "title";
    private static final String ATTRIBUTE_INFOTEXT = "infotext";
    private static final String ATTRIBUTE_KEYWORDS = "keywords";
    private static final String ATTRIBUTE_UPDATE = "update";

    /**
     * CONSTRUCTORES
     */
    public Multimedia() {
        this._iKey = 0;
        this._nodeName = "multimedia";
        this.elements = new LinkedHashMap<Integer, Object>();
    }

    public Multimedia(String name, String src, String title, String infotext,
                      String keywords, Date update) {
        this();
        this.set_name(name);
        this.set_src(src);
        this.set_title(title);
        this.set_infoText(infotext);
        this.set_keywords(keywords);
        this.set_update(update);

        elements = new LinkedHashMap<Integer, Object>();
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

    public String get_src() {
        return this._src;
    }

    public void set_src(String src) {
        this._src = src;
    }

    public String get_title() {
        return this._title;
    }

    public void set_title(String title) {
        this._title = title;
    }

    public String get_infoText() {
        return this._infoText;
    }

    public void set_infoText(String infotext) {
        this._infoText = infotext;
    }

    public String get_keywords() {
        return this._keywords;
    }

    public void set_keywords(String keywords) {
        this._keywords = keywords;
    }

    public Date get_update() {
        return this._update;
    }

    public String get_updateString() {
        if(this._update != null)
            return _dateFormatOut.format(this._update);
        else return "";
    }

    public void set_update(String update) throws ParseException {
        this._update = _dateFormatIn.parse(update);
    }

    public void set_update(Date update) {
        this._update = update;
    }

    public String get_nodeName() {
        return this._nodeName;
    }

    /**
     *
     * @return
     */
    public void addVideo (Video video) {
        this.elements.put(_iKey++, video);
    }

    /**
     *
     * @param node
     * @return
     * @throws ParseException
     */
    public Multimedia fromXml(Node node) throws ParseException {
        logger.debug("Begin");
        Multimedia multimedia = new Multimedia();

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
                multimedia.set_name(sAttrValue);
            } else if (sAttrName.equalsIgnoreCase(ATTRIBUTE_SRC)) {
                multimedia.set_src(sAttrValue);
            } else if (sAttrName.equalsIgnoreCase(ATTRIBUTE_TITLE)) {
                multimedia.set_title(sAttrValue);
            } else if (sAttrName.equalsIgnoreCase(ATTRIBUTE_INFOTEXT)) {
                multimedia.set_infoText(sAttrValue);
            } else if (sAttrName.equalsIgnoreCase(ATTRIBUTE_KEYWORDS)) {
                multimedia.set_keywords(sAttrValue);
            } else if (sAttrName.equalsIgnoreCase(ATTRIBUTE_UPDATE)) {
                multimedia.set_update(sAttrValue);
            } else {
                logger.info("unknow Multimedia property " + sAttrName + ":" + sAttrValue);
            }

            logger.debug("set Multimedia property " + sAttrName + ":" + sAttrValue);
        }

        logger.debug("End");
        return multimedia;
    }

    /**
     *
     * @param document
     * @param parentNode
     */
    public void toXml(Document document, Element parentNode){
        logger.debug("Begin");

        parentNode.setAttribute(ATTRIBUTE_NAME, this.get_name());
        parentNode.setAttribute(ATTRIBUTE_SRC, this.get_src());
        parentNode.setAttribute(ATTRIBUTE_TITLE, this.get_title());
        parentNode.setAttribute(ATTRIBUTE_INFOTEXT, this.get_infoText());
        parentNode.setAttribute(ATTRIBUTE_KEYWORDS, this.get_keywords());
        parentNode.setAttribute(ATTRIBUTE_UPDATE, this.get_updateString());

        for (Map.Entry<Integer, Object> entry1 : this.elements.entrySet()) {
            Integer key = entry1.getKey();
            Video value = (Video) entry1.getValue();

            Element videoNode = document.createElement(value.get_nodeName());
            value.toXml(document, videoNode);

            parentNode.appendChild(videoNode);
        }

        logger.debug("End");
    }
}
