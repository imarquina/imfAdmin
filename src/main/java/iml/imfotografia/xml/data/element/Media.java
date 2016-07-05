package iml.imfotografia.xml.data.element;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Media {
    private String _id;
    private Integer _width;
    private Integer _height;
    private String _caption;
    private String _src;
    private String _linkText;
    private String _linkUrl;
    private String _infoText;
    private String _format;
    private Date _update;
    private Date _public;
    private DateFormat _formatDate = new SimpleDateFormat("yyyymmdd");

    /**
     * CONSTRUCTORES
     */
    public Media() {

    }

    public Media(String id, Integer width, Integer height, String caption, String src,
                 Date update, Date dPublic) {
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
        this._id = id;
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

    public Date get_update() {
        return this._update;
    }

    public void set_update(String update) throws ParseException {
        this._update = _formatDate.parse(update);
    }

    public void set_update(Date update) {
        this._update = update;
    }

    public Date get_dPublic() {
        return this._public;
    }

    public void set_dPublic(String dPublic) throws ParseException {
        this._public = _formatDate.parse(dPublic);
    }

    public void set_dPublic(Date dPublic) {
        this._public = dPublic;
    }
}
