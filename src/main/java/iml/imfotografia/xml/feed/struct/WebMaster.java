package iml.imfotografia.xml.feed.struct;

import iml.imfotografia.xml.feed.XmlFeed;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class WebMaster {
    private String _nodeName;
    private String _content;

    final static Logger logger = Logger.getLogger(WebMaster.class);

    /**
     * CONSTRUCTORS
     */
    public WebMaster() {
        this._nodeName = "webMaster";
        this._content = "";
    }

    public WebMaster(String content) {
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

    /**
     *
     * @param document
     * @param parentNode
     */
    public void toXml(Document document, Element parentNode){
        logger.debug("Begin");

        Element webMasterNode = document.createElement(this.get_nodeName());
        webMasterNode.appendChild(document.createTextNode(this.get_content()));
        parentNode.appendChild(webMasterNode);

        logger.debug("End");
    }
}
