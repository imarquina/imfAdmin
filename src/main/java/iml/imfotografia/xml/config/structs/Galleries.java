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

        logger.debug("End");
    }
}
