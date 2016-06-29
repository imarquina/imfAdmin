package resources.collection;

import java.util.Date;
import java.util.Hashtable;

/**
 * Created by inaki.marquina on 29/06/2016.
 */
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

    }

    public Gallery(String _name, String _src, String _title, Date _update) {
        this._name = _name;
        this._src = _src;
        this._title = _title;
        this._update = _update;
    }

    /**
     * GETTER / SETTER
     */
    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_src() {
        return _src;
    }

    public void set_src(String _src) {
        this._src = _src;
    }

    public String get_title() {
        return _title;
    }

    public void set_title(String _title) {
        this._title = _title;
    }

    public String get_infoText() {
        return _infoText;
    }

    public void set_infoText(String _infoText) {
        this._infoText = _infoText;
    }

    public String get_keywords() {
        return _keywords;
    }

    public void set_keywords(String _keywords) {
        this._keywords = _keywords;
    }

    public Date get_update() {
        return _update;
    }

    public void set_update(Date _update) {
        this._update = _update;
    }
}
