package resources.elements;

import java.util.Date;

/**
 * Created by inaki.marquina on 29/06/2016.
 */
public class Section {
    private String _name;
    private Integer _width;
    private Integer _height;
    private Boolean _byDefault;
    private Date _update;
    private String _content;

    /**
     * CONSTRUCTORES
     */
    public Section() {
    }

    public Section(String _name, Integer _width, Integer _height, Boolean _byDefault, Date _update) {
        this._name = _name;
        this._width = _width;
        this._height = _height;
        this._byDefault = _byDefault;
        this._update = _update;
    }

    /**
     * GETTER / SETTER
     */
    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
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

    public Boolean get_byDefault() {
        return _byDefault;
    }

    public void set_byDefault(Boolean _byDefault) {
        this._byDefault = _byDefault;
    }

    public Date get_update() {
        return _update;
    }

    public void set_update(Date _update) {
        this._update = _update;
    }

    public String get_content() {
        return _content;
    }

    public void set_content(String _content) {
        this._content = _content;
    }
}
