package iml.imfotografia.xml.data;

/**
 * Created by imarquina on 2/7/16.
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * Clase de ejemplo de procesado de XML mediante DOM.
 * Usa la implementación por defecto de Java (JAXP)
 *
 * @author Xela
 *
 */
public class ProcesaXMLJAXP {

    public static void main(String[] args) {
        try {
            // Implementación DOM por defecto de Java
            // Construimos nuestro DocumentBuilder
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            // Procesamos el fichero XML y obtenemos nuestro objeto Document
            Document doc = documentBuilder.parse(new InputSource(new FileInputStream("/ruta_a_fichero/fichero.xml")));

            // Obtenemos la etiqueta raiz
            Element elementRaiz = doc.getDocumentElement();
            // Iteramos sobre sus hijos
            NodeList hijos = elementRaiz.getChildNodes();
            for(int i=0;i<hijos.getLength();i++){
                Node nodo = hijos.item(i);
                if (nodo instanceof Element){
                    System.out.println(nodo.getNodeName());
                }
            }

            // Buscamos una etiqueta dentro del XML
            NodeList listaNodos = doc.getElementsByTagName("etiquetaHija");
            for(int i=0;i<listaNodos.getLength();i++){
                Node nodo = listaNodos.item(i);
                if (nodo instanceof Element){
                    System.out.println(nodo.getTextContent());
                }
            }

            // Buscamos una etiqueta dentro del XML con Namespaces
            NodeList listaNodosNS = doc.getElementsByTagNameNS("<a rel='nofollow' href='http://www.latascadexela.es'," +
                    "'etiquetaConNamespace' class='vglnk'><span>http</span><span>://</span><span>www</span><span>.</span>" +
                    "<span>latascadexela</span><span>.</span><span>es</span><span>","</span><span>etiquetaConNamespace</span></a>");
            for(int i=0;i<listaNodosNS.getLength();i++){
                Node nodo = listaNodosNS.item(i);
                if (nodo instanceof Element){
                    System.out.println(nodo.getAttributes().getNamedItem("descripcion").getTextContent());
                }
            }

            // Buscamos una etiqueta mediante XPath.
            // Implementación de XPath por defecto en Java
            Node etiquetaHija = (Node)(XPathFactory.newInstance()
                    .newXPath().evaluate("/etiquetaPrincipal/etiquetaHija", doc, XPathConstants.NODE));
            if (etiquetaHija!=null){
                System.out.println(etiquetaHija.getTextContent());
            }

            // Añadimos una nueva etiqueta al documento
            // Primero creamos la etiqueta (element)
            Element nuevaEtiqueta = doc.createElement("nuevaEtiqueta");
            // después se la añadimos como hija a una etiqueta ya existente
            etiquetaHija.appendChild(nuevaEtiqueta);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }
}