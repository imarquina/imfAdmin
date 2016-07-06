package iml.imfotografia.xml.feed.struct;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LastBuildDate {
    private Date _content;
    private DateFormat _formatDate = new SimpleDateFormat("yyyymmdd");

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

    public void set_content(String content) throws ParseException {
        this._content = _formatDate.parse(content);
    }

    public void set_content(Date content) {
        this._content = content;
    }
}
