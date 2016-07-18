package iml.imfotografia.xml.feed.struct;

import iml.imfotografia.xml.feed.base.ElementStringBase;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ManagingEditor extends ElementStringBase {
    final static Logger logger = Logger.getLogger(ManagingEditor.class);

    /**
     * CONSTRUCTORS
     */
    public ManagingEditor() {
        super();
    }

    public ManagingEditor(String content) {
        super(content);
        this.set_nodeName("managingEditor");
    }

    /**
     *
     * @param document
     * @param parentNode
     */
    public void toXml(Document document, Element parentNode) {
        logger.debug("Begin");

        Element managingEditorNode = document.createElement(this.get_nodeName());
        managingEditorNode.appendChild(document.createTextNode(this.get_content()));
        parentNode.appendChild(managingEditorNode);

        logger.debug("End");
    }
}
