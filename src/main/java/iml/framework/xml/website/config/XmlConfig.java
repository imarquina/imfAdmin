package iml.framework.xml.website.config;

import iml.framework.xml.website.Property;
import iml.framework.xml.website.config.structs.*;
import iml.framework.xml.website.feed.XmlFeed;
import iml.framework.xml.website.sitemap.XmlSitemap;
import org.apache.log4j.Logger;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPathExpressionException;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

import static iml.framework.arq.utils.Text.SetLength;
import static iml.framework.arq.utils.Xml.innerXml;
import static iml.framework.arq.utils.Xml.normalize;

/**
 * Created by inaki.marquina on 06/07/2016.
 */
public class XmlConfig {
    private String _nameXml = "config";
    private String _xml;
    public Config config;

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

//    private Date lastElementUpdate;

    /**
     * ENUMERATORS
     */
    public enum writeMode
    {
        minified,
        unminified,
        both
    }

    private enum execMode
    {
        minified,
        unminified
    }

    /**
     * CONSTRUCTORS
     */
    public XmlConfig() {
        this._xml = "";
        config = new Config();
    }

    public XmlConfig(String xml) throws SAXException, ParserConfigurationException,
            XPathExpressionException, IOException, ParseException {
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

/*    public Date getLastElementUpdate() {
        return lastElementUpdate;
    }

    private void setLastElementUpdate(Date lastElementUpdate) {
        if (this.lastElementUpdate == null)
            this.lastElementUpdate = lastElementUpdate;
        else if (this.lastElementUpdate.before(lastElementUpdate))
            this.lastElementUpdate = lastElementUpdate;
    }*/

    /**
     * PRIVATE METHODS
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
        Node root = document.getDocumentElement();
        logger.info("root Node: " + root.getNodeName());

        this.getAttrConfig(root);

        if (root.hasChildNodes())
            openChildNodes(root.getChildNodes(), this.config);

        logger.debug("End");
    }

    /**
     *
     * @param nList
     * @param object
     * @throws ParseException
     */
    private void openChildNodes(NodeList nList, Object object) throws ParseException {
        logger.debug("Begin");

        for (int temp = 0; temp < nList.getLength(); temp++)
        {
            Node node = nList.item(temp);
            if (node.getNodeType() == Node.ELEMENT_NODE)
            {
                String nodeName =  node.getNodeName();
                if (!nodeName.equals(null)) nodeName = nodeName.trim();

                logger.info("Node Name = " + nodeName + "; Value = " + SetLength(innerXml(node, true), 60));

                if (object instanceof Config ) {
                    if (nodeName.equalsIgnoreCase(NODO_GALLERIES)) {
                        Galleries galleries = (new Galleries()).fromXml(node);
                        this.config.addGalleries(galleries);
                        if (node.hasChildNodes())
                            openChildNodes(node.getChildNodes(), galleries);
                    } else if (nodeName.equalsIgnoreCase(NODO_FOLDER)) {
                        Folder folder = (new Folder()).fromXml(node);
                        this.config.addFolder(folder);
                        if (node.hasChildNodes())
                            openChildNodes(node.getChildNodes(), folder);
                    } else if (nodeName.equalsIgnoreCase(NODO_SECTION)) {
                        Section section = (new Section()).fromXml(node);
                        this.config.addSection(section);
                    } else if (nodeName.equalsIgnoreCase(NODO_TITLE)) {
                        Title title = (new Title()).fromXml(node);
                        this.config.addTitle(title);
                    } else if (nodeName.equalsIgnoreCase(NODO_SLOGAN)) {
                        Slogan slogan = (new Slogan()).fromXml(node);
                        this.config.addSlogan(slogan);
                    } else if (nodeName.equalsIgnoreCase(NODO_TRACKS)) {
                        Tracks tracks = (new Tracks()).fromXml(node);
                        this.config.addTracks(tracks);
                        if (node.hasChildNodes())
                            openChildNodes(node.getChildNodes(), tracks);
                    } else if (nodeName.equalsIgnoreCase(NODO_CONTACTFORM)) {
                        ContactForm contactForm = (new ContactForm()).fromXml(node);
                        this.config.addContactForm(contactForm);
                    } else {
                        logger.info("unknow Node of ConfigNode: " + nodeName);
                    }
                } else if (object instanceof Galleries) {
                    Galleries galleries = (Galleries)object;

                    if (nodeName.equalsIgnoreCase(NODO_FOLDER)) {
                        Folder folder = (new Folder()).fromXml(node);
                        galleries.addFolder(folder);
                        if (node.hasChildNodes())
                            openChildNodes(node.getChildNodes(), folder);
                    } else if (nodeName.equalsIgnoreCase(NODO_GALLERY)) {
                        Gallery gallery = (new Gallery()).fromXml(node);
                        galleries.addGallery(gallery);
                        if (node.hasChildNodes())
                            openChildNodes(node.getChildNodes(), gallery);
                    } else if (nodeName.equalsIgnoreCase(NODO_MULTIMEDIA)) {
                        Multimedia multimedia = (new Multimedia()).fromXml(node);
                        galleries.addMultimedia(multimedia);
                        if (node.hasChildNodes())
                            openChildNodes(node.getChildNodes(), multimedia);
                    } else {
                        logger.info("unknow Node of Galleries: " + nodeName);
                    }
                } else if (object instanceof Gallery) {
                    Gallery gallery = (Gallery)object;

                    if (nodeName.equalsIgnoreCase(NODO_IMAGEN)) {
                        Image image = (new Image()).fromXml(node);
                        gallery.addImage(image);
                    } else {
                        logger.info("unknow Node of Gallery: " + nodeName);
                    }
                } else if (object instanceof Folder) {
                    Folder folder = (Folder)object;

                    if (nodeName.equalsIgnoreCase(NODO_GALLERY)) {
                        Gallery gallery = (new Gallery()).fromXml(node);
                        folder.addGallery(gallery);
                        if (node.hasChildNodes())
                            openChildNodes(node.getChildNodes(), gallery);
                    } else if (nodeName.equalsIgnoreCase(NODO_SECTION)) {
                        Section section = (new Section()).fromXml(node);
                        folder.addSection(section);
                    } else {
                        logger.info("unknow Node of Folder: " + nodeName);
                    }
                } else if (object instanceof Multimedia) {
                    Multimedia multimedia = (Multimedia)object;

                    if (nodeName.equalsIgnoreCase(NODO_VIDEO)) {
                        Video video = (Video)(new Video()).fromXml(node);
                        multimedia.addVideo(video);
                    } else {
                        logger.info("unknow Node of Multimedia: " + nodeName);
                    }
                } else if (object instanceof Tracks) {
                    Tracks tracks = (Tracks)object;

                    if (nodeName.equalsIgnoreCase(NODO_TRACK)) {
                        Track track = (new Track()).fromXml(node);
                        tracks.addTrack(track);
                    }
                } else {
                    logger.info("unknow Object instanceof: " + nodeName);
                }
            }
        }
        logger.debug("End");
    }

    /**
     *
     * @param node
     */
    private void getAttrConfig(Node node) {
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
                this.config.set_title(sAttrValue);
            } else if (sAttrName.equalsIgnoreCase(ATTRIBUTE_INFOTEXT)) {
                this.config.set_infoText(sAttrValue);
            } else if (sAttrName.equalsIgnoreCase(ATTRIBUTE_KEYWORDS)) {
                this.config.set_keywords(sAttrValue);
            } else {
                logger.info("unknow ConfigNode property " + sAttrName + ":" + sAttrValue);
            }

            logger.debug("set ConfigNode property " + sAttrName + ":" + sAttrValue);
        }

        logger.debug("End");
    }

    /**
     *
     * @param elements
     * @param extractCollection
     * @param clazz
     * @return
     */
    private ExtractCollection extractCollections(Map<String, Object> elements,
                                                 ExtractCollection extractCollection, Class clazz){
        logger.debug("Begin");

        Integer iKey = 0;

        for (Map.Entry<String, Object> entry : elements.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            if (value instanceof Galleries) {
                Galleries galleries = (Galleries)value;

                extractCollections(galleries.elements, extractCollection, clazz);
            } else if (value instanceof Gallery){
                Gallery gallery = (Gallery)value;

                extractCollection.setLastElementUpdate(gallery.get_update());
                extractCollection.elements.put(gallery.get_id(), gallery);
            } else if (value instanceof Multimedia){
                Multimedia multimedia = (Multimedia)value;

                extractCollection.setLastElementUpdate(multimedia.get_update());
                extractCollection.elements.put(multimedia.get_id(), multimedia);
            } else if (value instanceof Folder) {
                Folder folder = (Folder)value;

                if (clazz == XmlSitemap.class){
                    extractCollection.setLastElementUpdate(folder.get_update());
                    extractCollection.elements.put(folder.get_id(), folder);
                } else if (clazz == XmlFeed.class){
                    extractCollections(folder.elements, extractCollection, clazz);
                }
            }
        }

        logger.debug("End");
        return  extractCollection;
    }


    /**
     *
     * @param type
     * @throws ParserConfigurationException
     * @throws TransformerException
     */
    private void setXml(execMode type) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        DOMImplementation implementation = builder.getDOMImplementation();

        Document document = implementation.createDocument(null, this.config.get_nodeName(), null);
        document.setXmlVersion("1.0");

        //Main Node
        Element configNode = document.getDocumentElement();
        this.config.toXml(document, configNode);

        //Generate XML
        Source source = new DOMSource(document);

        this.execXml(source,type);
    }

    /**
     *
     * @param source
     * @param type
     * @throws TransformerException
     */
    private void execXml(Source source, execMode type) throws TransformerException {
        //Indicamos donde lo queremos almacenar
        String nameFile = "";
        if (type == execMode.minified)
            nameFile = Property.readProperty("iml.xml.dir.out") + this._nameXml + ".min.xml";
        else
            nameFile = Property.readProperty("iml.xml.dir.out") + this._nameXml + ".xml";

        Result result = new StreamResult(new File(nameFile)); //nombre del archivo

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.ENCODING,"utf-8");
        transformer.setOutputProperty(OutputKeys.VERSION,"1.0");
        transformer.setOutputProperty(OutputKeys.INDENT,(type==execMode.unminified)?"yes":"no");
        transformer.setOutputProperty(OutputKeys.STANDALONE,"yes");
        transformer.transform(source, result);
    }

    /**
     *
     * @param elements
     * @return
     */
    public ExtractCollection getCollections(Map<String, Object> elements, Class clazz){
        ExtractCollection elementCollection = new ExtractCollection();

        return this.extractCollections(elements, elementCollection, clazz);
    }

    /**
     *
     * @throws ParserConfigurationException
     * @throws TransformerException
     */
    public void writeXml(writeMode type) throws TransformerException, ParserConfigurationException {
        logger.debug("Begin");

        if(type == writeMode.both){
            this.setXml(execMode.minified);
            this.setXml(execMode.unminified);
        } else if (type == writeMode.minified) {
            this.setXml(execMode.minified);
        } else if (type == writeMode.unminified) {
            this.setXml(execMode.unminified);
        }

        logger.debug("End");
    }
}
