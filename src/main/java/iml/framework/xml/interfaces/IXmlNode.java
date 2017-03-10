package iml.framework.xml.interfaces;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Created by inaki.marquina on 18/07/2016.
 */
public interface IXmlNode {
    String _nodeName = "";

    void toXml(Document document, Element parentNode);
}
