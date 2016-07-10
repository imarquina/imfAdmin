package iml.imfotografia.xml.config.structs;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Gallery {
    private String _name;
    private String _src;
    private String _title;
    private String _infoText;
    private String _keywords;
    private Date _update;
    private DateFormat _formatDate = new SimpleDateFormat("yyyymmdd");

    public Map<String, Object> elements;

    /**
     * CONSTRUCTORES
     */
    public Gallery() {
        elements = new LinkedHashMap<String, Object>();
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

    public void set_update(String update) throws ParseException {
        this._update = _formatDate.parse(update);
    }

    public void set_update(Date update) {
        this._update = update;
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
}
