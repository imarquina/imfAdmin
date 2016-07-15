package iml.imfotografia.xml.feed.struct;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Language {
    private String _nodeName;
    private String _content;

    /**
     * CONSTRUCTORS
     */
    public Language() {
        this._nodeName = "language";
        this._content = "";
    }

    public Language(String content) {
        this();
        this.set_content(content);
    }

    /**
     * GETTER / SETTER
     */
    public String get_content() {
        return this._content;
    }

    public void set_content(String content) {
        this._content = content;
    }

    public String get_nodeName() {
        return _nodeName;
    }

    /**
     *
     * @param document
     * @param parentNode
     */
    public void toXml(Document document, Element parentNode){
        Element languageNode = document.createElement(this.get_nodeName());
        languageNode.appendChild(document.createTextNode(this.get_content()));
        parentNode.appendChild(languageNode);
    }
}
