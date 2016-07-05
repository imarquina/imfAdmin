package iml.imfotografia.xml.data.collection;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;

/**
 * COLLECTIONS
 */
public class Folder {
    private Integer _iKey;
    private String _name;
    private String _src;
    private String _title;
    private String _infoText;
    private String _keywords;
    private Date _update;
    private DateFormat _formatDate = new SimpleDateFormat("yyyymmdd");

    public Hashtable<Integer, Object> elements;

    /**
     * CONSTRUCTORS
     */
    public Folder() {
        _iKey = 0;
        this.elements = new Hashtable<Integer, Object>();
    }

    public Folder(String name, String src, String title, String infoText, String
            keywords, Date update) {
        this();

        this.set_name(name);
        this.set_src(src);
        this.set_title(title);
        this.set_infoText(infoText);
        this.set_keywords(keywords);
        this.set_update(update);
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
    public Gallery addGallery () {
        Gallery gallery = new Gallery();
        this.elements.put(_iKey++, gallery);

        return gallery;
    }
}
