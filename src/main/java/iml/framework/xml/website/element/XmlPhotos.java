package iml.framework.xml.website.element;

import iml.framework.arq.utils.Date;
import iml.framework.xml.website.Property;
import iml.framework.xml.website.element.interfaces.IElement;
import iml.framework.xml.website.element.structs.Config;
import iml.framework.xml.website.element.structs.Media;
import iml.framework.xml.website.element.structs.Photo;
import org.apache.log4j.Logger;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * Created by imarquina on 29/6/16.
 */
public class XmlPhotos {
    private String _nameXml = "photos";
    private String _xml;
    public Config config;

    final static Logger logger = Logger.getLogger(XmlPhotos.class);

    /**
     * ENUMERATORS
     */
    private enum nodeType
    {
        imagen,
        media
    }

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
     * CONSTANTS
     */
    private static final String NODO_COLLECTION_IMAGEN = "photos";
    private static final String NODO_COLLECTION_VIDEO = "videos";
    private static final String NODO_IMAGEN = "img";
    private static final String NODO_VIDEO = "vid";

    /**
     * CONSTRUCTORS
     */
    public XmlPhotos() {
        this._xml = "";
        config = new Config();
    }

    public XmlPhotos(String xml) throws IOException, SAXException, ParserConfigurationException,
            ParseException {
        this();
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
     * PRIVATE METHODS
     */
    /**
     * Parsea el xml para convertirlo en un objeto
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     * @throws ParseException
     */
    private void parseXml() throws ParserConfigurationException, SAXException, IOException, ParseException {
        logger.debug("Begin");

        //Get Document Builder
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        //Build Document
        Document document = builder.parse(new File(get_xml()));

        //Normalize the XML Structure; It's just too important !!
        document.getDocumentElement().normalize();

        //Here comes the root node
        Element root = document.getDocumentElement();
        logger.info("root Node: " + root.getNodeName());

        //Get all photos
        NodeList nPhotos = document.getElementsByTagName(NODO_COLLECTION_IMAGEN);
        openChildNodes(nPhotos);
        NodeList nVideos = document.getElementsByTagName(NODO_COLLECTION_VIDEO);
        openChildNodes(nVideos);

        logger.debug("End");
    }

    /**
     * Parsea los nodos hijos de uno dado para incluirlos como objetos de tipo
     * imagen o vídeo
     * @param nList
     * @throws ParseException
     */
    private void openChildNodes(NodeList nList) throws ParseException {
        logger.debug("Begin");

        for (int temp = 0; temp < nList.getLength(); temp++)
        {
            Node node = nList.item(temp);
            if (node.getNodeType() == Node.ELEMENT_NODE)
            {
                String nodeName =  node.getNodeName();
                if (!nodeName.equals(null)) nodeName = nodeName.trim();
                String nodeValue = node.getNodeValue();
                if (!(nodeValue == null)) nodeValue = nodeValue.trim();

                logger.info("Node Name = " + nodeName + "; Value = " +  nodeValue);

                //Check all attributes
                if (node.hasAttributes()) {
                    if (nodeName == NODO_IMAGEN) {
                        Photo photo = getAttrPhoto(node, nodeType.imagen);
                        this.config.images.addPhoto(photo.get_id(), photo);
                    }
                    else if (nodeName == NODO_VIDEO) {
                        Media media = getAttrMedia(node, nodeType.media);
                        this.config.medias.addMedia(media.get_id(), media);
                    }
                }
                if (node.hasChildNodes()) {
                    openChildNodes(node.getChildNodes());
                }
            }
        }
        logger.debug("End");
    }

    /**
     * Parsea el nodo para obtener un objeto de tipo imagen
     * @param node
     * @param type
     * @return
     * @throws ParseException
     */
    private Photo getAttrPhoto(Node node, nodeType type) throws ParseException {
        logger.debug("Begin");
        Photo photo = new Photo();

        // get attributes names and values
        NamedNodeMap nodeMap = node.getAttributes();
        for (int i = 0; i < nodeMap.getLength(); i++) {
            Node tempNode = nodeMap.item(i);
            String sAttrName = tempNode.getNodeName();
            if (!sAttrName.equals(null)) sAttrName = sAttrName.trim().toUpperCase();
            String sAttrValue = tempNode.getNodeValue();
            if (!sAttrValue.equals(null)) sAttrValue = sAttrValue.trim();

            logger.info("    Attr name : " + sAttrName + "; Value = " + sAttrValue);

            if (sAttrName == "WIDTH") {
                photo.set_width(sAttrValue);
            } else if (sAttrName == "HEIGHT") {
                photo.set_height(sAttrValue);
            } else if (sAttrName == "CAPTION") {
                photo.set_caption(sAttrValue);
            } else if (sAttrName == "SRC") {
                photo.set_id(sAttrValue);
                photo.set_src(sAttrValue);
            } else if (sAttrName == "DX") {
                photo.set_dx(sAttrValue);
            } else if (sAttrName == "DY") {
                photo.set_dy(sAttrValue);
            } else if (sAttrName == "LINKTEXT") {
                photo.set_linkText(sAttrValue);
            } else if (sAttrName == "LINKURL") {
                photo.set_linkUrl(sAttrValue);
            } else if (sAttrName == "INFOTEXT") {
                photo.set_infoText(sAttrValue);
            } else if (sAttrName == "FORMAT") {
                photo.set_format(sAttrValue);
            } else if (sAttrName == "STOCK") {
                photo.set_stock(sAttrValue);
            } else if (sAttrName == "PRICE") {
                photo.set_price(sAttrValue);
            } else if (sAttrName == "UPDATE") {
                photo.set_update(sAttrValue);
            } else if (sAttrName == "PUBLIC") {
                photo.set_dPublic(sAttrValue);
            } else {
                logger.info("unknow Photo property " + sAttrName + ":" + sAttrValue);
            }
            logger.debug("set Photo property " + sAttrName + ":" + sAttrValue);
        }
        logger.debug("End");
        return photo;
    }

    /**
     * Parsea el nodo para obtener un objeto de tipo vídeo
     * @param node
     * @param type
     * @return
     * @throws ParseException
     */
    private Media getAttrMedia(Node node, nodeType type) throws ParseException {
        logger.debug("Begin");
        Media video = new Media();

        // get attributes names and values
        NamedNodeMap nodeMap = node.getAttributes();
        for (int i = 0; i < nodeMap.getLength(); i++) {
            Node tempNode = nodeMap.item(i);
            String sAttrName = tempNode.getNodeName();
            if (!sAttrName.equals(null)) sAttrName = sAttrName.trim().toUpperCase();
            String sAttrValue = tempNode.getNodeValue();
            if (!sAttrValue.equals(null)) sAttrValue = sAttrValue.trim();

            logger.info("    Attr name : " + sAttrName + "; Value = " + sAttrValue);

            if (sAttrName == "WIDTH") {
                video.set_width(sAttrValue);
            } else if (sAttrName == "HEIGHT") {
                video.set_height(sAttrValue);
            } else if (sAttrName == "CAPTION") {
                video.set_caption(sAttrValue);
            } else if (sAttrName == "SRC") {
                video.set_id(sAttrValue);
                video.set_src(sAttrValue);
            } else if (sAttrName == "DX") {
                video.set_dx(sAttrValue);
            } else if (sAttrName == "DY") {
                video.set_dy(sAttrValue);
            } else if (sAttrName == "LINKTEXT") {
                video.set_linkText(sAttrValue);
            } else if (sAttrName == "LINKURL") {
                video.set_linkUrl(sAttrValue);
            } else if (sAttrName == "INFOTEXT") {
                video.set_infoText(sAttrValue);
            } else if (sAttrName == "FORMAT") {
                video.set_format(sAttrValue);
            } else if (sAttrName == "STOCK") {
                video.set_stock(sAttrValue);
            } else if (sAttrName == "PRICE") {
                video.set_price(sAttrValue);
            } else if (sAttrName == "UPDATE") {
                video.set_update(sAttrValue);
            } else if (sAttrName == "PUBLIC") {
                video.set_dPublic(sAttrValue);
            } else {
                logger.info("unknow Photo property " + sAttrName + ":" + sAttrValue);
            }

            logger.debug("set Photo property " + sAttrName + ":" + sAttrValue);
        }
        logger.debug("End");
        return video;
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

        String nameFile;
        if (type == execMode.minified)
            nameFile = Property.readProperty("iml.xml.dir.out") + this._nameXml + ".min.xml";
        else
            nameFile = Property.readProperty("iml.xml.dir.out") + this._nameXml + ".xml";

        //Indicamos donde lo queremos almacenar
        Result result = new StreamResult(new File(nameFile)); //nombre del archivo

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.ENCODING,"utf-8");
        transformer.setOutputProperty(OutputKeys.VERSION,"1.0");
        transformer.setOutputProperty(OutputKeys.INDENT,(type== execMode.unminified)?"yes":"no");
        transformer.setOutputProperty(OutputKeys.STANDALONE,"yes");
        transformer.transform(source, result);
    }

    /**
     * PUBLIC METHODS
     */

    /**
     * Agrupa los elementos y los ordena por el campo update
     * @return
     */
    public List<IElement> getElementByUpdate(){
        List<IElement> list = new ArrayList<IElement>();
        list.addAll(this.config.images.photo.values());
        list.addAll(this.config.medias.media.values());

        //Ordenar los elementos de más a menos reciente
        Collections.sort(list, new Comparator<IElement>() {
            public int compare(IElement o1, IElement o2) {
                return (int) Date.DateDiff(o1.get_update(), o2.get_update());
            }
        });

        return list;
    }

    /**
     * Agrupa los elementos y los ordena por el campo public
     * @return
     */
    public List<IElement> getElementByPublic(){
        List<IElement> list = new ArrayList<IElement>();
        list.addAll(this.config.images.photo.values());
        list.addAll(this.config.medias.media.values());

        //Ordenar los elementos de más a menos reciente
        Collections.sort(list, new Comparator<IElement>() {
            public int compare(IElement o1, IElement o2) {
                return (int) Date.DateDiff(o1.get_dPublic(), o2.get_dPublic());
            }
        });

        return list;
    }

    /**
     *
     * @throws ParserConfigurationException
     * @throws TransformerException
     */
    public void writeXml(writeMode type) throws ParserConfigurationException, TransformerException {
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