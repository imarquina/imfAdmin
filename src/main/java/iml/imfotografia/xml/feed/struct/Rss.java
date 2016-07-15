package iml.imfotografia.xml.feed.struct;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Rss {
    private String _version;
    private String _nodeName;
    public Channel chanel;

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
        //Main Node
        Element rssNode = document.getDocumentElement();
        rssNode.setAttribute("version", this.get_version());

        chanel.toXml(document, rssNode);
    }
}
