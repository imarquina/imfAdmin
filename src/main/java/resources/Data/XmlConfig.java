package resources.Data;

import resources.Data.element.*;
import java.util.Hashtable;

/**
 * Created by inaki.marquina on 29/06/2016.
 */
public class XmlConfig {
    private String _title;
    private String _infoText;
    private String _keywords;
    public Title title;
    public Slogan slogan;
    public ContactForm contactForm;
    public Hashtable<String, Track> tracks;
    public Hashtable<Integer, Object> elements;

    /**
     * CONSTRUCTORS
     */
    public XmlConfig() {
        this.title = new Title();
        this.slogan = new Slogan();
        this.contactForm = new ContactForm();
        this.tracks = new Hashtable<String, Track>();
        this.elements = new Hashtable<Integer, Object>();
    }

    public XmlConfig(String title, String infoText, String keywords) {
        this.set_title(title);
        this.set_infoText(infoText);
        this.set_keywords(keywords);

        this.title = new Title();
        this.slogan = new Slogan();
        this.contactForm = new ContactForm();
        this.tracks = new Hashtable<String, Track>();
        this.elements = new Hashtable<Integer, Object>();
        this.elements = new Hashtable<Integer, Object>();
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

