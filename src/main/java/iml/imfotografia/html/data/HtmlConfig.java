package iml.imfotografia.html.data;

import iml.imfotografia.xml.data.element.ContactForm;
import iml.imfotografia.xml.data.element.Slogan;
import iml.imfotografia.xml.data.element.Title;
import iml.imfotografia.xml.data.element.Track;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

import static iml.imfotografia.utils.Text.SetLength;

/**
 * Created by imarquina on 3/7/16.
 */
public class HtmlConfig {
    private String _xml;
    private String _title;
    private String _infoText;
    private String _keywords;
    private Integer _iKey;
    public Title title;
    public Slogan slogan;
    public ContactForm contactForm;
    public Hashtable<String, Track> tracks;
    public Hashtable<Integer, Object> elements;

    final static Logger logger = Logger.getLogger(HtmlConfig.class);

    /**
     * ENUMERATORS
     */
    private enum nodeType
    {
        section,
        title,
        slogan,
        contactform,
        track,
        imagen,
        video
    }

    private enum collectionType
    {
        galleries,
        gallery,
        folder,
        multimedia,
        tracks
    }

    /**
     * CONSTANTS
     */
    private static final String NODO_COLLECTION_GALLERIES = "galleries";
    private static final String NODO_COLLECTION_GALLERY = "gallery";
    private static final String NODO_COLLECTION_FOLDER = "folder";
    private static final String NODO_COLLECTION_MULTIMEDIA = "multimedia";
    private static final String NODO_COLLECTION_TRACK = "tracks";
    private static final String NODO_SECTION = "section";
    private static final String NODO_TITLE = "title";
    private static final String NODO_SLOGAN = "slogan";
    private static final String NODO_CONTACTFORM = "contactform";
    private static final String NODO_IMAGEN = "img";
    private static final String NODO_VIDEO = "vid";
    private static final String NODO_TRACK = "track";

    /**
     * CONSTRUCTORS
     */
    public HtmlConfig() {
        this._iKey = 0;
        this.title = new Title();
        this.slogan = new Slogan();
        this.contactForm = new ContactForm();
        this.tracks = new Hashtable<String, Track>();
        this.elements = new Hashtable<Integer, Object>();
    }

    public HtmlConfig(String xml) throws IOException {
        this ();
        this.set_xml(xml);
        parseDoc();
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

    public String get_xml() {
        return this._xml;
    }

    public void set_xml(String xml) {
        this._xml = xml;
    }

    /**
     * PRIVATE METHODS
     */
    private void parseDoc() throws IOException {
        logger.debug("parseDoc::Begin");

        //Build Document
        File input = new File(get_xml());
        Document document = Jsoup.parse(input, "UTF-8");

        //Here comes the root node
        Elements root = document.getElementsByTag("config");

        if (root.get(0) != null) {
            logger.info("parseDoc::" + "root Node: " + root.get(0).tagName());
            openChildNodes(root.get(0).childNodes());
        }
    }

    private void openChildNodes(List<Node> nList) {
        logger.debug("openChildNodes:Start");

        for (Node node : nList) {
            if (node instanceof Element) {
                String nodeName = ((Element) node).tagName();
                if (!nodeName.equals(null)) nodeName = nodeName.trim();
                String nodeValue = ((Element) node).outerHtml();
                if (!(nodeValue == null)) nodeValue = nodeValue.trim();

                logger.debug("openChildNodes::" + "Node Name = " + nodeName + "; Value = " + SetLength(nodeValue, 60));

                if (nodeName.equalsIgnoreCase(NODO_COLLECTION_GALLERIES)) {
                    openChildNodes(node.childNodes());
                }
                else if (nodeName.equalsIgnoreCase(NODO_COLLECTION_GALLERY)) {
                    openChildNodes(node.childNodes());
                }
                else if (nodeName.equalsIgnoreCase(NODO_COLLECTION_FOLDER)) {
                    openChildNodes(node.childNodes());
                }
                else if (nodeName.equalsIgnoreCase(NODO_COLLECTION_MULTIMEDIA)) {
                    openChildNodes(node.childNodes());
                }
                else if (nodeName.equalsIgnoreCase(NODO_COLLECTION_TRACK)) {
                    openChildNodes(node.childNodes());
                }
                else if (nodeName.equalsIgnoreCase(NODO_IMAGEN)) {

                }
                else if (nodeName.equalsIgnoreCase(NODO_VIDEO)) {

                }
                else if (nodeName.equalsIgnoreCase(NODO_SECTION)) {

                }
                else if (nodeName.equalsIgnoreCase(NODO_TITLE)) {

                }
                else if (nodeName.equalsIgnoreCase(NODO_SLOGAN)) {

                }
                else if (nodeName.equalsIgnoreCase(NODO_TRACK)) {

                }
                else if (nodeName.equalsIgnoreCase(NODO_CONTACTFORM)) {

                }
            }
        }
        logger.debug("openChildNodes:End");
    }
}
