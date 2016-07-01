package iml.imfotografia.xml.feed.element;

import java.util.Date;

public class PubDate {
    private Date _content;

    /**
     * CONSTRUCTORS
     */
    public PubDate() {
    }

    public PubDate(Date content) {
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
