package iml.imfotografia.xml.feed.struct;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Description {
    private String _content;

    /**
     * CONSTRUCTORS
     */
    public Description() {
    }

    public Description(String content) {
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

    public void toXml(Document document, Element parentNode){
        Element descriptionNode = document.createElement("description");
        descriptionNode.appendChild(document.createTextNode(this.get_content()));
        parentNode.appendChild(descriptionNode);
    }
}
