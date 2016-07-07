package iml.imfotografia.xml.element.interfaces;

import java.text.ParseException;
import java.util.Date;

/**
 * Created by inaki.marquina on 07/07/2016.
 */
public interface IElement {
    String _id = "";
    Integer _width = 0;
    Integer _height = 0;
    String _caption = "";
    String _src = "";
    String _dx = "";
    String _dy = "";
    String _linkText = "";
    String _linkUrl = "";
    String _infoText = "";
    String _format = "";
    String _stock = "";
    Integer _price = 0;
    Date _update = new Date();
    Date _public = new Date();

    public String get_id();
    public void set_id(String id);
    public Integer get_width();
    public void set_width(String width);
    public void set_width(Integer width);
    public Integer get_height();
    public void set_height(String height);
    public void set_height(Integer height);
    public String get_caption();
    public void set_caption(String caption);
    public String get_src();
    public void set_src(String src);
    public String get_linkText();
    public void set_linkText(String linkText);
    public String get_linkUrl();
    public void set_linkUrl(String linkUrl);
    public String get_infoText();
    public void set_infoText(String infoText);
    public String get_format();
    public void set_format(String format);
    public String get_stock();
    public void set_stock(String stock);
    public Integer get_price();
    public void set_price(String price);
    public void set_price(Integer price);
    public Date get_update();
    public void set_update(String update) throws ParseException;
    public void set_update(Date update);
    public Date get_dPublic();
    public void set_dPublic(String dPublic) throws ParseException;
    public void set_dPublic(Date dPublic);
    public String get_dx();
    public void set_dx(String dx);
    public String get_dy();
    public void set_dy(String dy);
}
