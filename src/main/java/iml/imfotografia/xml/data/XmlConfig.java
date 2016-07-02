package iml.imfotografia.xml.data;

import iml.imfotografia.xml.data.collection.*;
import iml.imfotografia.xml.data.element.*;
import org.w3c.dom.*;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Hashtable;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by inaki.marquina on 29/06/2016.
 */
public class XmlConfig {
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

    final static Logger logger = Logger.getLogger(XmlConfig.class);

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
    public XmlConfig() {
        this._iKey = 0;
        this.title = new Title();
        this.slogan = new Slogan();
        this.contactForm = new ContactForm();
        this.tracks = new Hashtable<String, Track>();
        this.elements = new Hashtable<Integer, Object>();
    }

    public XmlConfig(String xml) throws IOException, SAXException, ParserConfigurationException, ParseException {
        this ();
        this.set_xml(xml);
        parseXml();
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

    /**
     * PRIVATE METHODS
     */
    /**
     * Parsea el xml para convertirlo en un objeto
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     * @throws ParseException
     */
    private void parseXml() throws ParserConfigurationException, SAXException, IOException, ParseException {
        logger.debug("parseXml::Begin");

        //Get Document Builder
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        //Build Document
        Document document = builder.parse(new File(get_xml()));

        //Normalize the XML Structure; It's just too important !!
        //document.getDocumentElement().normalize();

        //Here comes the root node
        Element root = document.getDocumentElement();
        logger.info("parseXml::" + "root Node: " + root.getNodeName());

        if (root.hasChildNodes())
            openChildNodes(root.getChildNodes());
    }

    /**
     * Parsea los nodos hijos de uno dado para incluirlos como objetos de tipo
     * imagen o vídeo
     * @param nList
     * @throws ParseException
     */
    private void openChildNodes(NodeList nList) throws ParseException {
        logger.debug("openChildNodes:Start");

        for (int temp = 0; temp < nList.getLength(); temp++)
        {
            Node node = nList.item(temp);
            if (node.getNodeType() == Node.ELEMENT_NODE)
            {
                Boolean bNodeContentHTML = false;

                String nodeName =  node.getNodeName();
                if (!nodeName.equals(null)) nodeName = nodeName.trim();
                String nodeValue = node.getTextContent();
                if (!(nodeValue == null)) nodeValue = nodeValue.trim();

                logger.debug("openChildNodes::" + "Node Name = " + nodeName + "; Value = " +  nodeValue);

                //Check all attributes
                if (node.hasAttributes()) {
                    if (nodeName == NODO_COLLECTION_GALLERIES) {
                        Galleries galleries = getAttrGalleries(node, collectionType.galleries);
                        this.elements.put(_iKey++, galleries);
                    }
                    else if (nodeName == NODO_COLLECTION_GALLERY) {
                        Gallery gallery = getAttrGallery(node, collectionType.gallery);
                        this.elements.put(_iKey++, gallery);
                    }
                    else if (nodeName == NODO_COLLECTION_FOLDER) {
                        Folder folder = getAttrFolder(node, collectionType.folder);
                        this.elements.put(_iKey++, folder);
                    }
                    else if (nodeName == NODO_COLLECTION_MULTIMEDIA) {
                        Multimedia multimedia = getAttrMultimedia(node, collectionType.multimedia);
                        this.elements.put(_iKey++, multimedia);
                    }
                    else if (nodeName == NODO_COLLECTION_TRACK) {
                        Tracks tracks = getAttrTracks(node, collectionType.tracks);
                        this.elements.put(_iKey++, tracks);
                    }
                    else if (nodeName == NODO_IMAGEN) {
                        Image image = getAttrImage(node, nodeType.imagen);
                        this.elements.put(_iKey++, image);
                    }
                    else if (nodeName == NODO_VIDEO) {
                        Video video = getAttrVideo(node, nodeType.video);
                        this.elements.put(_iKey++, video);
                    }
                    else if (nodeName == NODO_SECTION) {
                        Section section = getAttrSection(node, nodeType.section);
                        section.set_content(nodeValue);
                        this.elements.put(_iKey++, section);

                        bNodeContentHTML = true;
                    }
                    else if (nodeName == NODO_TITLE) {
                        Title title = getAttrTitle(node, nodeType.title);
                        title.set_content(nodeValue);
                        this.elements.put(_iKey++, title);
                    }
                    else if (nodeName == NODO_SLOGAN) {
                        Slogan slogan = getAttrSlogan(node, nodeType.slogan);
                        slogan.set_content(nodeValue);
                        this.elements.put(_iKey++, slogan);
                    }
                    else if (nodeName == NODO_TRACK) {
                        Track track = getAttrTrack(node, nodeType.track);
                        this.elements.put(_iKey++, track);
                    }
                    else if (nodeName == NODO_CONTACTFORM) {
                        ContactForm contactform = getAttrContactform(node, nodeType.contactform);
                        this.elements.put(_iKey++, contactform);
                    }
                }
                if (!bNodeContentHTML && node.hasChildNodes())
                    openChildNodes(node.getChildNodes());
            }
        }
        logger.debug("openChildNodes:End");
    }

    private void getAttribute(Node node) {
        logger.debug("getAttribute::Start");

        // get attributes names and values
        NamedNodeMap nodeMap = node.getAttributes();
        for (int i = 0; i < nodeMap.getLength(); i++) {
            Node tempNode = nodeMap.item(i);
            String sAttrName = tempNode.getNodeName();
            if (!sAttrName.equals(null)) sAttrName = sAttrName.trim().toUpperCase();
            String sAttrValue = tempNode.getNodeValue();
            if (!sAttrValue.equals(null)) sAttrValue = sAttrValue.trim();

            logger.debug("getAttribute:: " + "    Attr name : " + sAttrName + "; Value = " + sAttrValue);
        }
        logger.debug("getAttribute::End");
    }

    /**
     *
     * @param node
     * @param type
     * @return
     */
    private Galleries getAttrGalleries(Node node, collectionType type) {
        logger.debug("getAttrGalleries::Start");
        Galleries galleries = new Galleries();

        // get attributes names and values
        NamedNodeMap nodeMap = node.getAttributes();
        for (int i = 0; i < nodeMap.getLength(); i++) {
            Node tempNode = nodeMap.item(i);
            String sAttrName = tempNode.getNodeName();
            if (!sAttrName.equals(null)) sAttrName = sAttrName.trim().toUpperCase();
            String sAttrValue = tempNode.getNodeValue();
            if (!sAttrValue.equals(null)) sAttrValue = sAttrValue.trim();

            logger.debug("getAttrGalleries:: " + "    Attr name : " + sAttrName + "; Value = " + sAttrValue);

            logger.debug("getAttrGalleries::set Galleries property " + sAttrName + ":" + sAttrValue);
        }

        logger.debug("getAttrGalleries::End");
        return galleries;
    }

    /**
     *
     * @param node
     * @param type
     * @return
     * @throws ParseException
     */
    private Gallery getAttrGallery(Node node, collectionType type) throws ParseException {
        logger.debug("getAttrGallery::Start");
        Gallery gallery = new Gallery();

        // get attributes names and values
        NamedNodeMap nodeMap = node.getAttributes();
        for (int i = 0; i < nodeMap.getLength(); i++) {
            Node tempNode = nodeMap.item(i);
            String sAttrName = tempNode.getNodeName();
            if (!sAttrName.equals(null)) sAttrName = sAttrName.trim().toUpperCase();
            String sAttrValue = tempNode.getNodeValue();
            if (!sAttrValue.equals(null)) sAttrValue = sAttrValue.trim();

            logger.debug("getAttrGallery:: " + "    Attr name : " + sAttrName + "; Value = " + sAttrValue);

            if (sAttrName == "NAME") {
                gallery.set_name(sAttrValue);
            } else if (sAttrName == "SRC") {
                gallery.set_src(sAttrValue);
            } else if (sAttrName == "TITLE") {
                gallery.set_title(sAttrValue);
            } else if (sAttrName == "INFOTEXT") {
                gallery.set_infoText(sAttrValue);
            } else if (sAttrName == "KEYWORDS") {
                gallery.set_keywords(sAttrValue);
            } else if (sAttrName == "UPDATE") {
                gallery.set_update(sAttrValue);
            } else {
                logger.info("getAttrGallery::unknow Gallery property " + sAttrName + ":" + sAttrValue);
            }

            logger.debug("getAttrGallery::set Gallery property " + sAttrName + ":" + sAttrValue);
        }

        logger.debug("getAttrGallery::End");
        return gallery;
    }

    /**
     *
     * @param node
     * @param type
     * @return
     * @throws ParseException
     */
    private Folder getAttrFolder(Node node, collectionType type) throws ParseException {
        logger.debug("getAttrFolder::Start");
        Folder folder = new Folder();

        // get attributes names and values
        NamedNodeMap nodeMap = node.getAttributes();
        for (int i = 0; i < nodeMap.getLength(); i++) {
            Node tempNode = nodeMap.item(i);
            String sAttrName = tempNode.getNodeName();
            if (!sAttrName.equals(null)) sAttrName = sAttrName.trim().toUpperCase();
            String sAttrValue = tempNode.getNodeValue();
            if (!sAttrValue.equals(null)) sAttrValue = sAttrValue.trim();

            logger.debug("getAttrFolder:: " + "    Attr name : " + sAttrName + "; Value = " + sAttrValue);

            if (sAttrName == "NAME") {
                folder.set_name(sAttrValue);
            } else if (sAttrName == "SRC") {
                folder.set_src(sAttrValue);
            } else if (sAttrName == "TITLE") {
                folder.set_title(sAttrValue);
            } else if (sAttrName == "INFOTEXT") {
                folder.set_infoText(sAttrValue);
            } else if (sAttrName == "KEYWORDS") {
                folder.set_keywords(sAttrValue);
            } else if (sAttrName == "UPDATE") {
                folder.set_update(sAttrValue);
            } else {
                logger.info("getAttrFolder::unknow Folder property " + sAttrName + ":" + sAttrValue);
            }

            logger.debug("getAttrFolder::set Folder property " + sAttrName + ":" + sAttrValue);
        }

        logger.debug("getAttrFolder::End");
        return folder;
    }

    /**
     *
     * @param node
     * @param type
     * @return
     * @throws ParseException
     */
    private Multimedia getAttrMultimedia(Node node, collectionType type) throws ParseException {
        logger.debug("getAttrMultimedia::Start");
        Multimedia multimedia = new Multimedia();

        // get attributes names and values
        NamedNodeMap nodeMap = node.getAttributes();
        for (int i = 0; i < nodeMap.getLength(); i++) {
            Node tempNode = nodeMap.item(i);
            String sAttrName = tempNode.getNodeName();
            if (!sAttrName.equals(null)) sAttrName = sAttrName.trim().toUpperCase();
            String sAttrValue = tempNode.getNodeValue();
            if (!sAttrValue.equals(null)) sAttrValue = sAttrValue.trim();

            logger.debug("getAttrMultimedia:: " + "    Attr name : " + sAttrName + "; Value = " + sAttrValue);

            if (sAttrName == "NAME") {
                multimedia.set_name(sAttrValue);
            } else if (sAttrName == "SRC") {
                multimedia.set_src(sAttrValue);
            } else if (sAttrName == "TITLE") {
                multimedia.set_title(sAttrValue);
            } else if (sAttrName == "INFOTEXT") {
                multimedia.set_infoText(sAttrValue);
            } else if (sAttrName == "KEYWORDS") {
                multimedia.set_keywords(sAttrValue);
            } else if (sAttrName == "UPDATE") {
                multimedia.set_update(sAttrValue);
            } else {
                logger.info("getAttrMultimedia::unknow Multimedia property " + sAttrName + ":" + sAttrValue);
            }

            logger.debug("getAttrMultimedia::set Multimedia property " + sAttrName + ":" + sAttrValue);
        }

        logger.debug("getAttrMultimedia::End");
        return multimedia;
    }

    /**
     *
     * @param node
     * @param type
     * @return
     */
    private Tracks getAttrTracks(Node node, collectionType type) {
        logger.debug("getAttrTracks::Start");
        Tracks tracks = new Tracks();

        // get attributes names and values
        NamedNodeMap nodeMap = node.getAttributes();
        for (int i = 0; i < nodeMap.getLength(); i++) {
            Node tempNode = nodeMap.item(i);
            String sAttrName = tempNode.getNodeName();
            if (!sAttrName.equals(null)) sAttrName = sAttrName.trim().toUpperCase();
            String sAttrValue = tempNode.getNodeValue();
            if (!sAttrValue.equals(null)) sAttrValue = sAttrValue.trim();

            logger.debug("getAttrTracks:: " + "    Attr name : " + sAttrName + "; Value = " + sAttrValue);

            if (sAttrName == "NAME") {
                tracks.set_name(sAttrValue);
            } else {
                logger.info("getAttrTracks::unknow Tracks property " + sAttrName + ":" + sAttrValue);
            }

            logger.debug("getAttrTracks::set Tracks property " + sAttrName + ":" + sAttrValue);
        }

        logger.debug("getAttrTracks::End");
        return tracks;
    }

    /**
     * Parsea el nodo para obtener un objeto de tipo imagen
     * @param node
     * @param type
     * @return
     * @throws ParseException
     */
    private Image getAttrImage(Node node, nodeType type) throws ParseException {
        logger.debug("getAttrImage::Start");
        Image image = new Image();

        // get attributes names and values
        NamedNodeMap nodeMap = node.getAttributes();
        for (int i = 0; i < nodeMap.getLength(); i++) {
            Node tempNode = nodeMap.item(i);
            String sAttrName = tempNode.getNodeName();
            if (!sAttrName.equals(null)) sAttrName = sAttrName.trim().toUpperCase();
            String sAttrValue = tempNode.getNodeValue();
            if (!sAttrValue.equals(null)) sAttrValue = sAttrValue.trim();

            logger.debug("getAttrPhoto:: " + "    Attr name : " + sAttrName + "; Value = " + sAttrValue);

            if (sAttrName == "ID") {
                image.set_id(sAttrValue);
            } else {
                logger.info("getAttrPhoto::unknow Image property " + sAttrName + ":" + sAttrValue);
            }
            logger.debug("getAttrPhoto::set Image property " + sAttrName + ":" + sAttrValue);
        }
        logger.debug("getAttrImage::End");
        return image;
    }

    /**
     * Parsea el nodo para obtener un objeto de tipo vídeo
     * @param node
     * @param type
     * @return
     * @throws ParseException
     */
    private Video getAttrVideo(Node node, nodeType type) throws ParseException {
        logger.debug("getAttrVideo::Start");
        Video video = new Video();

        // get attributes names and values
        NamedNodeMap nodeMap = node.getAttributes();
        for (int i = 0; i < nodeMap.getLength(); i++) {
            Node tempNode = nodeMap.item(i);
            String sAttrName = tempNode.getNodeName();
            if (!sAttrName.equals(null)) sAttrName = sAttrName.trim().toUpperCase();
            String sAttrValue = tempNode.getNodeValue();
            if (!sAttrValue.equals(null)) sAttrValue = sAttrValue.trim();

            logger.debug("getAttrPhoto:: " + "    Attr name : " + sAttrName + "; Value = " + sAttrValue);

            if (sAttrName == "ID") {
                video.set_id(sAttrValue);
            } else {
                logger.info("getAttrVideo::unknow Video property " + sAttrName + ":" + sAttrValue);
            }

            logger.debug("getAttrVideo::set Video property " + sAttrName + ":" + sAttrValue);
        }
        logger.debug("getAttrVideo::End");
        return video;
    }

    /**
     *
     * @param node
     * @param type
     * @return
     * @throws ParseException
     */
    private Section getAttrSection(Node node, nodeType type) throws ParseException {
        logger.debug("getAttrSection::Start");
        Section section = new Section();

        // get attributes names and values
        NamedNodeMap nodeMap = node.getAttributes();
        for (int i = 0; i < nodeMap.getLength(); i++) {
            Node tempNode = nodeMap.item(i);
            String sAttrName = tempNode.getNodeName();
            if (!sAttrName.equals(null)) sAttrName = sAttrName.trim().toUpperCase();
            String sAttrValue = tempNode.getNodeValue();
            if (!sAttrValue.equals(null)) sAttrValue = sAttrValue.trim();

            logger.debug("getAttrSection:: " + "    Attr name : " + sAttrName + "; Value = " + sAttrValue);

            if (sAttrName == "NAME") {
                section.set_name(sAttrValue);
            } else if (sAttrName == "WIDTH") {
                section.set_width(sAttrValue);
            } else if (sAttrName == "HEIGHT") {
                section.set_height(sAttrValue);
            } else if (sAttrName == "BYDEFAULT") {
                section.set_byDefault(sAttrValue);
            } else if (sAttrName == "UPDATE") {
                section.set_update(sAttrValue);
            } else {
                logger.info("getAttrPhoto::unknow Section property " + sAttrName + ":" + sAttrValue);
            }
            logger.debug("getAttrSection::set Section property " + sAttrName + ":" + sAttrValue);
        }

        logger.debug("getAttrSection::End");
        return section;
    }

    /**
     *
     * @param node
     * @param type
     * @return
     */
    private Title getAttrTitle(Node node, nodeType type) {
        logger.debug("getAttrTitle::Start");
        Title title = new Title();

        // get attributes names and values
        NamedNodeMap nodeMap = node.getAttributes();
        for (int i = 0; i < nodeMap.getLength(); i++) {
            Node tempNode = nodeMap.item(i);
            String sAttrName = tempNode.getNodeName();
            if (!sAttrName.equals(null)) sAttrName = sAttrName.trim().toUpperCase();
            String sAttrValue = tempNode.getNodeValue();
            if (!sAttrValue.equals(null)) sAttrValue = sAttrValue.trim();

            logger.debug("getAttrTitle:: " + "    Attr name : " + sAttrName + "; Value = " + sAttrValue);

            logger.debug("getAttrTitle::set Title property " + sAttrName + ":" + sAttrValue);
        }

        logger.debug("getAttrTitle::End");
        return title;
    }

    private Slogan getAttrSlogan(Node node, nodeType type) {
        logger.debug("getAttrSlogan::Start");
        Slogan slogan = new Slogan();

        // get attributes names and values
        NamedNodeMap nodeMap = node.getAttributes();
        for (int i = 0; i < nodeMap.getLength(); i++) {
            Node tempNode = nodeMap.item(i);
            String sAttrName = tempNode.getNodeName();
            if (!sAttrName.equals(null)) sAttrName = sAttrName.trim().toUpperCase();
            String sAttrValue = tempNode.getNodeValue();
            if (!sAttrValue.equals(null)) sAttrValue = sAttrValue.trim();

            logger.debug("getAttrSlogan:: " + "    Attr name : " + sAttrName + "; Value = " + sAttrValue);

            logger.debug("getAttrSlogan::set Slogan property " + sAttrName + ":" + sAttrValue);
        }

        logger.debug("getAttrSlogan::End");
        return slogan;
    }

    private Track getAttrTrack(Node node, nodeType type) {
        logger.debug("getAttrTrack::Start");
        Track track = new Track();

        // get attributes names and values
        NamedNodeMap nodeMap = node.getAttributes();
        for (int i = 0; i < nodeMap.getLength(); i++) {
            Node tempNode = nodeMap.item(i);
            String sAttrName = tempNode.getNodeName();
            if (!sAttrName.equals(null)) sAttrName = sAttrName.trim().toUpperCase();
            String sAttrValue = tempNode.getNodeValue();
            if (!sAttrValue.equals(null)) sAttrValue = sAttrValue.trim();

            logger.debug("getAttrTrack:: " + "    Attr name : " + sAttrName + "; Value = " + sAttrValue);

            if (sAttrName == "SRC") {
                track.set_src(sAttrValue);
            } else if (sAttrName == "ARTIST") {
                track.set_artist(sAttrValue);
            } else if (sAttrValue == "NAME") {
                track.set_name(sAttrValue);
            } else {
                logger.info("getAttrPhoto::unknow Track property " + sAttrName + ":" + sAttrValue);
            }

            logger.debug("getAttrTrack::set Track property " + sAttrName + ":" + sAttrValue);
        }

        logger.debug("getAttrTrack::End");
        return track;
    }

    private ContactForm getAttrContactform(Node node, nodeType type) {
        logger.debug("getAttrContactform::Start");
        ContactForm contactform = new ContactForm();

        // get attributes names and values
        NamedNodeMap nodeMap = node.getAttributes();
        for (int i = 0; i < nodeMap.getLength(); i++) {
            Node tempNode = nodeMap.item(i);
            String sAttrName = tempNode.getNodeName();
            if (!sAttrName.equals(null)) sAttrName = sAttrName.trim().toUpperCase();
            String sAttrValue = tempNode.getNodeValue();
            if (!sAttrValue.equals(null)) sAttrValue = sAttrValue.trim();

            logger.debug("getAttrContactform:: " + "    Attr name : " + sAttrName + "; Value = " + sAttrValue);

            logger.debug("getAttrContactform::set ContactForm property " + sAttrName + ":" + sAttrValue);
        }

        logger.debug("getAttrContactform::End");
        return contactform;
    }
}

