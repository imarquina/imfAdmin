package iml.framework.xml.website.feed.struct;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Rss {
    private String _version;
    private String _nodeName;
    public Channel chanel;

    final static Logger logger = Logger.getLogger(Rss.class);

    /**
     * CONSTRUCTORS
     */
    public Rss() {
        this._nodeName = "rss";
        this._version = "";
        chanel = new Channel();
    }

    public Rss(String _version) {
        this();
        this._version = _version;
    }

    /**
     * GETTER / SETTER
     */
    public String get_version() {
        return this._version;
    }

    public void set_version(String version) {
        this._version = version;
    }

    public String get_nodeName() {
        return this._nodeName;
    }

    public void addChanel(Channel chanel){
        this.chanel = chanel;
    }

    public void toXml(Document document){
        logger.debug("Begin");

        //Main Node
        Element rssNode = document.getDocumentElement();
        rssNode.setAttribute("version", this.get_version());

        chanel.toXml(document, rssNode);

        logger.debug("End");
    }
}
