package iml.imfotografia.xml.config;

import iml.imfotografia.xml.config.structs.*;
import org.apache.log4j.Logger;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.LinkedHashMap;

import static iml.imfotografia.utils.Text.SetLength;
import static iml.imfotografia.utils.Xml.innerXml;
import static iml.imfotografia.utils.Xml.normalize;

/**
 * Created by inaki.marquina on 29/06/2016.
 */
public class xborrar_XmlConfig {
    private String _xml;
    private String _title;
    private String _infoText;
    private String _keywords;
    private Integer _iKey;
    public Title title;
    public Slogan slogan;
    public ContactForm contactForm;
    public LinkedHashMap<String, Track> tracks;
    public LinkedHashMap<Integer, Object> elements;

    final static Logger logger = Logger.getLogger(xborrar_XmlConfig.class);

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
    public xborrar_XmlConfig() {
        this._iKey = 0;
        this.title = new Title();
        this.slogan = new Slogan();
        this.contactForm = new ContactForm();
        this.tracks = new LinkedHashMap<String, Track>();
        this.elements = new LinkedHashMap<Integer, Object>();
    }

    public xborrar_XmlConfig(String xml) throws IOException, ParserConfigurationException, ParseException, SAXException, XPathExpressionException {
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
     * PRIVATE METHODS
     */
    /**
     * Parsea el xml para convertirlo en un objeto
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws ParseException
     */
    private void parseXml() throws ParserConfigurationException, IOException, ParseException, SAXException, XPathExpressionException {
        logger.debug("Begin");

        //Get Document Builder
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        //Build Document
        Document document = builder.parse(new File(get_xml()));

        normalize(document);

        //Normalize the XML Structure; It's just too important !!
        document.getDocumentElement().normalize();

        //Here comes the root node
        Element root = document.getDocumentElement();
        logger.info("root Node: " + root.getNodeName());

        if (root.hasChildNodes())
            openChildNodes(root.getChildNodes());

        logger.debug("End");
    }

    private void writeXml() {
        /*
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        //initialize StreamResult with File object to save to file
        StreamResult result = new StreamResult(new StringWriter());
        DOMSource source = new DOMSource(doc);
        transformer.transform(source, result);

        String xmlString = result.getWriter().toString();
        System.out.println(xmlString);
        */
    }

    /**
     * Parsea los nodos hijos de uno dado para incluirlos como objetos de tipo
     * imagen o vídeo
     * @param nList
     * @throws ParseException
     */
    private void openChildNodes(NodeList nList) throws ParseException {
        logger.debug("Start");

        for (int temp = 0; temp < nList.getLength(); temp++)
        {
            Node node = nList.item(temp);
            if (node.getNodeType() == Node.ELEMENT_NODE)
            {
                Boolean bNodeContentHTML = false;

                String nodeName =  node.getNodeName();
                if (!nodeName.equals(null)) nodeName = nodeName.trim();

                logger.info("Node Name = " + nodeName + "; Value = " + SetLength(innerXml(node, true), 60));

                if (nodeName.equalsIgnoreCase(NODO_COLLECTION_GALLERIES)) {
                    Galleries galleries = getAttrGalleries(node, collectionType.galleries);
                    this.elements.put(_iKey++, galleries);
                }
                else if (nodeName.equalsIgnoreCase(NODO_COLLECTION_GALLERY)) {
                    Gallery gallery = getAttrGallery(node, collectionType.gallery);
                    this.elements.put(_iKey++, gallery);
                }
                else if (nodeName.equalsIgnoreCase(NODO_COLLECTION_FOLDER)) {
                    Folder folder = getAttrFolder(node, collectionType.folder);
                    this.elements.put(_iKey++, folder);
                }
                else if (nodeName.equalsIgnoreCase(NODO_COLLECTION_MULTIMEDIA)) {
                    Multimedia multimedia = getAttrMultimedia(node, collectionType.multimedia);
                    this.elements.put(_iKey++, multimedia);
                }
                else if (nodeName.equalsIgnoreCase(NODO_COLLECTION_TRACK)) {
                    Tracks tracks = getAttrTracks(node, collectionType.tracks);
                    this.elements.put(_iKey++, tracks);
                }
                else if (nodeName.equalsIgnoreCase(NODO_IMAGEN)) {
                    Image image = getAttrImage(node, nodeType.imagen);
                    this.elements.put(_iKey++, image);
                }
                else if (nodeName.equalsIgnoreCase(NODO_VIDEO)) {
                    Video video = getAttrVideo(node, nodeType.video);
                    this.elements.put(_iKey++, video);
                }
                else if (nodeName.equalsIgnoreCase(NODO_SECTION)) {
                    Section section = getAttrSection(node, nodeType.section);
                    section.set_content(innerXml(node));
                    this.elements.put(_iKey++, section);

                    bNodeContentHTML = true;
                }
                else if (nodeName.equalsIgnoreCase(NODO_TITLE)) {
                    Title title = getAttrTitle(node, nodeType.title);
                    title.set_content(innerXml(node));
                    this.elements.put(_iKey++, title);

                    bNodeContentHTML = true;
                }
                else if (nodeName.equalsIgnoreCase(NODO_SLOGAN)) {
                    Slogan slogan = getAttrSlogan(node, nodeType.slogan);
                    slogan.set_content(innerXml(node));
                    this.elements.put(_iKey++, slogan);

                    bNodeContentHTML = true;
                }
                else if (nodeName.equalsIgnoreCase(NODO_TRACK)) {
                    Track track = getAttrTrack(node, nodeType.track);
                    this.elements.put(_iKey++, track);
                }
                else if (nodeName.equalsIgnoreCase(NODO_CONTACTFORM)) {
                    ContactForm contactform = getAttrContactform(node, nodeType.contactform);
                    this.elements.put(_iKey++, contactform);
                }

                if (!bNodeContentHTML && node.hasChildNodes())
                    openChildNodes(node.getChildNodes());
            }
        }
        logger.debug("End");
    }

    private void getAttribute(Node node) {
        logger.debug("Start");

        // get attributes names and values
        NamedNodeMap nodeMap = node.getAttributes();
        for (int i = 0; i < nodeMap.getLength(); i++) {
            Node tempNode = nodeMap.item(i);
            String sAttrName = tempNode.getNodeName();
            if (!sAttrName.equals(null)) sAttrName = sAttrName.trim().toUpperCase();
            String sAttrValue = tempNode.getNodeValue();
            if (!sAttrValue.equals(null)) sAttrValue = sAttrValue.trim();

            logger.debug("    Attr name : " + sAttrName + "; Value = " + sAttrValue);
        }
        logger.debug("End");
    }

    /**
     *
     * @param node
     * @param type
     * @return
     */
    private Galleries getAttrGalleries(Node node, collectionType type) {
        logger.debug("Start");
        Galleries galleries = new Galleries();

        // get attributes names and values
        NamedNodeMap nodeMap = node.getAttributes();
        for (int i = 0; i < nodeMap.getLength(); i++) {
            Node tempNode = nodeMap.item(i);
            String sAttrName = tempNode.getNodeName();
            if (!sAttrName.equals(null)) sAttrName = sAttrName.trim().toUpperCase();
            String sAttrValue = tempNode.getNodeValue();
            if (!sAttrValue.equals(null)) sAttrValue = sAttrValue.trim();

            logger.info("    Attr name : " + sAttrName + "; Value = " + sAttrValue);

            logger.debug("set Galleries property " + sAttrName + ":" + sAttrValue);
        }

        logger.debug("End");
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
        logger.debug("Start");
        Gallery gallery = new Gallery();

        // get attributes names and values
        NamedNodeMap nodeMap = node.getAttributes();
        for (int i = 0; i < nodeMap.getLength(); i++) {
            Node tempNode = nodeMap.item(i);
            String sAttrName = tempNode.getNodeName();
            if (!sAttrName.equals(null)) sAttrName = sAttrName.trim().toUpperCase();
            String sAttrValue = tempNode.getNodeValue();
            if (!sAttrValue.equals(null)) sAttrValue = sAttrValue.trim();

            logger.info("    Attr name : " + sAttrName + "; Value = " + sAttrValue);

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
                logger.info("unknow Gallery property " + sAttrName + ":" + sAttrValue);
            }

            logger.debug("set Gallery property " + sAttrName + ":" + sAttrValue);
        }

        logger.debug("End");
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
        logger.debug("Start");
        Folder folder = new Folder();

        // get attributes names and values
        NamedNodeMap nodeMap = node.getAttributes();
        for (int i = 0; i < nodeMap.getLength(); i++) {
            Node tempNode = nodeMap.item(i);
            String sAttrName = tempNode.getNodeName();
            if (!sAttrName.equals(null)) sAttrName = sAttrName.trim().toUpperCase();
            String sAttrValue = tempNode.getNodeValue();
            if (!sAttrValue.equals(null)) sAttrValue = sAttrValue.trim();

            logger.info("    Attr name : " + sAttrName + "; Value = " + sAttrValue);

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
                logger.info("unknow Folder property " + sAttrName + ":" + sAttrValue);
            }

            logger.debug("set Folder property " + sAttrName + ":" + sAttrValue);
        }

        logger.debug("End");
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
        logger.debug("Start");
        Multimedia multimedia = new Multimedia();

        // get attributes names and values
        NamedNodeMap nodeMap = node.getAttributes();
        for (int i = 0; i < nodeMap.getLength(); i++) {
            Node tempNode = nodeMap.item(i);
            String sAttrName = tempNode.getNodeName();
            if (!sAttrName.equals(null)) sAttrName = sAttrName.trim().toUpperCase();
            String sAttrValue = tempNode.getNodeValue();
            if (!sAttrValue.equals(null)) sAttrValue = sAttrValue.trim();

            logger.info("    Attr name : " + sAttrName + "; Value = " + sAttrValue);

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
                logger.info("unknow Multimedia property " + sAttrName + ":" + sAttrValue);
            }

            logger.debug("set Multimedia property " + sAttrName + ":" + sAttrValue);
        }

        logger.debug("End");
        return multimedia;
    }

    /**
     *
     * @param node
     * @param type
     * @return
     */
    private Tracks getAttrTracks(Node node, collectionType type) {
        logger.debug("Start");
        Tracks tracks = new Tracks();

        // get attributes names and values
        NamedNodeMap nodeMap = node.getAttributes();
        for (int i = 0; i < nodeMap.getLength(); i++) {
            Node tempNode = nodeMap.item(i);
            String sAttrName = tempNode.getNodeName();
            if (!sAttrName.equals(null)) sAttrName = sAttrName.trim().toUpperCase();
            String sAttrValue = tempNode.getNodeValue();
            if (!sAttrValue.equals(null)) sAttrValue = sAttrValue.trim();

            logger.info("    Attr name : " + sAttrName + "; Value = " + sAttrValue);

            if (sAttrName == "NAME") {
                tracks.set_name(sAttrValue);
            } else {
                logger.info("unknow Tracks property " + sAttrName + ":" + sAttrValue);
            }

            logger.debug("set Tracks property " + sAttrName + ":" + sAttrValue);
        }

        logger.debug("End");
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
        logger.debug("Start");
        Image image = new Image();

        // get attributes names and values
        NamedNodeMap nodeMap = node.getAttributes();
        for (int i = 0; i < nodeMap.getLength(); i++) {
            Node tempNode = nodeMap.item(i);
            String sAttrName = tempNode.getNodeName();
            if (!sAttrName.equals(null)) sAttrName = sAttrName.trim().toUpperCase();
            String sAttrValue = tempNode.getNodeValue();
            if (!sAttrValue.equals(null)) sAttrValue = sAttrValue.trim();

            logger.info("    Attr name : " + sAttrName + "; Value = " + sAttrValue);

            if (sAttrName == "ID") {
                image.set_id(sAttrValue);
            } else {
                logger.info("unknow Image property " + sAttrName + ":" + sAttrValue);
            }
            logger.debug("set Image property " + sAttrName + ":" + sAttrValue);
        }
        logger.debug("End");
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
        logger.debug("Start");
        Video video = new Video();

        // get attributes names and values
        NamedNodeMap nodeMap = node.getAttributes();
        for (int i = 0; i < nodeMap.getLength(); i++) {
            Node tempNode = nodeMap.item(i);
            String sAttrName = tempNode.getNodeName();
            if (!sAttrName.equals(null)) sAttrName = sAttrName.trim().toUpperCase();
            String sAttrValue = tempNode.getNodeValue();
            if (!sAttrValue.equals(null)) sAttrValue = sAttrValue.trim();

            logger.info("    Attr name : " + sAttrName + "; Value = " + sAttrValue);

            if (sAttrName == "ID") {
                video.set_id(sAttrValue);
            } else {
                logger.info("unknow Video property " + sAttrName + ":" + sAttrValue);
            }

            logger.debug("set Video property " + sAttrName + ":" + sAttrValue);
        }
        logger.debug("End");
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
        logger.debug("Start");
        Section section = new Section();

        // get attributes names and values
        NamedNodeMap nodeMap = node.getAttributes();
        for (int i = 0; i < nodeMap.getLength(); i++) {
            Node tempNode = nodeMap.item(i);
            String sAttrName = tempNode.getNodeName();
            if (!sAttrName.equals(null)) sAttrName = sAttrName.trim().toUpperCase();
            String sAttrValue = tempNode.getNodeValue();
            if (!sAttrValue.equals(null)) sAttrValue = sAttrValue.trim();

            logger.info("    Attr name : " + sAttrName + "; Value = " + sAttrValue);

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
                logger.info("unknow Section property " + sAttrName + ":" + sAttrValue);
            }
            logger.debug("set Section property " + sAttrName + ":" + sAttrValue);
        }

        logger.debug("End");
        return section;
    }

    /**
     *
     * @param node
     * @param type
     * @return
     */
    private Title getAttrTitle(Node node, nodeType type) {
        logger.debug("Start");
        Title title = new Title();

        // get attributes names and values
        NamedNodeMap nodeMap = node.getAttributes();
        for (int i = 0; i < nodeMap.getLength(); i++) {
            Node tempNode = nodeMap.item(i);
            String sAttrName = tempNode.getNodeName();
            if (!sAttrName.equals(null)) sAttrName = sAttrName.trim().toUpperCase();
            String sAttrValue = tempNode.getNodeValue();
            if (!sAttrValue.equals(null)) sAttrValue = sAttrValue.trim();

            logger.info("    Attr name : " + sAttrName + "; Value = " + sAttrValue);

            logger.debug("set Title property " + sAttrName + ":" + sAttrValue);
        }

        logger.debug("End");
        return title;
    }

    private Slogan getAttrSlogan(Node node, nodeType type) {
        logger.debug("Start");
        Slogan slogan = new Slogan();

        // get attributes names and values
        NamedNodeMap nodeMap = node.getAttributes();
        for (int i = 0; i < nodeMap.getLength(); i++) {
            Node tempNode = nodeMap.item(i);
            String sAttrName = tempNode.getNodeName();
            if (!sAttrName.equals(null)) sAttrName = sAttrName.trim().toUpperCase();
            String sAttrValue = tempNode.getNodeValue();
            if (!sAttrValue.equals(null)) sAttrValue = sAttrValue.trim();

            logger.info("    Attr name : " + sAttrName + "; Value = " + sAttrValue);

            logger.debug("set Slogan property " + sAttrName + ":" + sAttrValue);
        }

        logger.debug("End");
        return slogan;
    }

    private Track getAttrTrack(Node node, nodeType type) {
        logger.debug("Start");
        Track track = new Track();

        // get attributes names and values
        NamedNodeMap nodeMap = node.getAttributes();
        for (int i = 0; i < nodeMap.getLength(); i++) {
            Node tempNode = nodeMap.item(i);
            String sAttrName = tempNode.getNodeName();
            if (!sAttrName.equals(null)) sAttrName = sAttrName.trim().toUpperCase();
            String sAttrValue = tempNode.getNodeValue();
            if (!sAttrValue.equals(null)) sAttrValue = sAttrValue.trim();

            logger.info("    Attr name : " + sAttrName + "; Value = " + sAttrValue);

            if (sAttrName == "SRC") {
                track.set_src(sAttrValue);
            } else if (sAttrName == "ARTIST") {
                track.set_artist(sAttrValue);
            } else if (sAttrValue == "NAME") {
                track.set_name(sAttrValue);
            } else {
                logger.info("unknow Track property " + sAttrName + ":" + sAttrValue);
            }

            logger.debug("set Track property " + sAttrName + ":" + sAttrValue);
        }

        logger.debug("End");
        return track;
    }

    private ContactForm getAttrContactform(Node node, nodeType type) {
        logger.debug("Start");
        ContactForm contactform = new ContactForm();

        // get attributes names and values
        NamedNodeMap nodeMap = node.getAttributes();
        for (int i = 0; i < nodeMap.getLength(); i++) {
            Node tempNode = nodeMap.item(i);
            String sAttrName = tempNode.getNodeName();
            if (!sAttrName.equals(null)) sAttrName = sAttrName.trim().toUpperCase();
            String sAttrValue = tempNode.getNodeValue();
            if (!sAttrValue.equals(null)) sAttrValue = sAttrValue.trim();

            logger.info("    Attr name : " + sAttrName + "; Value = " + sAttrValue);

            logger.debug("set ContactForm property " + sAttrName + ":" + sAttrValue);
        }

        logger.debug("End");
        return contactform;
    }
}

