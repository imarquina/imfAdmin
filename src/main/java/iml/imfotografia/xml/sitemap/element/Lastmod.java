package iml.imfotografia.xml.sitemap.element;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Lastmod {
    private Date _content;
    private DateFormat _dateFormatOut = new SimpleDateFormat("yyyy-mm-dd");

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
    public String get_content() {
        return _dateFormatOut.format(this._content);
    }

    public void set_content(Date content) {
        this._content = content;
    }
}
