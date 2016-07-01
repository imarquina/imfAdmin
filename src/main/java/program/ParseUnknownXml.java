package program;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * Created by inaki.marquina on 01/07/2016.
 */
public class ParseUnknownXml {
    public static void openXml() throws ParserConfigurationException, SAXException, IOException
    {
        //Get Document Builder
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        //Build Document
        Document document = builder.parse(new File("./resources/xml/in/photos.xml"));

        //Normalize the XML Structure; It's just too important !!
        document.getDocumentElement().normalize();

        //Here comes the root node
        Element root = document.getDocumentElement();
        System.out.println(root.getNodeName());

        //Get all photos
        NodeList nPhotos = document.getElementsByTagName("photos");
        openChildNodes(nPhotos);
        NodeList nVideos = document.getElementsByTagName("videos");
        openChildNodes(nVideos);
        System.out.println("============================");
    }

    //This function is called recursively
    private static void openChildNodes(NodeList nList)
    {
        for (int temp = 0; temp < nList.getLength(); temp++)
        {
            Node node = nList.item(temp);
            if (node.getNodeType() == Node.ELEMENT_NODE)
            {
                System.out.println("Node Name = " + node.getNodeName() + "; Value = " + node.getTextContent());
                //Check all attributes
                if (node.hasAttributes()) {
                    getAttributes(node);
                }
                if (node.hasChildNodes()) {
                    openChildNodes(node.getChildNodes());
                }
            }
        }
    }

    private static void getAttributes(Node node)
    {
        // get attributes names and values
        NamedNodeMap nodeMap = node.getAttributes();
        for (int i = 0; i < nodeMap.getLength(); i++)
        {
            Node tempNode = nodeMap.item(i);
            System.out.println("    Attr name : " + tempNode.getNodeName()+ "; Value = " + tempNode.getNodeValue());
        }
    }

}
