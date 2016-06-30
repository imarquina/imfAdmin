package resources.Data.element;

import java.util.Date;

public class Video {
    private String _name;
    private String _src;
    private String _title;
    private String _infoText;
    private String _keywords;
    private Date _update;

    /**
     * CONSTRUCTORES
     */
    public Video() {
    }

    public Video(Date update, String infoText, String keywords, String title, String src, String name) {
        this._update = update;
        this._infoText = infoText;
        this._keywords = keywords;
        this._title = title;
        this._src = src;
        this._name = name;
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
