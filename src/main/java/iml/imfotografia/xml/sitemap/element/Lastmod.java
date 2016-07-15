package iml.imfotografia.xml.sitemap.element;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Lastmod {
    private String _nodeName;
    private Date _content;
    private DateFormat _dateFormatOut = new SimpleDateFormat("yyyy-mm-dd");

    /**
     * CONSTRUCTORS
     */
    public Lastmod() {
        this._nodeName = "lastmod";
        this._content = new Date();
    }

    public Lastmod(Date content) {
        this();
        this.set_content(content);
    }

    /**
     * GETTER / SETTER
     */
    public String get_content() {
        return _dateFormatOut.format(this._content);
    }

    public void set_content(Date content) {
        this._content = content;
    }

    public String get_nodeName() {
        return _nodeName;
    }

    public void toXml(Document document, Element parentNode){
        Element pubDateNode = document.createElement(this.get_nodeName());
        pubDateNode.appendChild(document.createTextNode(this.get_content()));
        parentNode.appendChild(pubDateNode);
    }
}
