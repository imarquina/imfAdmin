package iml.imfotografia.xml.config.structs;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class Multimedia {
    private Integer _iKey;
    private String _nodeName = "";
    private String _name;
    private String _src;
    private String _title;
    private String _infoText;
    private String _keywords;
    private Date _update;
    private DateFormat _formatDate = new SimpleDateFormat("yyyymmdd");

    public Map<Integer, Object> elements;

    final static Logger logger = Logger.getLogger(Multimedia.class);

    /**
     * CONSTRUCTORES
     */
    public Multimedia() {
        this._iKey = 0;
        this._nodeName = "multimedia";
        this.elements = new LinkedHashMap<Integer, Object>();
    }

    public Multimedia(String name, String src, String title, String infotext,
                      String keywords, Date update) {
        this();
        this.set_name(name);
        this.set_src(src);
        this.set_title(title);
        this.set_infoText(infotext);
        this.set_keywords(keywords);
        this.set_update(update);

        elements = new LinkedHashMap<Integer, Object>();
    }

    /**
     * GETTER / SETTER
     */
    public String get_name() {
        return this._name;
    }

    public void set_name(String name) {
        this._name = name;
    }

    public String get_src() {
        return this._src;
    }

    public void set_src(String src) {
        this._src = src;
    }

    public String get_title() {
        return this._title;
    }

    public void set_title(String title) {
        this._title = title;
    }

    public String get_infoText() {
        return this._infoText;
    }

    public void set_infoText(String infotext) {
        this._infoText = infotext;
    }

    public String get_keywords() {
        return this._keywords;
    }

    public void set_keywords(String keywords) {
        this._keywords = keywords;
    }

    public Date get_update() {
        return this._update;
    }

    public void set_update(String update) throws ParseException {
        this._update = _formatDate.parse(update);
    }

    public void set_update(Date update) {
        this._update = update;
    }

    public String get_nodeName() {
        return this._nodeName;
    }

    /**
     *
     * @return
     */
    public void addVideo (Video video) {
        this.elements.put(_iKey++, video);
    }

    /**
     *
     * @param document
     * @param parentNode
     */
    public void toXml(Document document, Element parentNode){
        logger.debug("Begin");

        logger.debug("End");
    }
}
