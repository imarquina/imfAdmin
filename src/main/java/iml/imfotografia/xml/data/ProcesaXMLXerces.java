package iml.imfotografia.xml.data;

import java.io.CharArrayWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Writer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import com.sun.org.apache.xml.internal.serialize.LineSeparator;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import com.sun.org.apache.xpath.internal.XPathAPI;

/**
 * Clase de ejemplo de procesado de XML mediante DOM.
 * Usa la implementación de Xerces
 *
 * @author Xela
 *
 */
public class ProcesaXMLXerces {
    public static void main(String[] args) {
        try {
            // Implementación DOM de Xerces
            // Creamos el parseador
            DOMParser parser = new DOMParser();
            // Procesamos el fichero XML
            parser.parse(new InputSource(new FileInputStream("/ruta_a_fichero/fichero.xml")));
            // Obtenemos el objeto Document
            Document doc = parser.getDocument();

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
            NodeList listaNodosNS = doc.getElementsByTagNameNS("<a rel='nofollow' " +
                    "href='http://www.latascadexela.es','etiquetaConNamespace' class='vglnk'>" +
                    "<span>http</span><span>://</span><span>www</span><span>.</span><span>latascadexela</span>" +
                    "<span>.</span><span>es</span><span>","</span><span>etiquetaConNamespace</span></a>");
            for(int i=0;i<listaNodosNS.getLength();i++){
                Node nodo = listaNodosNS.item(i);
                if (nodo instanceof Element){
                    System.out.println(nodo.getAttributes().getNamedItem("descripcion").getTextContent());
                }
            }

            // Buscamos una etiqueta mediante XPath.
            // Implementación de XPath de Xerces
            Node etiquetaHija = XPathAPI.selectSingleNode(doc, "/etiquetaPrincipal/etiquetaHija");
            if (etiquetaHija!=null){
                System.out.println(etiquetaHija.getTextContent());
            }

            // Añadimos una nueva etiqueta al documento
            // Primero creamos la etiqueta (element)
            Element nuevaEtiqueta = doc.createElement("nuevaEtiqueta");
            // Añadimos atributos
            nuevaEtiqueta.setAttribute("atributoNuevo", "Es un nuevo atributo");
            // Añadimos contenido
            nuevaEtiqueta.setTextContent("Contenido dentro de la nueva etiqueta");
            // después se la añadimos como hija a una etiqueta ya existente
            etiquetaHija.appendChild(nuevaEtiqueta);

            // Vamos a convertir el arbol DOM en un String
            // Definimos el formato de salida: encoding, identación, separador de línea,...
            // Pasamos doc como argumento para tener un formato de partida
            OutputFormat format = new OutputFormat(doc);
            format.setLineSeparator(LineSeparator.Unix);
            format.setIndenting(true);
            format.setLineWidth(0);
            format.setPreserveSpace(false);
            // Definimos donde vamos a escribir. Puede ser cualquier OutputStream o un Writer
            CharArrayWriter salidaXML = new CharArrayWriter();
            // Serializamos el arbol DOM
            XMLSerializer serializer = new XMLSerializer((Writer)salidaXML,format);
            serializer.asDOMSerializer();
            serializer.serialize(doc);
            // Ya tenemos el XML serializado en el objeto salidaXML
            System.out.println(salidaXML.toString());


            // Vamos a crear un XML desde cero
            // En este caso usamos DocumentBuilder
            DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            // Creamos el documento XML
            Document docNuevo = docBuilder.newDocument();
            // Creamos la etiqueta raiz
            Element raiz = docNuevo.createElement("etiquetaRaiz");
            docNuevo.appendChild(raiz);
            // Creamos una etiqueta hija de la etiqueta raiz
            Element hijaRaiz = docNuevo.createElement("etiquetaHijaRaiz");
            raiz.appendChild(hijaRaiz);

            // Incluso podemos copiar parte de otro documeto XML en este nuevo
            // Vamos a copiar todo el XML parseado en este nuevo
            // Primero obtenemos la etiqueta Raiz del XML parseado al principio
            Element etiquetaRaizACopiar = doc.getDocumentElement();
            // Luego la copiamos bajo nuestra etiqueta hijaRaiz, por ejemplo
            Node etiquetaRaizCopiada = docNuevo.importNode(etiquetaRaizACopiar, true); // El segundo atributo indica si queremos copiar los hijos
            // Ya tenemos una copia de la etiqueta en nuestro document. Ahora la situamos bajo etiquetaHija
            hijaRaiz.appendChild(etiquetaRaizCopiada);

            // ImportNode hace una copia, dejando el xml original intacto
            // AdoptNode mueve, es decir, elimina los elementos del arbol original y los pega en el nuevo-
            // Este método siempre es recursivo.
            // Por ejemplo, adoptemos la etiquetaHija.
            Node etiquetaHijaAdoptada = docNuevo.adoptNode(etiquetaHija);
            // Ya tenemos la etiquetaHija en nuestro document y se ha eliminado del anterior.
            // La situamos bajo la etiqueta raiz
            raiz.appendChild(etiquetaHijaAdoptada);

            // Veamos los dos XML el nuevo y cómo ha quedado el parseado.
            // Definimos donde vamos a escribir. Puede ser cualquier OutputStream o un Writer
            CharArrayWriter salidaXMLParseado = new CharArrayWriter();
            // Serializamos el arbol DOM
            XMLSerializer serializerXMLParseado = new XMLSerializer((Writer)salidaXMLParseado,format);
            serializerXMLParseado.asDOMSerializer();
            serializerXMLParseado.serialize(doc);
            // Ya tenemos el XML serializado en el objeto salidaXMLParseado
            System.out.println("XML parseado: \n"+salidaXMLParseado.toString());

            // Definimos donde vamos a escribir. Puede ser cualquier OutputStream o un Writer
            CharArrayWriter salidaXMLNuevo = new CharArrayWriter();
            // Serializamos el arbol DOM
            XMLSerializer serializerXMLNuevo = new XMLSerializer((Writer)salidaXMLNuevo,format);
            serializerXMLNuevo.asDOMSerializer();
            serializerXMLNuevo.serialize(docNuevo);
            // Ya tenemos el XML serializado en el objeto salidaXMLNuevo
            System.out.println("XML nuevo: \n"+salidaXMLNuevo.toString());


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

    }

}
