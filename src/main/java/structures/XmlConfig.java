package structures;

import java.util.Hashtable;

/**
 * Created by inaki.marquina on 29/06/2016.
 */
public class XmlConfig {
    private String _title;
    private String _infoText;
    private String _keywords;
    public Title title = new Title();
    public Slogan slogan = new Slogan();
    public ContactForm contactForm = new ContactForm();
    public Hashtable<String, Track> tracks;

    public Hashtable<Integer, Object> elements;

    /**
     * CONSTRUCTORS
     */
    public XmlConfig() {
    }

    public XmlConfig(String _title, String _infoText, String _keywords) {
        this._title = _title;
        this._infoText = _infoText;
        this._keywords = _keywords;
    }

    /**
     * GETTER / SETTER
     */
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
}
