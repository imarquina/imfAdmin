package iml.framework.xml.website.sitemap;

import iml.framework.arq.utils.Crypto;
import iml.framework.arq.utils.Text;
import iml.framework.xml.website.Property;
import iml.framework.xml.website.config.XmlConfig;
import iml.framework.xml.website.config.base.ElementBase;
import iml.framework.xml.website.config.structs.*;
import iml.framework.xml.website.element.XmlPhotos;
import iml.framework.xml.website.sitemap.element.Url;
import iml.framework.xml.website.sitemap.element.Urlset;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
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
import java.util.Date;
import java.util.Map;

/**
 * Created by imarquina on 29/6/16.
 */
public class XmlSitemap {
    private String _xmlElement;
    private String _xmlConfig;
    private String _nameXml = "sitemap";
    public Urlset urlset = new Urlset();

    final static Logger logger = LogManager.getLogger(XmlSitemap.class);

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
        this._xmlElement = "";
        urlset = new Urlset();
    }

    public XmlSitemap(String xmlConfig, String xmlElement) throws ParserConfigurationException, ParseException, SAXException, XPathExpressionException, IOException {
        this ();
        this.set_xmlElement(xmlElement);
        this.set_xmlConfig(xmlConfig);

        generateXml();
    }

    /**
     * GETTER / SETTER
     */
    public String get_xmlElement() {
        return this._xmlElement;
    }

    public void set_xmlElement(String xmlElement) {
        this._xmlElement = xmlElement;
    }

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

        Document document = implementation.createDocument(Property.readProperty("iml.sitemap.xmlns"),
                this.urlset.get_nodeName(), null);
        document.setXmlVersion("1.0");

        this.urlset.toXml(document);

        //Generate XML
        Source source = new DOMSource(document);

        //Indicamos donde lo queremos almacenar
        Result result = new StreamResult(new File(Property.readProperty("iml.xml.dir.out") +
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

        this.urlset.set_xmlns(Property.readProperty("iml.sitemap.xmlns"));
        this.urlset.setXmlns_xsi(Property.readProperty("iml.sitemap.xmlns_xsi"));
        this.urlset.setXsi_schemaLocation(Property.readProperty("iml.sitemap.xsi_schemaLocation"));

        //Leer los datos de estructura
        XmlPhotos xmlElment = new XmlPhotos(get_xmlElement());
        XmlConfig xmlConfig = new XmlConfig(get_xmlConfig());

        //Estraer galerias e imágenes para completar información de item
        ExtractCollection extractCollection = xmlConfig.getCollections(xmlConfig.config.elements, XmlSitemap.class);

        Url uRot = new Url(Property.readProperty("iml.url.root"), extractCollection.getLastElementUpdate());
        this.urlset.addUrl(uRot);

        this.addUrlElements(extractCollection.elements, xmlElment);

        logger.debug("End");
    }

    /**
     *
     * @param collection
     */
    private void addUrlElements(Map<String, Object> collection, XmlPhotos xmlElment){
        String sLoc = Property.readProperty("iml.url.root");

        //Recorrer los item para procesado
        for (Map.Entry<String, Object> col : collection.entrySet()){
            String colKey = col.getKey();
            Object colValue = col.getValue();

            if (colValue instanceof Gallery){
                Gallery gal = (Gallery)colValue;

                //Se añade línea por la galería
                Url uGal = new Url(sLoc + Property.readProperty("iml.sitemap.gallery.link") +
                        Crypto.getMD5(Text.htmlReplace(gal.get_name())),
                        gal.get_update());
                this.urlset.addUrl(uGal);

                for (Map.Entry<String, Object> entry : gal.elements.entrySet()) {
                    String iteKey = entry.getKey();
                    Object iteValue = entry.getValue();

                    String url = "";
                    String suf = "";
                    Date update = null;

                    Integer iImgIndex = -1;

                    if (iteValue instanceof Image || iteValue instanceof Video) {
                        ElementBase ele = (ElementBase)iteValue;

                        url = sLoc + Property.readProperty("iml.sitemap.item.link") +
                                Crypto.getMD5(Text.htmlReplace(gal.get_name()));

                        if (iteValue instanceof Image) {
                            iImgIndex = gal.getIndexKey(((Image) iteValue).get_id());
                            if (xmlElment.config.images.photo.containsKey(ele.get_id())){
                                update = xmlElment.config.images.photo.get(ele.get_id()).get_update();
                            }
                        } else if (iteValue instanceof Video) {
                            iImgIndex = gal.getIndexKey(((Video) iteValue).get_id());
                            if (xmlElment.config.medias.media.containsKey(ele.get_id())){
                                update = xmlElment.config.medias.media.get(ele.get_id()).get_update();
                            }
                        }
                        suf = "&amp;photo=" + iImgIndex;
                    }

                    //Se añade línea por cada imagen o vídeo
                    Url uElm = new Url(url + suf, update);
                    this.urlset.addUrl(uElm);
                }
            }else if (colValue instanceof Folder){
                Folder folder = (Folder)colValue;

                //Se añade línea por la galería
                Url uFolder = new Url(sLoc + Property.readProperty("iml.sitemap.folder.link") +
                        Crypto.getMD5(Text.htmlReplace(folder.get_name())),
                        folder.get_update());
                this.urlset.addUrl(uFolder);

                addUrlElements(folder.elements, xmlElment);
            }
        }
    }
}

