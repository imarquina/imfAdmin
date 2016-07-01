package iml.imfotografia.xml.data;

import iml.imfotografia.xml.data.collection.*;
import iml.imfotografia.xml.data.element.*;
import java.util.Hashtable;

/**
 * Created by inaki.marquina on 29/06/2016.
 */
public class XmlConfig {
    private String _title;
    private String _infoText;
    private String _keywords;
    private Integer _iKey;
    public Title title;
    public Slogan slogan;
    public ContactForm contactForm;
    public Hashtable<String, Track> tracks;
    public Hashtable<Integer, Object> elements;

    /**
     * CONSTRUCTORS
     */
    public XmlConfig() {
        this._iKey = 0;
        this.title = new Title();
        this.slogan = new Slogan();
        this.contactForm = new ContactForm();
        this.tracks = new Hashtable<String, Track>();
        this.elements = new Hashtable<Integer, Object>();
    }

    public XmlConfig(String title, String infoText, String keywords) {
        this ();

        this.set_title(title);
        this.set_infoText(infoText);
        this.set_keywords(keywords);
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

    private Integer get_iKey() {
        return _iKey;
    }

    /**
     *
     * @return
     */
    public Galleries addGalleries(){
        Galleries galleries = new Galleries();
        this.elements.put(_iKey++, galleries);

        return galleries;
    }

    /**
     *
     * @return
     */
    public Folder addFolder(){
        Folder folder = new Folder();
        this.elements.put(_iKey++, folder);

        return folder;
    }

    /**
     *
     * @return
     */
    public Section addSection(){
        Section section = new Section();
        this.elements.put(_iKey++, section);

        return section;
    }

    /**
     *
     * @return
     */
    public Track addTrack(){
        Track track = new Track();
        this.elements.put(_iKey++, track);

        return track;
    }
}

