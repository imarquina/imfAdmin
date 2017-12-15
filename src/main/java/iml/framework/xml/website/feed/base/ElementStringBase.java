package iml.framework.xml.website.feed.base;

import iml.framework.xml.interfaces.IXmlNode;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Created by inaki.marquina on 18/07/2016.
 */
public abstract class ElementStringBase implements IXmlNode {
    private String _nodeName;
    private String _content;

    /**
     * CONSTRUCTORS
     */
    public ElementStringBase() {
        this._nodeName = "";
        this._content = "";
    }

    public ElementStringBase(String content) {
        this();
        this.set_content(content);
    }

    /**
     * GETTER / SETTER
     */
    public String get_content() {
        return this._content;
    }

    public void set_content(String content) {
        this._content = content;
    }

    public String get_nodeName() {
        return _nodeName;
    }

    protected void set_nodeName(String nodeName) { this._nodeName = nodeName; }

    /**
     *
     * @param document
     * @param parentNode
     */
    public abstract void toXml(Document document, Element parentNode);
}
