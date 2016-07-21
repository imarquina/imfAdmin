package iml.imfotografia.xml.config.structs;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

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
    private DateFormat _dateFormatOut = new SimpleDateFormat("yyyy-mm-dd");

    public Map<String, Object> elements;

    final static Logger logger = Logger.getLogger(Gallery.class);

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
     * @param document
     * @param parentNode
     */
    public void toXml(Document document, Element parentNode){
        logger.debug("Begin");

        parentNode.setAttribute("name", this.get_name());
        parentNode.setAttribute("src", this.get_src());
        parentNode.setAttribute("title", this.get_title());
        parentNode.setAttribute("infotext", this.get_infoText());
        parentNode.setAttribute("keywords", this.get_keywords());
        parentNode.setAttribute("update", this.get_updateString());

        logger.debug("End");
    }
}
