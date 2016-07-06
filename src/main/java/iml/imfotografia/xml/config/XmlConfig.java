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

import static iml.imfotografia.utils.Text.SetLength;
import static iml.imfotografia.utils.Xml.innerXml;
import static iml.imfotografia.utils.Xml.normalize;

/**
 * Created by inaki.marquina on 06/07/2016.
 */
public class XmlConfig {
    private String _xml;
    Config config;

    final static Logger logger = Logger.getLogger(XmlConfig.class);

    /**
     * CONSTANTS
     */
    private static final String NODO_GALLERIES = "galleries";
    private static final String NODO_GALLERY = "gallery";
    private static final String NODO_FOLDER = "folder";
    private static final String NODO_MULTIMEDIA = "multimedia";
    private static final String NODO_TRACKS = "tracks";
    private static final String NODO_SECTION = "section";
    private static final String NODO_TITLE = "title";
    private static final String NODO_SLOGAN = "slogan";
    private static final String NODO_CONTACTFORM = "contactform";
    private static final String NODO_IMAGEN = "img";
    private static final String NODO_VIDEO = "vid";
    private static final String NODO_TRACK = "track";
    private static final String ATTRIBUTE_TITLE = "TITLE";
    private static final String ATTRIBUTE_INFOTEXT = "INFOTEXT";
    private static final String ATTRIBUTE_KEYWORDS = "KEYWORDS";

    /**
     * CONSTRUCTORS
     */
    public XmlConfig() {
        this._xml = "";
        config = new Config();
    }

    public XmlConfig(String xml) throws SAXException, ParserConfigurationException, XPathExpressionException, IOException, ParseException {
        this ();
        this.set_xml(xml);

        parseXml();
    }

    /**
     * GETTER / SETTER
     */
    public String get_xml() {
        return this._xml;
    }

    public void set_xml(String xml) {
        this._xml = xml;
    }

    /**
     * PUBLIC METHODS
     */
    /**
     *
     * @throws XPathExpressionException
     * @throws IOException
     * @throws SAXException
     * @throws ParserConfigurationException
     */
    private void parseXml() throws XPathExpressionException, IOException, SAXException, ParserConfigurationException, ParseException {
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

        getAttrConfig(root);

        if (root.hasChildNodes())
            openChildNodes(root.getChildNodes(), this.config);

        logger.debug("End");
    }

    private void openChildNodes(NodeList nList, Object object) throws ParseException {
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

                if (object instanceof Config ) {
                    if (nodeName.equalsIgnoreCase(NODO_GALLERIES)) {
                        Galleries galleries = getAttrGalleries(node);
                        this.config.addGalleries(galleries);
                        if (node.hasChildNodes())
                            openChildNodes(node.getChildNodes(), galleries);
                    } else if (nodeName.equalsIgnoreCase(NODO_FOLDER)) {
                        Folder folder = getAttrFolder(node);
                        this.config.addFolder(folder);
                        if (node.hasChildNodes())
                            openChildNodes(node.getChildNodes(), folder);
                    } else if (nodeName.equalsIgnoreCase(NODO_SECTION)) {
                        Section section = getAttrSection(node);
                        this.config.addSection(section);
                    } else if (nodeName.equalsIgnoreCase(NODO_TITLE)) {
                        Title title = getAttrTitle(node);
                        this.config.addTitle(title);
                    } else if (nodeName.equalsIgnoreCase(NODO_SLOGAN)) {
                        Slogan slogan = getAttrSlogan(node);
                        this.config.addSlogan(slogan);
                    } else if (nodeName.equalsIgnoreCase(NODO_TRACKS)) {
                        Tracks tracks = getAttrTracks(node);
                        this.config.addTracks(tracks);
                        if (node.hasChildNodes())
                            openChildNodes(node.getChildNodes(), tracks);
                    } else if (nodeName.equalsIgnoreCase(NODO_CONTACTFORM)) {
                        ContactForm contactForm = getAttrContactForm(node);
                        this.config.addContactForm(contactForm);
                    } else {
                        logger.info("unknow Node of Config: " + nodeName);
                    }
                } else if (object instanceof Galleries) {
                    Galleries galleries = (Galleries)object;

                    if (nodeName.equalsIgnoreCase(NODO_FOLDER)) {
                        Folder folder = getAttrFolder(node);
                        galleries.addFolder(folder);
                        if (node.hasChildNodes())
                            openChildNodes(node.getChildNodes(), folder);
                    } else if (nodeName.equalsIgnoreCase(NODO_GALLERY)) {
                        Gallery gallery = getAttrGallery(node);
                        galleries.addGallery(gallery);
                        if (node.hasChildNodes())
                            openChildNodes(node.getChildNodes(), gallery);
                    } else if (nodeName.equalsIgnoreCase(NODO_MULTIMEDIA)) {
                        Multimedia multimedia = getAttrMultimedia(node);
                        galleries.addMultimedia(multimedia);
                        if (node.hasChildNodes())
                            openChildNodes(node.getChildNodes(), multimedia);
                    } else {
                        logger.info("unknow Node of Galleries: " + nodeName);
                    }
                } else if (object instanceof Gallery) {
                    Gallery gallery = (Gallery)object;

                    if (nodeName.equalsIgnoreCase(NODO_IMAGEN)) {
                        Image image = getAttrImage(node);
                    } else {
                        logger.info("unknow Node of Gallery: " + nodeName);
                    }
                } else if (object instanceof Folder) {
                    Folder folder = (Folder)object;

                    if (nodeName.equalsIgnoreCase(NODO_GALLERY)) {
                        Gallery gallery = getAttrGallery(node);
                        folder.addGallery(gallery);
                        if (node.hasChildNodes())
                            openChildNodes(node.getChildNodes(), gallery);
                    } else if (nodeName.equalsIgnoreCase(NODO_SECTION)) {
                        Section section = getAttrSection(node);
                        folder.addSection(section);
                    } else {
                        logger.info("unknow Node of Folder: " + nodeName);
                    }
                } else if (object instanceof Multimedia) {
                    Multimedia multimedia = (Multimedia)object;

                    if (nodeName.equalsIgnoreCase(NODO_VIDEO)) {
                        Video video = getAttrVideo(node);
                        multimedia.addVideo(video);
                    } else {
                        logger.info("unknow Node of Multimedia: " + nodeName);
                    }
                } else if (object instanceof Tracks) {
                    Tracks tracks = (Tracks)object;

                    if (nodeName.equalsIgnoreCase(NODO_TRACK)) {
                        Track track = getAttrTrack(node);
                        tracks.addTrack(track);
                    }
                } else {
                    logger.info("unknow Object instanceof: " + nodeName);
                }
            }
        }
        logger.debug("End");
    }

    private void getAttrConfig(Node node) {
        logger.debug("Start");

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
                this.config.set_title(sAttrValue);
            } else if (sAttrName.equalsIgnoreCase(ATTRIBUTE_INFOTEXT)) {
                this.config.set_infoText(sAttrValue);
            } else if (sAttrName.equalsIgnoreCase(ATTRIBUTE_KEYWORDS)) {
                this.config.set_keywords(sAttrValue);
            } else {
                logger.info("unknow Config property " + sAttrName + ":" + sAttrValue);
            }

            logger.debug("set Config property " + sAttrName + ":" + sAttrValue);
        }

        logger.debug("End");
    }

    private Galleries getAttrGalleries(Node node) {
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

    private Folder getAttrFolder(Node node) throws ParseException {
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

            if (sAttrName.equalsIgnoreCase("NAME")) {
                folder.set_name(sAttrValue);
            } else if (sAttrName.equalsIgnoreCase("SRC")) {
                folder.set_src(sAttrValue);
            } else if (sAttrName.equalsIgnoreCase("TITLE")) {
                folder.set_title(sAttrValue);
            } else if (sAttrName.equalsIgnoreCase("INFOTEXT")) {
                folder.set_infoText(sAttrValue);
            } else if (sAttrName.equalsIgnoreCase("KEYWORDS")) {
                folder.set_keywords(sAttrValue);
            } else if (sAttrName.equalsIgnoreCase("UPDATE")) {
                folder.set_update(sAttrValue);
            } else {
                logger.info("unknow Folder property " + sAttrName + ":" + sAttrValue);
            }

            logger.debug("set Folder property " + sAttrName + ":" + sAttrValue);
        }

        logger.debug("End");
        return folder;
    }

    private Gallery getAttrGallery(Node node) throws ParseException {
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

    private Multimedia getAttrMultimedia(Node node) throws ParseException {
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

    private Section getAttrSection(Node node) throws ParseException {
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

            if (sAttrName.equalsIgnoreCase("NAME")) {
                section.set_name(sAttrValue);
            } else if (sAttrName.equalsIgnoreCase("WIDTH")) {
                section.set_width(sAttrValue);
            } else if (sAttrName.equalsIgnoreCase("HEIGHT")) {
                section.set_height(sAttrValue);
            } else if (sAttrName.equalsIgnoreCase("BYDEFAULT")) {
                section.set_byDefault(sAttrValue);
            } else if (sAttrName.equalsIgnoreCase("UPDATE")) {
                section.set_update(sAttrValue);
            } else {
                logger.info("unknow Section property " + sAttrName + ":" + sAttrValue);
            }
            logger.debug("set Section property " + sAttrName + ":" + sAttrValue);
        }

        logger.debug("End");
        return section;
    }

    private Title getAttrTitle(Node node) {
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

    private Slogan getAttrSlogan(Node node) {
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

    private Tracks getAttrTracks(Node node) {
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

            if (sAttrName.equalsIgnoreCase("NAME")) {
                tracks.set_name(sAttrValue);
            } else {
                logger.info("unknow Tracks property " + sAttrName + ":" + sAttrValue);
            }

            logger.debug("set Tracks property " + sAttrName + ":" + sAttrValue);
        }

        logger.debug("End");
        return tracks;
    }

    private ContactForm getAttrContactForm(Node node) {
        logger.debug("Start");
        ContactForm contactForm = new ContactForm();

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
        return contactForm;
    }

    private Image getAttrImage(Node node) throws ParseException {
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

            if (sAttrName.equalsIgnoreCase("ID")) {
                image.set_id(sAttrValue);
            } else {
                logger.info("unknow Image property " + sAttrName + ":" + sAttrValue);
            }
            logger.debug("set Image property " + sAttrName + ":" + sAttrValue);
        }
        logger.debug("End");
        return image;
    }

    private Video getAttrVideo(Node node) throws ParseException {
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

            if (sAttrName.equalsIgnoreCase("ID")) {
                video.set_id(sAttrValue);
            } else {
                logger.info("unknow Video property " + sAttrName + ":" + sAttrValue);
            }

            logger.debug("set Video property " + sAttrName + ":" + sAttrValue);
        }
        logger.debug("End");
        return video;
    }

    private Track getAttrTrack(Node node) {
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

            if (sAttrName.equalsIgnoreCase("SRC")) {
                track.set_src(sAttrValue);
            } else if (sAttrName.equalsIgnoreCase("ARTIST")) {
                track.set_artist(sAttrValue);
            } else if (sAttrName.equalsIgnoreCase("NAME")) {
                track.set_name(sAttrValue);
            } else {
                logger.info("unknow Track property " + sAttrName + ":" + sAttrValue);
            }

            logger.debug("set Track property " + sAttrName + ":" + sAttrValue);
        }

        logger.debug("End");
        return track;
    }
}
