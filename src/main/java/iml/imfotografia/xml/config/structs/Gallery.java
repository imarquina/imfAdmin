package iml.imfotografia.xml.config.structs;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Gallery {
    private String _nodeName = "";
    private String _name;
    private String _src;
    private String _title;
    private String _infoText;
    private String _keywords;
    private Date _update;
    private DateFormat _dateFormatIn = new SimpleDateFormat("yyyymmdd");
    private DateFormat _dateFormatOut = new SimpleDateFormat("yyyymmdd");

    public Map<String, Object> elements;

    final static Logger logger = Logger.getLogger(Gallery.class);

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
    public Gallery() {
        this._nodeName = "gallery";
        this.elements = new LinkedHashMap<String, Object>();
    }

    public Gallery(String name, String src, String title, Date update) {
        this();

        set_name(name);
        set_src(src);
        set_title(title);
        set_update(update);
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

    public void set_infoText(String infoText) {
        this._infoText = infoText;
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

    public String get_updateString(){
        return _dateFormatOut.format(this._update);
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
    public void addImage(Image image) {
        this.elements.put(image.get_id(), image);
    }

    /**
     *
     * @param key
     * @return
     */
    public Integer getIndexKey(String key){
        List<Object> list = new ArrayList<Object>(this.elements.values());

        for (Integer i = 0; i < list.size(); i++){
            if (((Image)list.get(i)).get_id().equalsIgnoreCase(key))
                return i;
        }
        return -1;
    }

    /**
     *
     * @param node
     * @return
     * @throws ParseException
     */
    public Gallery fromXml(Node node) throws ParseException {
        logger.debug("Begin");
        Gallery gallery = new Gallery();

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
                gallery.set_name(sAttrValue);
            } else if (sAttrName.equalsIgnoreCase(ATTRIBUTE_SRC)) {
                gallery.set_src(sAttrValue);
            } else if (sAttrName.equalsIgnoreCase(ATTRIBUTE_TITLE)) {
                gallery.set_title(sAttrValue);
            } else if (sAttrName.equalsIgnoreCase(ATTRIBUTE_INFOTEXT)) {
                gallery.set_infoText(sAttrValue);
            } else if (sAttrName.equalsIgnoreCase(ATTRIBUTE_KEYWORDS)) {
                gallery.set_keywords(sAttrValue);
            } else if (sAttrName.equalsIgnoreCase(ATTRIBUTE_UPDATE)) {
                gallery.set_update(sAttrValue);
            } else {
                logger.info("unknow Gallery property " + sAttrName + ":" + sAttrValue);
            }

            logger.debug("set Gallery property " + sAttrName + ":" + sAttrValue);
        }

        logger.debug("End");
        return gallery;
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

        for (Map.Entry<String, Object> entry1 : this.elements.entrySet()) {
            String key = entry1.getKey();
            Image value = (Image)entry1.getValue();

            Element imagekNode = document.createElement(value.get_nodeName());
            value.toXml(document, imagekNode);

            parentNode.appendChild(imagekNode);
        }

        logger.debug("End");
    }
}
