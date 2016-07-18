package iml.imfotografia.arq.utils;

import iml.imfotografia.arq.extensions.StringWriter;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

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
        return innerXml(node, false);
    }

    /**
     *
     * @param node
     * @param includeItself
     * @return
     */
    public static String innerXml(Node node, Boolean includeItself) {
        String xmlString = "";
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            if (includeItself) {
                Source source = new DOMSource(node);

                StringWriter sw = new StringWriter();
                StreamResult result = new StreamResult(sw);

                transformer.transform(source, result);
                xmlString = sw.toStringNormalized();
            } else {
                if (node.hasChildNodes()) {
                    NodeList nodes = node.getChildNodes();

                    for (int i = 0; i < nodes.getLength(); i++) {
                        //if (!isBlankText(nodes.item(i))) {
                            Source source = new DOMSource(nodes.item(i));

                            StringWriter sw = new StringWriter();
                            StreamResult result = new StreamResult(sw);

                            transformer.transform(source, result);
                            xmlString += sw.toStringNormalized();
                        //}
                    }
                } else xmlString = node.getTextContent();
            }

        } catch (Exception ex) {
            ex.printStackTrace ();
        }

        return xmlString;
    }

    /**
     *
     * @param node
     * @return
     */
    private static Boolean isBlankText(Node node) {
        if (node instanceof org.w3c.dom.Text) {
            String value = node.getNodeValue().trim();
            if (value.equals("") ) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param document
     * @throws XPathExpressionException
     */
    public static void normalize(Document document) throws XPathExpressionException {
        XPathFactory xpathFactory = XPathFactory.newInstance();
        // XPath to find empty text nodes.
        XPathExpression xpathExp = xpathFactory.newXPath().compile("//text()[normalize-space(.) = '']");
        NodeList emptyTextNodes = (NodeList)xpathExp.evaluate(document, XPathConstants.NODESET);

        // Remove each empty text node from document.
        for (int i = 0; i < emptyTextNodes.getLength(); i++) {
            Node emptyTextNode = emptyTextNodes.item(i);
            emptyTextNode.getParentNode().removeChild(emptyTextNode);
        }
    }
}
