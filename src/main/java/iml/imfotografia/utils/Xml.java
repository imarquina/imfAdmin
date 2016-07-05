package iml.imfotografia.utils;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

/**
 * Created by imarquina on 4/7/16.
 */
public class Xml {
    /*
    public static String innerXml(Node node) {
        DOMImplementationLS lsImpl = (DOMImplementationLS)node.getOwnerDocument()
                .getImplementation().getFeature("LS", "3.0");
        LSSerializer lsSerializer = lsImpl.createLSSerializer();
        NodeList childNodes = node.getChildNodes();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < childNodes.getLength(); i++) {
            sb.append(lsSerializer.writeToString(childNodes.item(i)));
        }

        return sb.toString();
    }
    */

    /**
     *
     * @param node
     * @return
     */
    public static String innerXml(Node node) {
        String xmlString = "";
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            if (node.hasChildNodes()) {
                NodeList nodes = node.getChildNodes();

                for (int i = 0; i < nodes.getLength(); i++) {
                    Source source = new DOMSource(nodes.item(i));

                    StringWriter sw = new StringWriter();
                    StreamResult result = new StreamResult(sw);

                    transformer.transform(source, result);
                    xmlString += sw.toString();
                }
            }
            else xmlString = node.getTextContent();

        } catch (Exception ex) {
            ex.printStackTrace ();
        }

        return xmlString;
    }


}
