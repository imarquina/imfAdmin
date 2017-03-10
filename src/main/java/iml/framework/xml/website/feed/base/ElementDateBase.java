package iml.framework.xml.website.feed.base;

import iml.framework.xml.interfaces.IXmlNode;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by imarquina on 18/7/16.
 */
public abstract class ElementDateBase implements IXmlNode {
    private String _nodeName;
    private Date _content;
    private DateFormat _dateFormatIn = new SimpleDateFormat("yyyyMMdd");
    private DateFormat _dateFormatOut = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss 'GMT'", Locale.ENGLISH);

    /**
     * CONSTRUCTORS
     */
    public ElementDateBase() {
        this._nodeName = "";
        this._content = new Date();
        //this._dateFormatOut.setTimeZone(TimeZone.getTimeZone("GMT"));
    }

    public ElementDateBase(String content) throws ParseException {
        this();
        this.set_content(content);
    }

    public ElementDateBase(Date content) {
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
        return this._nodeName;
    }

    protected void set_nodeName(String nodeName) { this._nodeName = nodeName; }

    /**
     *
     * @param document
     * @param parentNode
     */
    public abstract void toXml(Document document, Element parentNode);
}
