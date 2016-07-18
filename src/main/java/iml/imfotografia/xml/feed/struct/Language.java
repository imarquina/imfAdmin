package iml.imfotografia.xml.feed.struct;

import iml.imfotografia.xml.feed.base.ElementBase;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Language extends ElementBase {
    final static Logger logger = Logger.getLogger(Language.class);

    /**
     * CONSTRUCTORS
     */
    public Language() {
        super();
    }

    public Language(String content) {
        super();
        this.set_nodeName("language");
        this.set_content(content);
    }

    /**
     *
     * @param document
     * @param parentNode
     */
    public void toXml(Document document, Element parentNode){
        logger.debug("Begin");

        Element languageNode = document.createElement(this.get_nodeName());
        languageNode.appendChild(document.createTextNode(this.get_content()));
        parentNode.appendChild(languageNode);

        logger.debug("End");
    }
}
