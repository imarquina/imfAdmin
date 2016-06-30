package resources;

import java.util.Date;
import java.util.Hashtable;

/**
 * Created by imarquina on 29/6/16.
 */
public class XmlPhotos {
    public Photos photos = new Photos();
}

class Photos {
    public Hashtable<String, Photo> photo;
}

class Photo {
    String _id;
    Integer _width;
    Integer _height;
    String _caption;
    String _src;
    String _linkText;
    String _linkUrl;
    String _infoText;
    String _format;
    String _stock;
    Integer _price;
    Date _update;
    Date _public;

    /**
     * CONSTRUCTORES
     */
    protected Photo() {

    }

    protected Photo(String _id, Integer _width, Integer _height, String _caption, String _src, Date _update, Date _public) {
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
    protected String get_id() {
        return _id;
    }

    protected void set_id(String _id) {
        this._id = _id;
    }

    protected Integer get_width() {
        return _width;
    }

    protected void set_width(Integer _width) {
        this._width = _width;
    }

    protected Integer get_height() {
        return _height;
    }

    protected void set_height(Integer _height) {
        this._height = _height;
    }

    protected String get_caption() {
        return _caption;
    }

    protected void set_caption(String _caption) {
        this._caption = _caption;
    }

    protected String get_src() {
        return _src;
    }

    protected void set_src(String _src) {
        this._src = _src;
    }

    protected String get_linkText() {
        return _linkText;
    }

    protected void set_linkText(String _linkText) {
        this._linkText = _linkText;
    }

    protected String get_linkUrl() {
        return _linkUrl;
    }

    protected void set_linkUrl(String _linkUrl) {
        this._linkUrl = _linkUrl;
    }

    protected String get_infoText() {
        return _infoText;
    }

    protected void set_infoText(String _infoText) {
        this._infoText = _infoText;
    }

    protected String get_format() {
        return _format;
    }

    protected void set_format(String _format) {
        this._format = _format;
    }

    protected String get_stock() {
        return _stock;
    }

    protected void set_stock(String _stock) {
        this._stock = _stock;
    }

    protected Integer get_price() {
        return _price;
    }

    protected void set_price(Integer _price) {
        this._price = _price;
    }

    protected Date get_update() {
        return _update;
    }

    protected void set_update(Date _update) {
        this._update = _update;
    }

    protected Date get_public() {
        return _public;
    }

    protected void set_public(Date _public) {
        this._public = _public;
    }
}
