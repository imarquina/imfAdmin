package iml.imfotografia.xml.sitemap;

import iml.imfotografia.utils.Date;
import iml.imfotografia.utils.Property;
import iml.imfotografia.xml.config.XmlConfig;
import iml.imfotografia.xml.config.structs.Gallery;
import iml.imfotografia.xml.element.XmlPhotos;
import iml.imfotografia.xml.element.interfaces.IElement;
import iml.imfotografia.xml.feed.struct.Item;
import iml.imfotografia.xml.feed.struct.Title;
import iml.imfotografia.xml.sitemap.element.Url;
import iml.imfotografia.xml.sitemap.element.Urlset;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by imarquina on 29/6/16.
 */
public class XmlSitemap {
    private String _xmlElement;
    private String _xmlConfig;
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

    private Property properties;

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
    public void writeXml() {

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

        properties = new Property("config.properties");

        this.urlset.set_xmlns(properties.readProperty("iml.sitemap.xmlns"));
        this.urlset.setXmlns_xsi(properties.readProperty("iml.sitemap.xmlns_xsi"));
        this.urlset.setXsi_schemaLocation(properties.readProperty("iml.sitemap.xsi_schemaLocation"));

        //Leer los datos de elementos y estructura
        XmlPhotos xmlElment = new XmlPhotos(get_xmlElement());
        XmlConfig xmlConfig = new XmlConfig(get_xmlConfig());

        //Estraer galerias e imágenes para completar información de item
        ArrayList<Gallery> imageGalleries = xmlConfig.getGallerys(xmlConfig.config.elements);

        //Recorrer los item para procesado
        for (Gallery g : imageGalleries){
            Url url = new Url(g.get_name(), g.get_update());
            this.urlset.addUrl(url);
        }

        logger.debug("End");
    }
}

