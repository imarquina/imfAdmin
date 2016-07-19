package iml.imfotografia.xml.element.structs;

import iml.imfotografia.xml.sitemap.element.Url;
import org.apache.log4j.Logger;
import org.apache.log4j.varia.StringMatchFilter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.Map;

/**
 * Created by imarquina on 18/7/16.
 */
public class Config {
    private String _nodeName = "";

    public Photos images = new Photos();
    public Medias medias = new Medias();

    final static Logger logger = Logger.getLogger(Config.class);

    /**
     * CONSTRUCTORS
     */
    public Config(){
        this._nodeName = "config";
    }

    /**
     * GETTER / SETTER
     */
    public String get_nodeName() {
        return this._nodeName;
    }

    /**
     * PUBLIC METHODS
     */

    /**
     *
     * @param document
     * @param parentNode
     */
    public void toXml(Document document, Element parentNode){
        logger.debug("Begin");

        Element photosNode = document.createElement(this.images.get_nodeName());

        //bucle para los item
        for (Map.Entry<String, Photo> entry : this.images.photo.entrySet()) {
            String key = entry.getKey();
            Photo value = entry.getValue();

            value.toXml(document, photosNode);
        }

        parentNode.appendChild(photosNode);

        Element mediasNode = document.createElement(this.medias.get_nodeName());

        //bucle para los item
        for (Map.Entry<String, Media> entry : this.medias.media.entrySet()) {
            String key = entry.getKey();
            Media value = entry.getValue();

            value.toXml(document, mediasNode);
        }

        parentNode.appendChild(mediasNode);

        logger.debug("End");
    }
}
