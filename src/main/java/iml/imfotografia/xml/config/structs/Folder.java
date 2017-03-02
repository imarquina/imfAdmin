package iml.imfotografia.xml.config.structs;

import iml.imfotografia.xml.config.base.CollectionBase;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import java.text.ParseException;
import java.util.Map;

/**
 * COLLECTIONS
 */
public class Folder extends CollectionBase {
    final static Logger logger = Logger.getLogger(Folder.class);

    /**
     * CONSTANTS
     */
    private static final String ATTRIBUTE_NAME = "name";
    private static final String ATTRIBUTE_SRC = "src";
    private static final String ATTRIBUTE_TITLE = "title";
    private static final String ATTRIBUTE_INFOTEXT = "infotext";
    private static final String ATTRIBUTE_KEYWORDS = "keywords";
    private static final String ATTRIBUTE_UPDATE = "update";

    /**
     * CONSTRUCTORS
     */
    public Folder() {
        super();
        this._nodeName = "folder";
    }

    /**
     *
     * @return
     */
    public void addGallery (Gallery gallery) {
        super.addCollection(gallery);
    }

    public void addSection (Section section) {
        super.addSection(section);
    }

    /**
     *
     * @param node
     * @return
     * @throws ParseException
     */
    public Folder fromXml(Node node) throws ParseException {
        logger.debug("Begin");
        Folder folder = new Folder();

        // get attributes names and values
        NamedNodeMap nodeMap = node.getAttributes();
        for (int i = 0; i < nodeMap.getLength(); i++) {
            Node tempNode = nodeMap.item(i);
            String sAttrName = tempNode.getNodeName();
            if (!sAttrName.equals(null)) sAttrName = sAttrName.trim().toUpperCase();
            String sAttrValue = tempNode.getNodeValue();
            if (!sAttrValue.equals(null)) sAttrValue = sAttrValue.trim();

            logger.info("    Attr name : " + sAttrName + "; Value = " + sAttrValue);

            if (sAttrName.equalsIgnoreCase(ATTRIBUTE_NAME)) {
                folder.set_name(sAttrValue);
            } else if (sAttrName.equalsIgnoreCase(ATTRIBUTE_SRC)) {
                folder.set_src(sAttrValue);
            } else if (sAttrName.equalsIgnoreCase(ATTRIBUTE_TITLE)) {
                folder.set_title(sAttrValue);
            } else if (sAttrName.equalsIgnoreCase(ATTRIBUTE_INFOTEXT)) {
                folder.set_infoText(sAttrValue);
            } else if (sAttrName.equalsIgnoreCase(ATTRIBUTE_KEYWORDS)) {
                folder.set_keywords(sAttrValue);
            } else if (sAttrName.equalsIgnoreCase(ATTRIBUTE_UPDATE)) {
                folder.set_update(sAttrValue);
            } else {
                logger.info("unknow Folder property " + sAttrName + ":" + sAttrValue);
            }

            logger.debug("set Folder property " + sAttrName + ":" + sAttrValue);
        }

        logger.debug("End");
        return folder;
    }

    /**
     *
     * @param document
     * @param parentNode
     */
    public void toXml(Document document, Element parentNode){
        logger.debug("Begin");

        parentNode.setAttribute(ATTRIBUTE_NAME, this.get_name());
        parentNode.setAttribute(ATTRIBUTE_SRC, this.get_src());
        parentNode.setAttribute(ATTRIBUTE_TITLE, this.get_title());
        parentNode.setAttribute(ATTRIBUTE_INFOTEXT, this.get_infoText());
        parentNode.setAttribute(ATTRIBUTE_KEYWORDS, this.get_keywords());
        parentNode.setAttribute(ATTRIBUTE_UPDATE, this.get_updateString());

        for (Map.Entry<String, Object> entry1 : this.elements.entrySet()) {
            String key = entry1.getKey();
            Object value = entry1.getValue();

            if (value instanceof  Gallery) {
                Gallery gallery = (Gallery)value;

                //Recoger y escribir atributos
                Element galleryNode = document.createElement(gallery.get_nodeName());
                gallery.toXml(document, galleryNode);

                parentNode.appendChild(galleryNode);
            } else if (value instanceof  Section) {
                Section section = (Section)value;

                Element sectionNode = document.createElement(section.get_nodeName());
                section.toXml(document, sectionNode);

                parentNode.appendChild(sectionNode);
            }
        }

        logger.debug("End");
    }
}
