package iml.imfotografia.xml.config.interfaces;

import iml.imfotografia.xml.config.structs.Image;
import iml.imfotografia.xml.interfaces.IXmlNode;
import org.w3c.dom.Node;

import java.text.ParseException;

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
