import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;

/**
 * Created by inaki.marquina on 29/06/2016.
 */
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
    }

    public Multimedia(String _name, String _src, String _title, String _infotext, String _keywords, Date _update) {
        this.set_name(_name);
        this.set_src(_src);
        this.set_title(_title);
        this.set_infotext(_infotext);
        this.set_keywords(_keywords);
        this.set_update(_update);
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

    public String get_infotext() {
        return _infotext;
    }

    public void set_infotext(String _infotext) {
        this._infotext = _infotext;
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
