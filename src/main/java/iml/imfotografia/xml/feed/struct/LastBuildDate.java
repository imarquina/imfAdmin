package iml.imfotografia.xml.feed.struct;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class LastBuildDate {
    private Date _content;
    private DateFormat _dateFormatIn = new SimpleDateFormat("yyyymmdd");
    private DateFormat _dateFormatOut = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH);

    /**
     * CONSTRUCTORS
     */
    public LastBuildDate() {
    }

    public LastBuildDate(String content) throws ParseException {
        this.set_content(content);
    }

    public LastBuildDate(Date content) {
        this.set_content(content);
    }

    /**
     * GETTER / SETTER
     */
    public String get_content() {
        return _dateFormatOut.format(this._content);
    }

    public void set_content(String content) throws ParseException {
        this._content = _dateFormatIn.parse(content);
    }

    public void set_content(Date content) {
        this._content = content;
    }

    public void toXml(Document document, Element parentNode){
        Element lastBuildDateNode = document.createElement("lastBuildDate");
        lastBuildDateNode.appendChild(document.createTextNode(this.get_content()));
        parentNode.appendChild(lastBuildDateNode);
    }
}
