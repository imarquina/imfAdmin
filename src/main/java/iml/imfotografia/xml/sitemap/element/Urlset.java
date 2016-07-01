package iml.imfotografia.xml.sitemap.element;

import java.util.Hashtable;

public class Urlset {
    private String _xmlns;
    private String xmlns_xsi;
    private String xsi_schemaLocation;
    Hashtable<String, Url> url;

    /**
     * CONSTRUCTORS
     */
    public Urlset() {
        url = new Hashtable<String, Url>();
    }

    public Urlset(String xmlns, String xmlns_xsi, String xsi_schemaLocation) {
        this.set_xmlns(xmlns);
        this.setXmlns_xsi(xmlns_xsi);
        this.setXsi_schemaLocation(xsi_schemaLocation);
        url = new Hashtable<String, Url>();
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
}
