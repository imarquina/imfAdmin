package iml.imfotografia.xml.config.structs;

import iml.imfotografia.xml.config.xborrar_XmlConfig;
import org.apache.log4j.Logger;

import java.util.LinkedHashMap;

/**
 * Created by inaki.marquina on 06/07/2016.
 */
public class Config {
    private String _title;
    private String _infoText;
    private String _keywords;
    private Integer _iKey;

    public LinkedHashMap<Integer, Object> elements;
    public Title title;
    public Slogan slogan;
    public Tracks tracks;
    public ContactForm contactForm;

    final static Logger logger = Logger.getLogger(xborrar_XmlConfig.class);

    /**
     * CONSTRUCTORS
     */
    public Config() {
        this._title = "";
        this._infoText = "";
        this._keywords = "";
        _iKey = 0;

        elements = new LinkedHashMap<Integer, Object>();
        title = new Title();
        slogan = new Slogan();
        tracks = new Tracks();
        contactForm = new ContactForm();
    }

    public Config(String infoText, String keywords, String title) {
        this();

        this.set_infoText(infoText);
        this.set_keywords(keywords);
        this.set_title(title);
    }

    /**
     * GETTER / SETTER
     */
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

    public String get_title() {
        return this._title;
    }

    public void set_title(String title) {
        this._title = title;
    }

    /**
     *
     * @return
     */
    public void addGalleries(Galleries galleries){
        this.elements.put(_iKey++, galleries);
    }

    /**
     *
     * @return
     */
    public void addFolder(Folder folder){
        this.elements.put(_iKey++, folder);
    }

    /**
     *
     * @return
     */
    public void addSection(Section section){
        this.elements.put(_iKey++, section);
    }

    public void addTitle(Title title) {
        this.title = title;
    }

    public void addSlogan(Slogan slogan) {
        this.slogan = slogan;
    }

    public void addTracks(Tracks tracks) {
        this.tracks = tracks;
    }

    public void addContactForm(ContactForm contactForm) {
        this.contactForm = contactForm;
    }
}
