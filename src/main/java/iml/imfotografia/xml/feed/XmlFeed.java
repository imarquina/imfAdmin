package iml.imfotografia.xml.feed;

import iml.imfotografia.xml.config.XmlConfig;
import iml.imfotografia.xml.feed.struct.Rss;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

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

    final static Logger logger = Logger.getLogger(XmlConfig.class);

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
            openChildNodes(root.getChildNodes(), this.rss);

        logger.debug("End");
    }

    private void openChildNodes(NodeList nList, Object object) throws ParseException {
        logger.debug("Start");

        logger.debug("End");
    }
}

