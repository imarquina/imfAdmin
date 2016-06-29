package resources.elements;

import java.util.Date;

/**
 * Created by inaki.marquina on 29/06/2016.
 */
public class Photo {
    private String _id;
    private Integer _width;
    private Integer _height;
    private String _caption;
    private String _src;
    private String _linkText;
    private String _linkUrl;
    private String _infoText;
    private String _format;
    private String _stock;
    private Integer _price;
    private Date _update;
    private Date _public;

    /**
     * CONSTRUCTORES
     */
    public Photo() {

    }

    public Photo(String _id, Integer _width, Integer _height, String _caption, String _src, Date _update, Date _public) {
        this._id = _id;
        this._width = _width;
        this._height = _height;
        this._caption = _caption;
        this._src = _src;
        this._update = _update;
        this._public = _public;
    }

    /**
     * GETTER / SETTER
     */
    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Integer get_width() {
        return _width;
    }

    public void set_width(Integer _width) {
        this._width = _width;
    }

    public Integer get_height() {
        return _height;
    }

    public void set_height(Integer _height) {
        this._height = _height;
    }

    public String get_caption() {
        return _caption;
    }

    public void set_caption(String _caption) {
        this._caption = _caption;
    }

    public String get_src() {
        return _src;
    }

    public void set_src(String _src) {
        this._src = _src;
    }

    public String get_linkText() {
        return _linkText;
    }

    public void set_linkText(String _linkText) {
        this._linkText = _linkText;
    }

    public String get_linkUrl() {
        return _linkUrl;
    }

    public void set_linkUrl(String _linkUrl) {
        this._linkUrl = _linkUrl;
    }

    public String get_infoText() {
        return _infoText;
    }

    public void set_infoText(String _infoText) {
        this._infoText = _infoText;
    }

    public String get_format() {
        return _format;
    }

    public void set_format(String _format) {
        this._format = _format;
    }

    public String get_stock() {
        return _stock;
    }

    public void set_stock(String _stock) {
        this._stock = _stock;
    }

    public Integer get_price() {
        return _price;
    }

    public void set_price(Integer _price) {
        this._price = _price;
    }

    public Date get_update() {
        return _update;
    }

    public void set_update(Date _update) {
        this._update = _update;
    }

    public Date get_public() {
        return _public;
    }

    public void set_public(Date _public) {
        this._public = _public;
    }
}
