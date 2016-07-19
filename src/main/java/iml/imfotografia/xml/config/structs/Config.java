package iml.imfotografia.xml.config.structs;

import iml.imfotografia.arq.utils.Crypto;
import iml.imfotografia.arq.utils.Text;
import iml.imfotografia.xml.sitemap.element.Url;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by inaki.marquina on 06/07/2016.
 */
public class Config {
    private Integer _iKey;
    private String _nodeName = "";

    private String _title;
    private String _infoText;
    private String _keywords;

    public Map<Integer, Object> elements;
    public Title title;
    public Slogan slogan;
    public Tracks tracks;
    public ContactForm contactForm;

    final static Logger logger = Logger.getLogger(Config.class);

    /**
     * CONSTRUCTORS
     */
    public Config() {
        _iKey = 0;
        this._nodeName = "config";

        this._title = "";
        this._infoText = "";
        this._keywords = "";

        elements = new LinkedHashMap<Integer, Object>();
        title = new Title();
        slogan = new Slogan();
        tracks = new Tracks();
        contactForm = new ContactForm();
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

    public String get_nodeName() {
        return this._nodeName;
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

    /**
     *
     * @param title
     */
    public void addTitle(Title title) {
        this.title = title;
    }

    /**
     *
     * @param slogan
     */
    public void addSlogan(Slogan slogan) {
        this.slogan = slogan;
    }

    /**
     *
     * @param tracks
     */
    public void addTracks(Tracks tracks) {
        this.tracks = tracks;
    }

    /**
     *
     * @param contactForm
     */
    public void addContactForm(ContactForm contactForm) {
        this.contactForm = contactForm;
    }

    /**
     *
     * @param document
     * @param parentNode
     */
    public void toXml(Document document, Element parentNode) {
        logger.debug("Begin");

        //Recorrer los item para procesado
        for (Map.Entry<Integer, Object> entry1 : this.elements.entrySet()) {
            Integer key = entry1.getKey();
            Object value = entry1.getValue();

            if (value instanceof Galleries) {
                Galleries galleries = (Galleries)value;

                Element galleriesNode = document.createElement(galleries.get_nodeName());
                galleries.toXml(document, galleriesNode);

                parentNode.appendChild(galleriesNode);
            } else if (value instanceof  Gallery) {
                Gallery gallery = (Gallery)value;

                //Recoger y escribir atributos
                //Código ...

                Element galleryNode = document.createElement(gallery.get_nodeName());
                gallery.toXml(document, galleryNode);

                parentNode.appendChild(galleryNode);
            } else if (value instanceof Folder) {
                Folder folder = (Folder)value;

                //Recoger y escribir atributos
                //Código ...

                Element folderNode = document.createElement(folder.get_nodeName());
                folder.toXml(document, folderNode);

                parentNode.appendChild(folderNode);
            } else if (value instanceof Section) {
                Section section = (Section)value;

                //Recoger y escribir atributos
                //Código ...

                Element sectionNode = document.createElement(section.get_nodeName());
                section.toXml(document, sectionNode);

                parentNode.appendChild(sectionNode);
            } else if (value instanceof Multimedia) {
                Multimedia multimedia = (Multimedia)value;

                //Recoger y escribir atributos
                //Código ...

                Element multimediaNode = document.createElement(multimedia.get_nodeName());
                multimedia.toXml(document, multimediaNode);

                parentNode.appendChild(multimediaNode);
            }
        }

        logger.debug("End");
    }
}
