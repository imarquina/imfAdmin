package iml.imfotografia.xml.feed;

import iml.imfotografia.xml.Propertyx;
import iml.imfotografia.xml.config.XmlConfig;
import iml.imfotografia.xml.config.base.CollectionBase;
import iml.imfotografia.xml.config.structs.ExtractCollection;
import iml.imfotografia.xml.element.XmlPhotos;
import iml.imfotografia.xml.element.interfaces.IElement;
import iml.imfotografia.xml.feed.struct.Channel;
import iml.imfotografia.xml.feed.struct.Image;
import iml.imfotografia.xml.feed.struct.Item;
import iml.imfotografia.xml.feed.struct.Rss;
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
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by imarquina on 29/6/16.
 */
public class XmlFeed {
    private String _xmlElement;
    private String _xmlConfig;
    private String _nameXml = "feed";
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
        this._xmlConfig = "";
        this._xmlElement = "";
        rss = new Rss();
    }

    public XmlFeed(String xmlConfig, String xmlElement) throws ParserConfigurationException, ParseException, SAXException, XPathExpressionException, IOException {
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

        Document document = implementation.createDocument(null, this.rss.get_nodeName(), null);
        document.setXmlVersion("1.0");

        this.rss.toXml(document);

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

        this.rss.set_version(Propertyx.readProperty("iml.feed.rss.version"));

        //Leer los datos de elementos y estructura
        XmlPhotos xmlElment = new XmlPhotos(get_xmlElement());
        XmlConfig xmlConfig = new XmlConfig(get_xmlConfig());

        //Agrupar los elementos
        List<IElement> list = xmlElment.getElementByUpdate();

        //Estraer galerias e imágenes para completar información de item
        //El método getCollections informa LastElementUpdate
        ExtractCollection extractCollection = xmlConfig.getCollections(xmlConfig.config.elements, XmlFeed.class);

        //Crear el xml
        Channel chanel = createChanel(xmlConfig, extractCollection.getLastElementUpdate());
        this.rss.addChanel(chanel);

        Image image = createImage();
        chanel.addImage(image);

        //Recorrer cada item para procesado
        for (IElement e : list) {
            //Buscar cada elemento en la galería / imagenes para saber cuantos item añadir
            for (Map.Entry<String, Object> entry : extractCollection.elements.entrySet()){
                String key = entry.getKey();
                CollectionBase value = (CollectionBase)entry.getValue();

                if (value.elements.containsKey(e.get_id())) {
                    //Bucle para añadir items por cada elemento si hay más de uno
                    Item item = createItem(e, value, value.getIndexKey(e.get_id()));
                    chanel.addItem(item);
                }
            }
        }

        logger.debug("End");
    }

    /**
     *
     * @param xmlConfig
     * @return
     * @throws ParseException
     */
    private Channel createChanel(XmlConfig xmlConfig, Date lastElementUpdate) throws ParseException {
        logger.debug("Begin");

        Channel chanel = new Channel(xmlConfig.config, lastElementUpdate);

        logger.debug("End");
        return chanel;
    }

    /**
     *
     * @return
     * @throws ParseException
     */
    private Image createImage() throws ParseException {
        logger.debug("Begin");

        Image image = new Image();

        logger.debug("End");
        return image;
    }

    /**
     *
     * @return
     */
    private Item createItem(IElement element, CollectionBase collection, Integer iImage) {
        logger.debug("Begin");

        Item item = new Item(element, collection, iImage);

        logger.debug("End");
        return item;
    }
}

