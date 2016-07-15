package iml.imfotografia.xml.feed.struct;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class PubDate {
    private String _nodeName;
    private Date _content;
    private DateFormat _dateFormatIn = new SimpleDateFormat("yyyymmdd");
    private DateFormat _dateFormatOut = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH);

    /**
     * CONSTRUCTORS
     */
    public PubDate() {
        this._nodeName = "pubDate";
        this._content = new Date();
        this._dateFormatOut.setTimeZone(TimeZone.getTimeZone("GMT"));
    }

    public PubDate(String content) throws ParseException {
        this();
        this.set_content(content);
    }

    public PubDate(Date content) {
        this();
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

    public String get_nodeName() {
        return _nodeName;
    }

    /**
     *
     * @param document
     * @param parentNode
     */
    public void toXml(Document document, Element parentNode){
        Element pubDateNode = document.createElement(this.get_nodeName());
        pubDateNode.appendChild(document.createTextNode(this.get_content()));
        parentNode.appendChild(pubDateNode);
    }
}
