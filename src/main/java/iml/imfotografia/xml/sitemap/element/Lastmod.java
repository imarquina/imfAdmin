package iml.imfotografia.xml.sitemap.element;

import java.util.Date;

public class Lastmod {
    private Date _content;

    /**
     * CONSTRUCTORS
     */
    public Lastmod() {
    }

    public Lastmod(Date content) {
        this.set_content(content);
    }

    /**
     * GETTER / SETTER
     */
    public Date get_content() {
        return this._content;
    }

    public void set_content(Date content) {
        this._content = content;
    }
}
