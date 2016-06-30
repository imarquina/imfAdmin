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

class Title {
    private String _content;

    /**
     * CONSTRUCTORS
     */
    public Title() {
    }

    /**
     * GETTER / SETTER
     */
    public String get_content() {
        return _content;
    }

    public void set_content(String _content) {
        this._content = _content;
    }
}

class Slogan {
    private String _content;

    /**
     * CONSTRUCTORS
     */
    public Slogan() {
    }

    /**
     * GETTER / SETTER
     */
    public String get_content() {
        return _content;
    }

    public void set_content(String _content) {
        this._content = _content;
    }
}

class ContactForm {
    private String _email;
    private String _subjAuxText;

    /**
     * CONSTRUCTORES
     */
    public ContactForm() {
    }

    public ContactForm(String _email, String _subjAuxText) {
        this.set_email(_email);
        this.set_subjAuxText(_subjAuxText);
    }

    /**
     * GETTER / SETTER
     */
    public String get_email() {
        return _email;
    }

    public void set_email(String _email) {
        this._email = _email;
    }

    public String get_subjAuxText() {
        return _subjAuxText;
    }

    public void set_subjAuxText(String _subjAuxText) {
        this._subjAuxText = _subjAuxText;
    }
}

class Track {
    private String _src;
    private String _artist;
    private String _name;

    /**
     * CONSTRUCTORS
     */
    public Track() {
    }

    public Track(String _src, String _artist, String _name) {
        this._src = _src;
        this._artist = _artist;
        this._name = _name;
    }

    /**
     * GETTER / SETTER
     */
    public String get_src() {
        return _src;
    }

    public void set_src(String _src) {
        this._src = _src;
    }

    public String get_artist() {
        return _artist;
    }

    public void set_artist(String _artist) {
        this._artist = _artist;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }
}
