package iml.imfotografia.xml.sitemap.element;

import iml.imfotografia.xml.feed.XmlFeed;
import iml.imfotografia.xml.feed.struct.Item;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;

public class Urlset {
    private Integer _iKey;
    private String _nodeName;
    private String _xmlns;
    private String xmlns_xsi;
    private String xsi_schemaLocation;

    public Map<Integer, Url> url;

    final static Logger logger = Logger.getLogger(Urlset.class);

    /**
     * CONSTRUCTORS
     */
    public Urlset() {
        this._iKey = 0;
        this._nodeName = "urlset";
        this.url = new LinkedHashMap<Integer, Url>();
    }

    public Urlset(String xmlns, String xmlns_xsi, String xsi_schemaLocation) {
        this();

        this.set_xmlns(xmlns);
        this.setXmlns_xsi(xmlns_xsi);
        this.setXsi_schemaLocation(xsi_schemaLocation);
    }

    /**
     * GETTER / SETTER
     */
    public String get_xmlns() {
        return this._xmlns;
    }

    public void set_xmlns(String xmlns) {
        this._xmlns = xmlns;
    }

    public String getXmlns_xsi() {
        return this.xmlns_xsi;
    }

    public void setXmlns_xsi(String xmlns_xsi) {
        this.xmlns_xsi = xmlns_xsi;
    }

    public String getXsi_schemaLocation() {
        return this.xsi_schemaLocation;
    }

    public void setXsi_schemaLocation(String xsi_schemaLocation) {
        this.xsi_schemaLocation = xsi_schemaLocation;
    }

    public String get_nodeName() {
        return _nodeName;
    }

    /**
     * PUBLIC METHODS
     */
    public void addUrl(Url url) {
        this.url.put(_iKey++, url);
    }

    /**
     *
     * @param document
     * @param parentNode
     */
    public void toXml(Document document, Element parentNode){
        logger.debug("Begin");

        Element pubDateNode = document.createElement(this.get_nodeName());

        //bucle para los item
        for (Map.Entry<Integer, Url> entry : this.url.entrySet()) {
            Integer key = entry.getKey();
            Url value = entry.getValue();

            value.toXml(document, pubDateNode);
        }

        parentNode.appendChild(pubDateNode);

        logger.debug("End");
    }
}
