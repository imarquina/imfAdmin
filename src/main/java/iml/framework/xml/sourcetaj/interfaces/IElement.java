package iml.framework.xml.sourcetaj.interfaces;

import iml.framework.xml.interfaces.IXmlNode;

/**
 * Created by imarquina on 3/8/16.
 */
public interface IElement extends IXmlNode {
    String _nodeName = "";
    String _content = "";

    public String get_content();
    public void set_content(String id);
    public String get_nodeName();
}
