package resources.Feed.element;

import java.util.Date;

public class LastBuildDate {
    private Date _content;

    /**
     * CONSTRUCTORS
     */
    public LastBuildDate() {
    }

    public LastBuildDate(Date content) {
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
