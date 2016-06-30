package resources.Data.collection;

import java.util.Date;
import java.util.Hashtable;

public class Gallery {
    private String _name;
    private String _src;
    private String _title;
    private String _infoText;
    private String _keywords;
    private Date _update;

    public Hashtable<Integer, Object> elements;

    /**
     * CONSTRUCTORES
     */
    public Gallery() {
        elements = new Hashtable<Integer, Object>();
    }

    public Gallery(String name, String src, String title, Date update) {
        set_name(name);
        set_src(src);
        set_title(title);
        set_update(update);

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

    public void set_update(Date update) {
        this._update = update;
    }
}
