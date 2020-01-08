package iml.framework.xml.website.config.structs;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import java.util.LinkedHashMap;
import java.util.Map;

public class Galleries {
    private Integer _iKey;
    private String _nodeName = "";
    public Map<String, Object> elements;

    final static Logger logger = LogManager.getLogger(Galleries.class);

    /**
     * CONSTRUCTORES
     */
    public Galleries() {
        this._iKey = 0;
        this._nodeName = "galleries";

        this.elements = new LinkedHashMap<String, Object>();
    }

    /**
     * GETTER / SETTER
     */
    public String get_nodeName() {
        return this._nodeName;
    }

    /**
     *
     * @return
     */
    public void addGallery (Gallery gallery) {
        this.elements.put((_iKey++).toString(), gallery);
    }

    /**
     *
     * @return
     */
    public void addFolder(Folder folder){
        this.elements.put((_iKey++).toString(), folder);
    }

    /**
     *
     * @return
     */
    public void addMultimedia(Multimedia multimedia) {
        this.elements.put((_iKey++).toString(), multimedia);
    }

    /**
     *
     * @param node
     * @return
     */
    public Galleries fromXml(Node node) {
        logger.debug("Begin");
        Galleries galleries = new Galleries();

        // get attributes names and values
        NamedNodeMap nodeMap = node.getAttributes();
        for (int i = 0; i < nodeMap.getLength(); i++) {
            Node tempNode = nodeMap.item(i);
            String sAttrName = tempNode.getNodeName();
            if (!sAttrName.equals(null)) sAttrName = sAttrName.trim().toUpperCase();
            String sAttrValue = tempNode.getNodeValue();
            if (!sAttrValue.equals(null)) sAttrValue = sAttrValue.trim();

            logger.info("    Attr name : " + sAttrName + "; Value = " + sAttrValue);

            logger.debug("set Galleries property " + sAttrName + ":" + sAttrValue);
        }

        logger.debug("End");
        return galleries;
    }

    /**
     *
     * @param document
     * @param parentNode
     */
    public void toXml(Document document, Element parentNode){
        logger.debug("Begin");

        for (Map.Entry<String, Object> entry1 : this.elements.entrySet()) {
            String key = entry1.getKey();
            Object value = entry1.getValue();

            if (value instanceof  Gallery) {
                Gallery gallery = (Gallery)value;

                Element galleryNode = document.createElement(gallery.get_nodeName());
                gallery.toXml(document, galleryNode);

                parentNode.appendChild(galleryNode);
            } else if (value instanceof  Folder) {
                Folder folder = (Folder)value;

                Element sectionNode = document.createElement(folder.get_nodeName());
                folder.toXml(document, sectionNode);

                parentNode.appendChild(sectionNode);
            } else if (value instanceof Multimedia) {
                Multimedia multimedia = (Multimedia)value;

                Element multimediaNode = document.createElement(multimedia.get_nodeName());
                multimedia.toXml(document, multimediaNode);

                parentNode.appendChild(multimediaNode);
            }
        }

        logger.debug("End");
    }
}
