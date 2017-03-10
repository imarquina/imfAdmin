package iml.framework.xml.website.config.interfaces;

import iml.framework.xml.website.interfaces.IXmlNode;

import java.text.ParseException;
import java.util.Date;

/**
 * Created by imarquina on 3/8/16.
 */
public interface ICollection extends IXmlNode {
    public String get_name();
    public void set_name(String name);
    public String get_src();
    public void set_src(String src);
    public String get_title();
    public void set_title(String title);
    public String get_infoText();
    public void set_infoText(String infoText);
    public String get_keywords();
    public void set_keywords(String keywords);
    public Date get_update();
    public String get_updateString();
    public void set_update(String update) throws ParseException;
    public void set_update(Date update);
    public String get_nodeName();

    public Integer getIndexKey(String key);
}
