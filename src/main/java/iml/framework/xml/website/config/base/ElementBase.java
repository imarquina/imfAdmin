package iml.framework.xml.website.config.base;

import iml.framework.xml.website.config.interfaces.IElement;
import iml.framework.xml.website.feed.base.ElementDateBase;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Created by imarquina on 3/8/16.
 */
public class ElementBase implements IElement {
    private String _nodeName = "";
    private String _id;

    final static Logger logger = Logger.getLogger(ElementDateBase.class);

    /**
     * CONSTANTS
     */
    public static final String ATTRIBUTE_ID = "id";

    /**
     * CONSTRUCTORS
     */
    public ElementBase() {
        this._nodeName = "ele";
    }

    public ElementBase(String id) {
        this();
        this.set_id(id);
    }

    /**
     * GETTER / SETTER
     */
    public String get_id() {
        return this._id;
    }

    public void set_id(String id) {
        this._id = id;
    }

    public String get_nodeName() {
        return this._nodeName;
    }

    protected void set_nodeName(String value) {
        this._nodeName = value;
    }

    /**
     *
     * @param document
     * @param parentNode
     */
    public void toXml(Document document, Element parentNode){
        logger.debug("Begin");

        parentNode.setAttribute(ATTRIBUTE_ID, this.get_id());

        logger.debug("End");
    }
}
