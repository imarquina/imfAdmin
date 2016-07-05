package iml.imfotografia.xml.data;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import iml.imfotografia.xml.data.collection.Photos;
import iml.imfotografia.xml.data.collection.Medias;
import iml.imfotografia.xml.data.element.Photo;
import iml.imfotografia.xml.data.element.Media;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import org.apache.log4j.Logger;


/**
 * Created by imarquina on 29/6/16.
 */
public class XmlPhotos {
    private String _xml;
    public Photos images = new Photos();
    public Medias medias = new Medias();

    final static Logger logger = Logger.getLogger(XmlPhotos.class);

    /**
     * ENUMERATORS
     */
    private enum nodeType
    {
        imagen,
        media
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
    }

    public XmlPhotos(String xml) throws IOException, SAXException, ParserConfigurationException, ParseException {
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
        logger.debug("parseXml::Begin");

        //Get Document Builder
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        //Build Document
        Document document = builder.parse(new File(get_xml()));

        //Normalize the XML Structure; It's just too important !!
        document.getDocumentElement().normalize();

        //Here comes the root node
        Element root = document.getDocumentElement();
        logger.info("parseXml::" + "root Node: " + root.getNodeName());

        //Get all photos
        NodeList nPhotos = document.getElementsByTagName(NODO_COLLECTION_IMAGEN);
        openChildNodes(nPhotos);
        NodeList nVideos = document.getElementsByTagName(NODO_COLLECTION_VIDEO);
        openChildNodes(nVideos);

        logger.debug("parseXml::End");
    }

    /**
     * Parsea los nodos hijos de uno dado para incluirlos como objetos de tipo
     * imagen o vídeo
     * @param nList
     * @throws ParseException
     */
    private void openChildNodes(NodeList nList) throws ParseException {
        logger.debug("openChildNodes:Start");

        for (int temp = 0; temp < nList.getLength(); temp++)
        {
            Node node = nList.item(temp);
            if (node.getNodeType() == Node.ELEMENT_NODE)
            {
                String nodeName =  node.getNodeName();
                if (!nodeName.equals(null)) nodeName = nodeName.trim();
                String nodeValue = node.getNodeValue();
                if (!(nodeValue == null)) nodeValue = nodeValue.trim();

                logger.debug("openChildNodes::" + "Node Name = " + nodeName + "; Value = " +  nodeValue);

                //Check all attributes
                if (node.hasAttributes()) {
                    if (nodeName == NODO_IMAGEN) {
                        Photo photo = getAttrPhoto(node, nodeType.imagen);
                        this.images.photo.put(photo.get_id(), photo);
                    }
                    else if (nodeName == NODO_VIDEO) {
                        Media video = getAttrMedia(node, nodeType.media);
                        this.medias.video.put(video.get_id(), video);
                    }
                }
                if (node.hasChildNodes()) {
                    openChildNodes(node.getChildNodes());
                }
            }
        }
        logger.debug("openChildNodes:End");
    }

    /**
     * Parsea el nodo para obtener un objeto de tipo imagen
     * @param node
     * @param type
     * @return
     * @throws ParseException
     */
    private Photo getAttrPhoto(Node node, nodeType type) throws ParseException {
        logger.debug("getAttrPhoto::Start");
        Photo photo = new Photo();

        // get attributes names and values
        NamedNodeMap nodeMap = node.getAttributes();
        for (int i = 0; i < nodeMap.getLength(); i++) {
            Node tempNode = nodeMap.item(i);
            String sAttrName = tempNode.getNodeName();
            if (!sAttrName.equals(null)) sAttrName = sAttrName.trim().toUpperCase();
            String sAttrValue = tempNode.getNodeValue();
            if (!sAttrValue.equals(null)) sAttrValue = sAttrValue.trim();

            logger.debug("getAttrPhoto:: " + "    Attr name : " + sAttrName + "; Value = " + sAttrValue);

            if (sAttrName == "WIDTH") {
                photo.set_width(sAttrValue);
            } else if (sAttrName == "HEIGHT") {
                photo.set_height(sAttrValue);
            } else if (sAttrName == "CAPTION") {
                photo.set_caption(sAttrValue);
            } else if (sAttrName == "SRC") {
                photo.set_id(sAttrValue);
                photo.set_src(sAttrValue);
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
                logger.info("getAttrPhoto::unknow Photo property " + sAttrName + ":" + sAttrValue);
            }
            logger.debug("getAttrPhoto::set Photo property " + sAttrName + ":" + sAttrValue);
        }
        logger.debug("getAttrPhoto::End");
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
        logger.debug("getAttrMedia::Start");
        Media video = new Media();

        // get attributes names and values
        NamedNodeMap nodeMap = node.getAttributes();
        for (int i = 0; i < nodeMap.getLength(); i++) {
            Node tempNode = nodeMap.item(i);
            String sAttrName = tempNode.getNodeName();
            if (!sAttrName.equals(null)) sAttrName = sAttrName.trim().toUpperCase();
            String sAttrValue = tempNode.getNodeValue();
            if (!sAttrValue.equals(null)) sAttrValue = sAttrValue.trim();

            logger.debug("getAttrMedia:: " + "    Attr name : " + sAttrName + "; Value = " + sAttrValue);

            if (sAttrName == "WIDTH") {
                video.set_width(sAttrValue);
            } else if (sAttrName == "HEIGHT") {
                video.set_height(sAttrValue);
            } else if (sAttrName == "CAPTION") {
                video.set_caption(sAttrValue);
            } else if (sAttrName == "SRC") {
                video.set_id(sAttrValue);
                video.set_src(sAttrValue);
            } else if (sAttrName == "LINKTEXT") {
                video.set_linkText(sAttrValue);
            } else if (sAttrName == "LINKURL") {
                video.set_linkUrl(sAttrValue);
            } else if (sAttrName == "INFOTEXT") {
                video.set_infoText(sAttrValue);
            } else if (sAttrName == "FORMAT") {
                video.set_format(sAttrValue);
            } else if (sAttrName == "UPDATE") {
                video.set_update(sAttrValue);
            } else if (sAttrName == "PUBLIC") {
                video.set_dPublic(sAttrValue);
            } else {
                logger.info("getAttrMedia::unknow Photo property " + sAttrName + ":" + sAttrValue);
            }

            logger.debug("getAttrMedia::set Photo property " + sAttrName + ":" + sAttrValue);
        }
        logger.debug("getAttrMedia::End");
        return video;
    }
}