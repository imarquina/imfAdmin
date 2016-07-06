package iml.imfotografia.xml.feed;

import iml.imfotografia.xml.config.prueba.Config;
import iml.imfotografia.xml.config.structs.*;
import iml.imfotografia.xml.feed.struct.*;
import iml.imfotografia.xml.feed.struct.Title;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.print.Doc;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import static iml.imfotografia.utils.Xml.normalize;

/**
 * Created by imarquina on 29/6/16.
 */
public class XmlFeed {
    private String _xml;
    public Rss rss;

    final static Logger logger = Logger.getLogger(XmlFeed.class);

    /**
     * CONSTANTS
     */
    private static final String ATTRIBUTE_TITLE = "TITLE";
    private static final String ATTRIBUTE_LINK = "LINK";
    private static final String ATTRIBUTE_DESCRIPTION = "DESCRIPTION";
    private static final String ATTRIBUTE_LANGUAGE = "LANGUAGE";
    private static final String ATTRIBUTE_PUBDATE = "PUBDATE";
    private static final String ATTRIBUTE_LASTBUILDDATE = "LASTBUILDDATE";
    private static final String ATTRIBUTE_DOCS = "DOCS";
    private static final String ATTRIBUTE_MANAGINGEDITOR = "MANAGINGEDITOR";
    private static final String ATTRIBUTE_WEBMASTER = "WEBMASTER";

    /**
     * CONSTRUCTORS
     */
    public XmlFeed() {
        this._xml = "";
        rss = new Rss();
    }

    public XmlFeed(String xml) throws ParserConfigurationException, ParseException, SAXException, XPathExpressionException, IOException {
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
    public void generateXml(String xmlConfig, String xmlPhotos) {

    }

    public void writeXml() {

    }

    /**
     * PRIVATE METHODS
     */
    private void parseXml() throws ParserConfigurationException, IOException, SAXException, XPathExpressionException, ParseException {
        logger.debug("Begin");

        this.rss.set_version("2.0");

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

        Channel chanel = new Channel();

        Config config = new Config(root);

        Title title = getAttrTitle(config);
        chanel.addTitle(title);

        Link link = getAttrLink(root, ATTRIBUTE_LINK);
        chanel.addLink(link);

        Description description = getAttrDescription(root, ATTRIBUTE_DESCRIPTION);
        chanel.addDescription(description);

        Language language = getAttrLanguage(root, ATTRIBUTE_LANGUAGE);
        chanel.addLanguage(language);

        PubDate pubDate = getAttrPubDate(root, ATTRIBUTE_PUBDATE);
        chanel.addPubDate(pubDate);

        LastBuildDate lastBuildDate = getAttrLastBuildDate(root, ATTRIBUTE_LASTBUILDDATE);
        chanel.addLastBuildDate(lastBuildDate);

        Docs docs = getAttrDocs(root, ATTRIBUTE_DOCS);
        chanel.addDocs(docs);

        ManagingEditor managingEditor = getAttrManagingEditor(root, ATTRIBUTE_MANAGINGEDITOR);
        chanel.addManagingEditor(managingEditor);

        WebMaster webMaster = getAttrWebMaster(root, ATTRIBUTE_WEBMASTER);
        chanel.addWebMaster(webMaster);

        this.rss.addChanel(chanel);

        if (root.hasChildNodes())
            openChildNodes(root.getChildNodes(), this.rss);

        logger.debug("End");
    }

    private void openChildNodes(NodeList nList, Object object) throws ParseException {
        logger.debug("Start");

        logger.debug("End");
    }

    private Title getAttrTitle(Config config){
        Title title = new Title();
        title.set_content(config.get_title());

        return title;
    }
}

