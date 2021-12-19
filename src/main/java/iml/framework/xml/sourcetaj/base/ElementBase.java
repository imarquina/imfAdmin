package iml.framework.xml.sourcetaj.base;

import iml.framework.xml.sourcetaj.interfaces.IElement;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Created by imarquina on 3/8/16.
 */
public class ElementBase implements IElement {
    private String _nodeName = "";
    private String _content = "";

    final static Logger logger = LogManager.getLogger(ElementBase.class);

    /**
     * CONSTRUCTORS
     */
    public ElementBase() {
        this._nodeName = "node";
    }

    public ElementBase(String content) {
        this();
        this.set_content(content);
    }

    /**
     * GETTER / SETTER
     */
    public String get_nodeName() {
        return this._nodeName;
    }

    protected void set_nodeName(String value) {
        this._nodeName = value;
    }

    public String get_content() {
        return _content;
    }

    public void set_content(String _content) {
        this._content = _content;
    }

    public void toXml(Document document, Element parentNode) {

    }
}
