package iml.imfotografia.xml.sitemap;

import iml.imfotografia.xml.Propertyx;
import iml.imfotografia.arq.utils.Crypto;
import iml.imfotografia.arq.utils.Text;
import iml.imfotografia.xml.config.XmlConfig;
import iml.imfotografia.xml.config.base.CollectionBase;
import iml.imfotografia.xml.config.structs.Gallery;
import iml.imfotografia.xml.config.structs.Image;
import iml.imfotografia.xml.config.structs.Video;
import iml.imfotografia.xml.sitemap.element.Url;
import iml.imfotografia.xml.sitemap.element.Urlset;
import org.apache.log4j.Logger;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
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
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by imarquina on 29/6/16.
 */
public class XmlSitemap {
    private String _xmlConfig;
    private String _nameXml = "sitemap";
    public Urlset urlset = new Urlset();

    final static Logger logger = Logger.getLogger(XmlSitemap.class);

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
    public XmlSitemap() {
        this._xmlConfig = "";
        urlset = new Urlset();
    }

    public XmlSitemap(String xmlConfig) throws ParserConfigurationException, ParseException, SAXException, XPathExpressionException, IOException {
        this ();
        this.set_xmlConfig(xmlConfig);

        generateXml();
    }

    /**
     * GETTER / SETTER
     */
    public String get_xmlConfig() {
        return this._xmlConfig;
    }

    public void set_xmlConfig(String xmlConfig) {
        this._xmlConfig = xmlConfig;
    }

    /**
     * PUBLIC METHODS
     */
    public void writeXml() throws ParserConfigurationException, TransformerException {
        logger.debug("Begin");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        DOMImplementation implementation = builder.getDOMImplementation();

        Document document = implementation.createDocument(Propertyx.readProperty("iml.sitemap.xmlns"),
                this.urlset.get_nodeName(), null);
        document.setXmlVersion("1.0");

        this.urlset.toXml(document);

        //Generate XML
        Source source = new DOMSource(document);

        //Indicamos donde lo queremos almacenar
        Result result = new StreamResult(new File(Propertyx.readProperty("iml.xml.dir.out") +
                this._nameXml + ".xml")); //nombre del archivo

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.ENCODING,"utf-8");
        transformer.setOutputProperty(OutputKeys.VERSION,"1.0");
        transformer.setOutputProperty(OutputKeys.INDENT,"yes");
        transformer.setOutputProperty(OutputKeys.STANDALONE,"yes");
        transformer.transform(source, result);

        logger.debug("End");
    }

    /**
     * PRIVATE METHODS
     */
    /**
     *
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     * @throws XPathExpressionException
     * @throws ParseException
     */
    private void generateXml() throws SAXException, ParserConfigurationException, ParseException, XPathExpressionException, IOException {
        logger.debug("Begin");

        this.urlset.set_xmlns(Propertyx.readProperty("iml.sitemap.xmlns"));
        this.urlset.setXmlns_xsi(Propertyx.readProperty("iml.sitemap.xmlns_xsi"));
        this.urlset.setXsi_schemaLocation(Propertyx.readProperty("iml.sitemap.xsi_schemaLocation"));

        //Leer los datos de estructura
        XmlConfig xmlConfig = new XmlConfig(get_xmlConfig());

        //Estraer galerias e imágenes para completar información de item
        ArrayList<CollectionBase> elementCollection = xmlConfig.getCollections(xmlConfig.config.elements);

        String sLoc = Propertyx.readProperty("iml.url.root") +
                Propertyx.readProperty("iml.feed.item.link");

        //Recorrer los item para procesado
        for (CollectionBase c : elementCollection){
            Url uGal = new Url(sLoc + Crypto.getMD5(Text.htmlReplace(c.get_name())),
                    c.get_update());
            this.urlset.addUrl(uGal);

            for (Map.Entry<String, Object> entry : c.elements.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();

                Integer iImgIndex = -1;
                if (value instanceof Image){
                    iImgIndex = c.getIndexKey(((Image)value).get_id());
                } else if (value instanceof Video){
                    iImgIndex = c.getIndexKey(((Video)value).get_id());
                }

                Url uImg = new Url(sLoc + Crypto.getMD5(Text.htmlReplace(c.get_name())) +
                        "&amp;photo=" + iImgIndex,
                        c.get_update());
                this.urlset.addUrl(uImg);
            }
        }

        logger.debug("End");
    }
}

