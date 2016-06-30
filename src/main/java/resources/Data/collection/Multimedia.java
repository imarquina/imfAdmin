package resources.Data.collection;

import java.util.Date;
import java.util.Hashtable;

public class Multimedia {
    private String _name;
    private String _src;
    private String _title;
    private String _infotext;
    private String _keywords;
    private Date _update;

    public Hashtable<Integer, Object> elements;

    /**
     * CONSTRUCTORES
     */
    public Multimedia() {
        elements = new Hashtable<Integer, Object>();
    }

    public Multimedia(String name, String src, String title, String infotext,
                      String keywords, Date update) {
        this.set_name(name);
        this.set_src(src);
        this.set_title(title);
        this.set_infotext(infotext);
        this.set_keywords(keywords);
        this.set_update(update);

        elements = new Hashtable<Integer, Object>();
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

    public String get_infotext() {
        return this._infotext;
    }

    public void set_infotext(String infotext) {
        this._infotext = infotext;
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

    public void set_update(Date update) {
        this._update = update;
    }
}
