package iml.imfotografia.xml.config.structs;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.LinkedHashMap;
import java.util.Map;

public class Galleries {
    private Integer _iKey;
    private String _nodeName = "";
    public Map<Integer, Object> elements;

    final static Logger logger = Logger.getLogger(Galleries.class);

    /**
     * CONSTRUCTORES
     */
    public Galleries() {
        this._iKey = 0;
        this._nodeName = "galleries";

        this.elements = new LinkedHashMap<Integer, Object>();
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
        this.elements.put(_iKey++, gallery);
    }

    /**
     *
     * @return
     */
    public void addFolder(Folder folder){
        this.elements.put(_iKey++, folder);
    }

    /**
     *
     * @return
     */
    public void addMultimedia(Multimedia multimedia) {
        this.elements.put(_iKey++, multimedia);
    }

    /**
     *
     * @param document
     * @param parentNode
     */
    public void toXml(Document document, Element parentNode){
        logger.debug("Begin");

        for (Map.Entry<Integer, Object> entry1 : this.elements.entrySet()) {
            Integer key = entry1.getKey();
            Object value = entry1.getValue();

            if (value instanceof  Gallery) {
                Gallery gallery = (Gallery)value;

                //Recoger y escribir atributos
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
