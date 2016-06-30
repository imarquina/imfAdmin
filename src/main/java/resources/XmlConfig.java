package resources;

import java.util.Date;
import java.util.Hashtable;

/**
 * Created by inaki.marquina on 29/06/2016.
 */
public class XmlConfig {
    private String _title;
    private String _infoText;
    private String _keywords;
    protected Title title = new Title();
    protected Slogan slogan = new Slogan();
    protected ContactForm contactForm = new ContactForm();
    protected Hashtable<String, Track> tracks;

    protected Hashtable<Integer, Object> elements;

    /**
     * CONSTRUCTORS
     */
    protected XmlConfig() {
    }

    protected XmlConfig(String _title, String _infoText, String _keywords) {
        this._title = _title;
        this._infoText = _infoText;
        this._keywords = _keywords;
    }

    /**
     * GETTER / SETTER
     */
    protected String get_title() {
        return _title;
    }

    protected void set_title(String _title) {
        this._title = _title;
    }

    protected String get_infoText() {
        return _infoText;
    }

    protected void set_infoText(String _infoText) {
        this._infoText = _infoText;
    }

    protected String get_keywords() {
        return _keywords;
    }

    protected void set_keywords(String _keywords) {
        this._keywords = _keywords;
    }
}

class Title {
    private String _content;

    /**
     * CONSTRUCTORS
     */
    protected Title() {
    }

    /**
     * GETTER / SETTER
     */
    protected String get_content() {
        return _content;
    }

    protected void set_content(String _content) {
        this._content = _content;
    }
}

class Slogan {
    private String _content;

    /**
     * CONSTRUCTORS
     */
    protected Slogan() {
    }

    /**
     * GETTER / SETTER
     */
    protected String get_content() {
        return _content;
    }

    protected void set_content(String _content) {
        this._content = _content;
    }
}

class ContactForm {
    private String _email;
    private String _subjAuxText;

    /**
     * CONSTRUCTORES
     */
    protected ContactForm() {
    }

    protected ContactForm(String _email, String _subjAuxText) {
        this.set_email(_email);
        this.set_subjAuxText(_subjAuxText);
    }

    /**
     * GETTER / SETTER
     */
    protected String get_email() {
        return _email;
    }

    protected void set_email(String _email) {
        this._email = _email;
    }

    protected String get_subjAuxText() {
        return _subjAuxText;
    }

    protected void set_subjAuxText(String _subjAuxText) {
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
    protected Track() {
    }

    protected Track(String _src, String _artist, String _name) {
        this._src = _src;
        this._artist = _artist;
        this._name = _name;
    }

    /**
     * GETTER / SETTER
     */
    protected String get_src() {
        return _src;
    }

    protected void set_src(String _src) {
        this._src = _src;
    }

    protected String get_artist() {
        return _artist;
    }

    protected void set_artist(String _artist) {
        this._artist = _artist;
    }

    protected String get_name() {
        return _name;
    }

    protected void set_name(String _name) {
        this._name = _name;
    }
}

/**
 * ELEMENTS
 */
class Image {
    private String _id;

    /**
     * CONSTRUCTORS
     */
    protected Image() {

    }

    /**
     * GETTER / SETTER
     */
    protected String get_id() {
        return _id;
    }

    protected void set_id(String _id) {
        this._id = _id;
    }
}

class Section {
    private String _name;
    private Integer _width;
    private Integer _height;
    private Boolean _byDefault;
    private Date _update;
    private String _content;

    /**
     * CONSTRUCTORES
     */
    protected Section() {
    }

    protected Section(String _name, Integer _width, Integer _height, Boolean _byDefault, Date _update) {
        this._name = _name;
        this._width = _width;
        this._height = _height;
        this._byDefault = _byDefault;
        this._update = _update;
    }

    /**
     * GETTER / SETTER
     */
    protected String get_name() {
        return _name;
    }

    protected void set_name(String _name) {
        this._name = _name;
    }

    protected Integer get_width() {
        return _width;
    }

    protected void set_width(Integer _width) {
        this._width = _width;
    }

    protected Integer get_height() {
        return _height;
    }

    protected void set_height(Integer _height) {
        this._height = _height;
    }

    protected Boolean get_byDefault() {
        return _byDefault;
    }

    protected void set_byDefault(Boolean _byDefault) {
        this._byDefault = _byDefault;
    }

    protected Date get_update() {
        return _update;
    }

    protected void set_update(Date _update) {
        this._update = _update;
    }

    protected String get_content() {
        return _content;
    }

    protected void set_content(String _content) {
        this._content = _content;
    }
}

class Video {
    private String _name;
    private String _src;
    private String _title;
    private String _infoText;
    private String _keywords;
    private Date _update;

    /**
     * CONSTRUCTORES
     */
    protected Video() {
    }

    protected Video(Date _update, String _infoText, String _keywords, String _title, String _src, String _name) {
        this._update = _update;
        this._infoText = _infoText;
        this._keywords = _keywords;
        this._title = _title;
        this._src = _src;
        this._name = _name;
    }

    /**
     * GETTER / SETTER
     */
    protected String get_name() {
        return _name;
    }

    protected void set_name(String _name) {
        this._name = _name;
    }

    protected String get_src() {
        return _src;
    }

    protected void set_src(String _src) {
        this._src = _src;
    }

    protected String get_title() {
        return _title;
    }

    protected void set_title(String _title) {
        this._title = _title;
    }

    protected String get_infoText() {
        return _infoText;
    }

    protected void set_infoText(String _infoText) {
        this._infoText = _infoText;
    }

    protected String get_keywords() {
        return _keywords;
    }

    protected void set_keywords(String _keywords) {
        this._keywords = _keywords;
    }

    protected Date get_update() {
        return _update;
    }

    protected void set_update(Date _update) {
        this._update = _update;
    }
}

/**
 * COLLECTIONS
 */
class Folder {
    private String _name;
    private String _src;
    private String _title;
    private String _infoText;
    private String _keywords;
    private Date _update;

    protected Hashtable<Integer, Object> elements;
}

class Galleries {
    protected Hashtable<Integer, Object> elements;

    /**
     * CONSTRUCTORES
     */
    protected Galleries(Hashtable<Integer, Object> elements) {
        this.elements = elements;
    }
}

class Gallery {
    private String _name;
    private String _src;
    private String _title;
    private String _infoText;
    private String _keywords;
    private Date _update;

    protected Hashtable<Integer, Object> elements;

    /**
     * CONSTRUCTORES
     */
    protected Gallery() {

    }

    protected Gallery(String _name, String _src, String _title, Date _update) {
        this._name = _name;
        this._src = _src;
        this._title = _title;
        this._update = _update;
    }

    /**
     * GETTER / SETTER
     */
    protected String get_name() {
        return _name;
    }

    protected void set_name(String _name) {
        this._name = _name;
    }

    protected String get_src() {
        return _src;
    }

    protected void set_src(String _src) {
        this._src = _src;
    }

    protected String get_title() {
        return _title;
    }

    protected void set_title(String _title) {
        this._title = _title;
    }

    protected String get_infoText() {
        return _infoText;
    }

    protected void set_infoText(String _infoText) {
        this._infoText = _infoText;
    }

    protected String get_keywords() {
        return _keywords;
    }

    protected void set_keywords(String _keywords) {
        this._keywords = _keywords;
    }

    protected Date get_update() {
        return _update;
    }

    protected void set_update(Date _update) {
        this._update = _update;
    }
}

class Multimedia {
    private String _name;
    private String _src;
    private String _title;
    private String _infotext;
    private String _keywords;
    private Date _update;

    protected Hashtable<Integer, Object> elements;

    /**
     * CONSTRUCTORES
     */
    protected Multimedia() {
    }

    protected Multimedia(String _name, String _src, String _title, String _infotext, String _keywords, Date _update) {
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
    protected String get_name() {
        return _name;
    }

    protected void set_name(String _name) {
        this._name = _name;
    }

    protected String get_src() {
        return _src;
    }

    protected void set_src(String _src) {
        this._src = _src;
    }

    protected String get_title() {
        return _title;
    }

    protected void set_title(String _title) {
        this._title = _title;
    }

    protected String get_infotext() {
        return _infotext;
    }

    protected void set_infotext(String _infotext) {
        this._infotext = _infotext;
    }

    protected String get_keywords() {
        return _keywords;
    }

    protected void set_keywords(String _keywords) {
        this._keywords = _keywords;
    }

    protected Date get_update() {
        return _update;
    }

    protected void set_update(Date _update) {
        this._update = _update;
    }
}