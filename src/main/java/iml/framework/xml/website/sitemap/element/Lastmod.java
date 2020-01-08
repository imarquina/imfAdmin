package iml.framework.xml.website.sitemap.element;

import iml.framework.xml.interfaces.IXmlNode;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Lastmod implements IXmlNode {
    private String _nodeName;
    private Date _content;
    private DateFormat _dateFormatOut = new SimpleDateFormat("yyyy-MM-dd");

    final static Logger logger = LogManager.getLogger(Lastmod.class);

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
        try {
            return _dateFormatOut.format(this._content);
        }
        catch(Exception ex){
            System.out.print(ex.fillInStackTrace());
            return "";
        }
    }

    public void set_content(Date content) {
        this._content = content;
    }

    public String get_nodeName() {
        return _nodeName;
    }

    public void toXml(Document document, Element parentNode){
        logger.debug("Begin");

        Element pubDateNode = document.createElement(this.get_nodeName());
        pubDateNode.appendChild(document.createTextNode(this.get_content()));
        parentNode.appendChild(pubDateNode);

        logger.debug("End");
    }
}
