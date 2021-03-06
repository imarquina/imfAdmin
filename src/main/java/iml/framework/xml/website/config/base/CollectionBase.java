package iml.framework.xml.website.config.base;

import iml.framework.xml.website.config.interfaces.ICollection;
import iml.framework.xml.website.config.interfaces.IElement;
import iml.framework.xml.website.config.structs.Gallery;
import iml.framework.xml.website.config.structs.Image;
import iml.framework.xml.website.config.structs.Section;
import iml.framework.xml.website.config.structs.Video;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by imarquina on 3/8/16.
 */
public class CollectionBase implements ICollection {
    protected String _nodeName = "";
    private String _name;
    private String _src;
    private String _title;
    private String _infoText;
    private String _keywords;
    private Date _update;
    private DateFormat _dateFormatIn = new SimpleDateFormat("yyyyMMdd");
    private DateFormat _dateFormatOut = new SimpleDateFormat("yyyyMMdd");

    public Map<String, Object> elements;

    final static Logger logger = LogManager.getLogger(Gallery.class);

    /**
     * CONSTANTS
     */
    protected static final String ATTRIBUTE_NAME = "name";
    protected static final String ATTRIBUTE_SRC = "src";
    protected static final String ATTRIBUTE_TITLE = "title";
    protected static final String ATTRIBUTE_INFOTEXT = "infotext";
    protected static final String ATTRIBUTE_KEYWORDS = "keywords";
    protected static final String ATTRIBUTE_UPDATE = "update";

    /**
     * CONSTRUCOTRS
     */
    public CollectionBase(){
        this._nodeName = "gallery";
        this._name = "";
        this._src = "";
        this._title = "";
        this._infoText = "";
        this._keywords = "";
        this.elements = new LinkedHashMap<String, Object>();
    }

    public CollectionBase(String name, String src, String title, Date update) {
        this();

        set_name(name);
        set_src(src);
        set_title(title);
        set_update(update);
    }

    /**
     * GETTER / SETTER
     */
    public String get_id() {
        return this._src.replace(".jpg", "");
    }

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
     * @param key
     * @return
     */
    public Integer getIndexKey(String key){
        List<Object> list = new ArrayList<Object>(this.elements.values());

        for (Integer i = 0; i < list.size(); i++){
            if (list.get(i) instanceof IElement && ((IElement)list.get(i)).get_id().equalsIgnoreCase(key))
                return i;
        }
        return -1;
    }

    /**
     *
     * @param element
     */
    public void addElement(ElementBase element){
        this.elements.put(element.get_id(), element);
    }

    /**
     *
     * @param collection
     */
    public void addCollection(CollectionBase collection) {
        this.elements.put(collection.get_id(), collection);
    }

    /**
     *
     * @param section
     */
    public void addSection(Section section) {
        this.elements.put(section.get_id(), section);
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
            ElementBase value = (ElementBase) entry1.getValue();

            if (value instanceof Image) {
                Image imageValue = (Image)value;
                Element imagekNode = document.createElement(imageValue.get_nodeName());
                imageValue.toXml(document, imagekNode);

                parentNode.appendChild(imagekNode);
            } else if (value instanceof Video) {
                Video videoValue = (Video)value;
                Element videoNode = document.createElement(videoValue.get_nodeName());
                videoValue.toXml(document, videoNode);

                parentNode.appendChild(videoNode);
            }
        }

        logger.debug("End");
    }
}
