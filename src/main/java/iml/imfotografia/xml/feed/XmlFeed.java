package iml.imfotografia.xml.feed;

import iml.imfotografia.utils.Date;
import iml.imfotografia.xml.config.XmlConfig;
import iml.imfotografia.xml.config.structs.Folder;
import iml.imfotografia.xml.config.structs.Galleries;
import iml.imfotografia.xml.config.structs.Gallery;
import iml.imfotografia.xml.element.XmlPhotos;
import iml.imfotografia.xml.element.interfaces.IElement;
import iml.imfotografia.xml.feed.struct.Channel;
import iml.imfotografia.xml.feed.struct.Image;
import iml.imfotografia.xml.feed.struct.Item;
import iml.imfotografia.xml.feed.struct.Rss;
import org.apache.log4j.Logger;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

/**
 * Created by imarquina on 29/6/16.
 */
public class XmlFeed {
    private String _xmlElement;
    private String _xmlConfig;
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

        this.rss.set_version("2.0");

        XmlPhotos xmlElment = new XmlPhotos(get_xmlElement());
        XmlConfig xmlConfig = new XmlConfig(get_xmlConfig());

        Channel chanel = createChanel(xmlConfig);
        this.rss.addChanel(chanel);

        Image image = createImage();
        chanel.addImage(image);

        List<IElement> list = new ArrayList<IElement>();
        list.addAll(xmlElment.images.photo.values());
        list.addAll(xmlElment.medias.media.values());

        Collections.sort(list, new Comparator<IElement>() {
            public int compare(IElement o1, IElement o2) {
                return (int) Date.DateDiff(o1.get_update(), o2.get_update());
            }
        });

        //Estraer galerias e imágenes para completar información de item
        Gallery gallery = extractElements(xmlConfig.config.elements);

        //Recorrer cada item para procesado
        for (IElement e : list) {
            //1.Buscar cada elemento en la galería / imagenes para saber cuantos item añadir

            //2.Bucle para añadir items por cada elemento si hay más de uno
                Item item = createItem(e, gallery);
                chanel.addItem(item);
        }

        logger.debug("End");
    }

    private void openChildNodes(NodeList nList, Object object) throws ParseException {
        logger.debug("Begin");

        logger.debug("End");
    }

    /**
     *
     * @param xmlConfig
     * @return
     * @throws ParseException
     */
    private Channel createChanel(XmlConfig xmlConfig) throws ParseException {
        logger.debug("Begin");

        Channel chanel = new Channel(xmlConfig.config);

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
    private Item createItem(IElement element, Gallery gallery) {
        logger.debug("Begin");

        Item item = new Item(element, gallery);

        logger.debug("End");
        return item;
    }

    private Map<Integer, Gallery> extractElements(Map<Integer, Object> elements){
        for (Map.Entry<Integer, Object> entry : elements.entrySet()) {
            Integer key = entry.getKey();
            Object value = entry.getValue();

            if (value instanceof Galleries) {
                extractElements((Map<Integer, Object>) value);
            } else if (value instanceof Gallery){
                Gallery gallery = (Gallery)entry;


            } else if (value instanceof Folder) {
                extractElements(((Folder) value).elements);
            }
        }
    }
}

