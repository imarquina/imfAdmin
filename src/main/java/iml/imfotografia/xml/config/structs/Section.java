package iml.imfotografia.xml.config.structs;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static iml.imfotografia.utils.Number.isNumeric;

public class Section {
    private String _name;
    private Integer _width;
    private Integer _height;
    private Boolean _byDefault;
    private Date _update;
    private String _content;
    private DateFormat _formatDate = new SimpleDateFormat("yyyymmdd");

    /**
     * CONSTRUCTORES
     */
    public Section() {
    }

    public Section(String name, Integer width, Integer height, Boolean byDefault, Date update) {
        this.set_name(name);
        this.set_width(width);
        this.set_height(height);
        this.set_byDefault(byDefault);
        this.set_update(update);
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

    public Integer get_width() {
        return this._width;
    }

    public void set_width(String width) {
        if (isNumeric(width))
            this._width = Integer.parseInt(width);
        else
            this._width = 0;
    }

    public void set_width(Integer width) {
        this._width = width;
    }

    public Integer get_height() {
        return this._height;
    }

    public void set_height(String height) {
        if (isNumeric(height))
            this._height = Integer.parseInt(height);
        else
            this._height = 0;
    }

    public void set_height(Integer height) {
        this._height = height;
    }

    public Boolean get_byDefault() {
        return this._byDefault;
    }

    public void set_byDefault(String byDefault) {
        this._byDefault = Boolean.valueOf(byDefault);
    }

    public void set_byDefault(Boolean byDefault) {
        this._byDefault = byDefault;
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

    public String get_content() {
        return this._content;
    }

    public void set_content(String content) {
        this._content = content;
    }
}
