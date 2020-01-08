package iml.framework.xml.website.feed.struct;

import iml.framework.xml.website.feed.base.ElementStringBase;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Language extends ElementStringBase {
    final static Logger logger = LogManager.getLogger(Language.class);

    /**
     * CONSTRUCTORS
     */
    public Language() {
        super();
    }

    public Language(String content) {
        super(content);
        this.set_nodeName("language");
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
