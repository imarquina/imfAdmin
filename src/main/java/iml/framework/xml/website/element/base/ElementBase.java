package iml.framework.xml.website.element.base;

import iml.framework.xml.website.element.interfaces.IElement;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static iml.framework.arq.utils.Number.isNumeric;

/**
 * Created by inaki.marquina on 07/07/2016.
 */
public abstract class ElementBase implements IElement {
    private String _nodeName = "";
    private String _id;
    private Integer _width;
    private Integer _height;
    private String _caption;
    private String _src;
    private String _dx;
    private String _dy;
    private String _linkText;
    private String _linkUrl;
    private String _infoText;
    private String _format;
    private String _stock;
    private Integer _price;
    private Date _update;
    private Date _public;
    private DateFormat _dateFormatIn = new SimpleDateFormat("yyyyMMdd");
    private DateFormat _dateFormatOut = new SimpleDateFormat("yyyyMMdd");

    final static Logger logger = LogManager.getLogger(ElementBase.class);

    /**
     * CONSTANTS
     */
    private static final String ATTRIBUTE_WIDTH = "width";
    private static final String ATTRIBUTE_HEIGHT = "height";
    private static final String ATTRIBUTE_CAPTION = "caption";
    private static final String ATTRIBUTE_SRC = "src";
    private static final String ATTRIBUTE_DX = "dx";
    private static final String ATTRIBUTE_DY = "dy";
    private static final String ATTRIBUTE_LINKTEXT = "linktext";
    private static final String ATTRIBUTE_INFOTEXT = "infotext";
    private static final String ATTRIBUTE_LINKURL = "linkurl";
    private static final String ATTRIBUTE_FORMAT = "format";
    private static final String ATTRIBUTE_PRICE = "price";
    private static final String ATTRIBUTE_STOCK = "stock";
    private static final String ATTRIBUTE_UPDATE = "update";
    private static final String ATTRIBUTE_PUBLIC = "public";

    /**
     * CONSTRUCTORES
     */
    public ElementBase() {
        this._id = "";
        this._width = 0;
        this._height = 0;
        this._caption = "";
        this._src = "";
        this._dx = "";
        this._dy = "";
        this._linkText = "";
        this._infoText = "";
        this._format = "";
        this._stock = "";
        this._price = 0;
    }

    public ElementBase(String id, Integer width, Integer height, String caption, String src,
                 Date update, Date dPublic) {
        this();
        this.set_id(id);
        this.set_width(width);
        this.set_height(height);
        this.set_caption(caption);
        this.set_src(src);
        this.set_update(update);
        this.set_dPublic(dPublic);
    }

    /**
     * GETTER / SETTER
     */
    public String get_id() {
        return this._id;
    }

    public void set_id(String id) {
        this._id = id.replace(".jpg","").replace(".png","");
    }

    public Integer get_width() {
        return this._width;
    }

    public void set_width(String width) {
        this._width = Integer.parseInt(width);
    }

    public void set_width(Integer width) {
        this._width = width;
    }

    public Integer get_height() {
        return this._height;
    }

    public void set_height(String height) {
        this._height = Integer.parseInt(height);
    }

    public void set_height(Integer height) {
        this._height = height;
    }

    public String get_caption() {
        return this._caption;
    }

    public void set_caption(String caption) {
        this._caption = caption;
    }

    public String get_src() {
        return this._src;
    }

    public void set_src(String src) {
        this._src = src;
    }

    public String get_linkText() {
        return this._linkText;
    }

    public void set_linkText(String linkText) {
        this._linkText = linkText;
    }

    public String get_linkUrl() {
        return this._linkUrl;
    }

    public void set_linkUrl(String linkUrl) {
        this._linkUrl = linkUrl;
    }

    public String get_infoText() {
        return this._infoText;
    }

    public void set_infoText(String infoText) {
        this._infoText = infoText;
    }

    public String get_format() {
        return this._format;
    }

    public void set_format(String format) {
        this._format = format;
    }

    public String get_stock() {
        return this._stock;
    }

    public void set_stock(String stock) {
        this._stock = stock;
    }

    public Integer get_price() {
        return this._price;
    }

    public void set_price(String price) {
        if (isNumeric(price))
            this._price = Integer.parseInt(price);
        else
            this._price = 0;
    }
    public void set_price(Integer price) {
        this._price = price;
    }

    public Date get_update() {
        return this._update;
    }

    public String get_updateString() {
        return _dateFormatOut.format(this._update);
    }

    public void set_update(String update) throws ParseException {
        this._update = _dateFormatIn.parse(update);
    }

    public void set_update(Date update) {
        this._update = update;
    }

    public Date get_dPublic() {
        return this._public;
    }

    public String get_dPublicString() {
        return _dateFormatOut.format(this._public);
    }

    public void set_dPublic(String dPublic) throws ParseException {
        this._public = _dateFormatIn.parse(dPublic);
    }

    public void set_dPublic(Date dPublic) {
        this._public = dPublic;
    }

    public String get_dx() {
        return this._dx;
    }

    public void set_dx(String dx) {
        this._dx = dx;
    }

    public String get_dy() {
        return this._dy;
    }

    public void set_dy(String dy) {
        this._dy = dy;
    }

    public String get_nodeName() { return this._nodeName; }

    protected void set_nodeName(String nodeName) {
        this._nodeName = nodeName;
    }

    public void toXml(Document document, Element parentNode){
        logger.debug("Begin");

        Element nodeImage = document.createElement(this.get_nodeName());

        nodeImage.setAttribute(ATTRIBUTE_WIDTH, this.get_width().toString());
        nodeImage.setAttribute(ATTRIBUTE_HEIGHT, this.get_height().toString());
        nodeImage.setAttribute(ATTRIBUTE_CAPTION, this.get_caption());
        nodeImage.setAttribute(ATTRIBUTE_SRC, this.get_src());
        nodeImage.setAttribute(ATTRIBUTE_DX, this.get_dx());
        nodeImage.setAttribute(ATTRIBUTE_DY, this.get_dy());
        nodeImage.setAttribute(ATTRIBUTE_LINKTEXT, this.get_linkText());
        nodeImage.setAttribute(ATTRIBUTE_LINKURL, this.get_linkUrl());
        nodeImage.setAttribute(ATTRIBUTE_INFOTEXT, this.get_infoText());
        nodeImage.setAttribute(ATTRIBUTE_FORMAT, this.get_format());
        if (this.get_price()>0)
            nodeImage.setAttribute(ATTRIBUTE_PRICE, this.get_price().toString());
        nodeImage.setAttribute(ATTRIBUTE_STOCK, this.get_stock());
        nodeImage.setAttribute(ATTRIBUTE_UPDATE, this.get_updateString());
        nodeImage.setAttribute(ATTRIBUTE_PUBLIC, this.get_dPublicString());

        parentNode.appendChild(nodeImage);

        logger.debug("End");
    }
}
