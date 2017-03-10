package iml.framework.xml.website.config.structs;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

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

    public Map<String, Object> elements;
    public Title title;
    public Slogan slogan;
    public Tracks tracks;
    public ContactForm contactForm;

    final static Logger logger = Logger.getLogger(Config.class);

    /**
     * CONSTANTS
     */
    private static final String ATTRIBUTE_TITLE = "title";
    private static final String ATTRIBUTE_INFOTEXT = "infotext";
    private static final String ATTRIBUTE_KEYWORDS = "keywords";

    /**
     * CONSTRUCTORS
     */
    public Config() {
        _iKey = 0;
        this._nodeName = "config";

        this._title = "";
        this._infoText = "";
        this._keywords = "";

        elements = new LinkedHashMap<String, Object>();
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
        this.elements.put((_iKey++).toString(), galleries);
    }

    /**
     *
     * @return
     */
    public void addFolder(Folder folder){
        this.elements.put((_iKey++).toString(), folder);
    }

    /**
     *
     * @return
     */
    public void addSection(Section section){
        this.elements.put((_iKey++).toString(), section);
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

    public void fromXml(Node node) {
        logger.debug("Begin");

        // get attributes names and values
        NamedNodeMap nodeMap = node.getAttributes();
        for (int i = 0; i < nodeMap.getLength(); i++) {
            Node tempNode = nodeMap.item(i);
            String sAttrName = tempNode.getNodeName();
            if (!sAttrName.equals(null)) sAttrName = sAttrName.trim().toUpperCase();
            String sAttrValue = tempNode.getNodeValue();
            if (!sAttrValue.equals(null)) sAttrValue = sAttrValue.trim();

            logger.info("    Attr name : " + sAttrName + "; Value = " + sAttrValue);

            if (sAttrName.equalsIgnoreCase(ATTRIBUTE_TITLE)) {
                this.set_title(sAttrValue);
            } else if (sAttrName.equalsIgnoreCase(ATTRIBUTE_INFOTEXT)) {
                this.set_infoText(sAttrValue);
            } else if (sAttrName.equalsIgnoreCase(ATTRIBUTE_KEYWORDS)) {
                this.set_keywords(sAttrValue);
            } else {
                logger.info("unknow ConfigNode property " + sAttrName + ":" + sAttrValue);
            }

            logger.debug("set ConfigNode property " + sAttrName + ":" + sAttrValue);
        }

        logger.debug("End");
    }

    /**
     *
     * @param document
     * @param parentNode
     */
    public void toXml(Document document, Element parentNode) {
        logger.debug("Begin");

        parentNode.setAttribute(ATTRIBUTE_TITLE, this.get_title());
        parentNode.setAttribute(ATTRIBUTE_INFOTEXT, this.get_infoText());
        parentNode.setAttribute(ATTRIBUTE_KEYWORDS, this.get_keywords());

        //Recorrer los item para procesado
        for (Map.Entry<String, Object> entry1 : this.elements.entrySet()) {
            String key = entry1.getKey();
            Object value = entry1.getValue();

            if (value instanceof Galleries) {
                Galleries galleries = (Galleries)value;

                Element galleriesNode = document.createElement(galleries.get_nodeName());
                galleries.toXml(document, galleriesNode);

                parentNode.appendChild(galleriesNode);
            } else if (value instanceof  Gallery) {
                Gallery gallery = (Gallery)value;

                //Recoger y escribir atributos
                Element galleryNode = document.createElement(gallery.get_nodeName());
                gallery.toXml(document, galleryNode);

                parentNode.appendChild(galleryNode);
            } else if (value instanceof Folder) {
                Folder folder = (Folder)value;

                Element folderNode = document.createElement(folder.get_nodeName());
                folder.toXml(document, folderNode);

                parentNode.appendChild(folderNode);
            } else if (value instanceof Section) {
                Section section = (Section)value;

                Element sectionNode = document.createElement(section.get_nodeName());
                section.toXml(document, sectionNode);

                parentNode.appendChild(sectionNode);
            } else if (value instanceof Multimedia) {
                Multimedia multimedia = (Multimedia)value;

                //Recoger y escribir atributos
                Element multimediaNode = document.createElement(multimedia.get_nodeName());
                multimedia.toXml(document, multimediaNode);

                parentNode.appendChild(multimediaNode);
            }
        }

        //title
        Element titleNode = document.createElement(this.title.get_nodeName());
        this.title.toXml(document, titleNode);
        parentNode.appendChild(titleNode);

        //slogan
        Element sloganNode = document.createElement(this.slogan.get_nodeName());
        this.slogan.toXml(document, sloganNode);
        parentNode.appendChild(sloganNode);

        //tracks
        Element tracksNode = document.createElement(this.tracks.get_nodeName());
        this.tracks.toXml(document, tracksNode);
        parentNode.appendChild(tracksNode);

        //contactform
        Element contacformNode = document.createElement(this.contactForm.get_nodeName());
        this.contactForm.toXml(document, contacformNode);
        parentNode.appendChild(contacformNode);

        logger.debug("End");
    }
}
