package resources.Sitemap;

import java.util.Date;
import java.util.Hashtable;

/**
 * Created by imarquina on 29/6/16.
 */
public class XmlSitemap {
    public Urlset urlset = new Urlset();
}

class Urlset {
    private String _xmlns;
    private String xmlns_xsi;
    private String xsi_schemaLocation;
    Hashtable<String, Url> url;

    /**
     * CONSTRUCTORS
     */
    protected Urlset() {
    }

    protected Urlset(String _xmlns, String xmlns_xsi, String xsi_schemaLocation) {
        this._xmlns = _xmlns;
        this.xmlns_xsi = xmlns_xsi;
        this.xsi_schemaLocation = xsi_schemaLocation;
    }

    /**
     * GETTER / SETTER
     */
    protected String get_xmlns() {
        return _xmlns;
    }

    protected void set_xmlns(String _xmlns) {
        this._xmlns = _xmlns;
    }

    protected String getXmlns_xsi() {
        return xmlns_xsi;
    }

    protected void setXmlns_xsi(String xmlns_xsi) {
        this.xmlns_xsi = xmlns_xsi;
    }

    protected String getXsi_schemaLocation() {
        return xsi_schemaLocation;
    }

    protected void setXsi_schemaLocation(String xsi_schemaLocation) {
        this.xsi_schemaLocation = xsi_schemaLocation;
    }
}

class Url {
    Loc loc = new Loc();
    Lastmod lastmod = new Lastmod();

    /**
     * CONSTRUCTORS
     */
    public Url() {
    }
}

class Loc {
    private String _content;

    /**
     * CONSTRUCTORS
     */
    protected Loc() {
    }

    /**
     * GETTER / SETTER
     */
    protected String get_content() {
        return _content;
    }

    protected void set_content(String _content) {
        this._content = _content;
    }
}

class Lastmod {
    private Date _content;

    /**
     * CONSTRUCTORS
     */
    protected Lastmod() {
    }

    /**
     * GETTER / SETTER
     */
    protected Date get_content() {
        return _content;
    }

    protected void set_content(Date _content) {
        this._content = _content;
    }
}
