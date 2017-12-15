package iml.framework.xml.website.config.interfaces;

import iml.framework.xml.interfaces.IXmlNode;

/**
 * Created by imarquina on 3/8/16.
 */
public interface IElement extends IXmlNode {
    String _nodeName = "";
    String _id = "";

    public String get_id();
    public void set_id(String id);
    public String get_nodeName();
}
