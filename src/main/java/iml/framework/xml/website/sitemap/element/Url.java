package iml.framework.xml.website.sitemap.element;

import iml.framework.xml.interfaces.IXmlNode;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.Date;

public class Url implements IXmlNode {
    private String _nodeName;

    public Loc loc;
    public Lastmod lastmod;

    final static Logger logger = LogManager.getLogger(Url.class);

    /**
     * CONSTRUCTORS
     */
    public Url() {
        this._nodeName = "url";
        loc = new Loc();
        lastmod = new Lastmod();
    }

    public Url(String sLoc, Date dLastMod){
        this();

        Loc loc = new Loc(sLoc);
        this.addLoc(loc);

        Lastmod lastmod = new Lastmod(dLastMod);
        this.addLasmod(lastmod);
    }

    /**
     *  PUBLIC METHODS
     */
    public void addLoc(Loc loc) {
        this.loc = loc;
    }

    public void addLasmod(Lastmod lastmod) { this.lastmod = lastmod; }

    public String get_nodeName() {
        return _nodeName;
    }

    /**
     *
     * @param document
     * @param parentNode
     */
    public void toXml(Document document, Element parentNode){
        logger.debug("Begin");

        Element pubDateNode = document.createElement(this.get_nodeName());

        this.loc.toXml(document, pubDateNode);
        this.lastmod.toXml(document, pubDateNode);

        parentNode.appendChild(pubDateNode);

        logger.debug("End");
    }
}
