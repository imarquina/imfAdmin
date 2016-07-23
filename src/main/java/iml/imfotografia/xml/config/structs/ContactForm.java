package iml.imfotografia.xml.config.structs;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class ContactForm {
    private String _nodeName = "";
    private String _email;
    private String _subjAuxText;

    final static Logger logger = Logger.getLogger(ContactForm.class);

    /**
     * CONSTANTS
     */
    private static final String ATTRIBUTE_EMAIL = "email";
    private static final String ATTRIBUTE_SUBJAUXTEXT = "subjauxtext";

    /**
     * CONSTRUCTORES
     */
    public ContactForm() {
        this._nodeName = "contactform";
        this._email = "";
        this._subjAuxText = "";
    }

    public ContactForm(String _email, String _subjAuxText) {
        this();
        this.set_email(_email);
        this.set_subjAuxText(_subjAuxText);
    }

    /**
     * GETTER / SETTER
     */
    public String get_email() {
        return this._email;
    }

    public void set_email(String email) {
        this._email = email;
    }

    public String get_subjAuxText() {
        return this._subjAuxText;
    }

    public void set_subjAuxText(String subjAuxText) {
        this._subjAuxText = subjAuxText;
    }

    public String get_nodeName() {
        return this._nodeName;
    }

    /**
     *
     * @param node
     * @return
     */
    public ContactForm fromXml(Node node) {
        logger.debug("Begin");
        ContactForm contactForm = new ContactForm();

        // get attributes names and values
        NamedNodeMap nodeMap = node.getAttributes();
        for (int i = 0; i < nodeMap.getLength(); i++) {
            Node tempNode = nodeMap.item(i);
            String sAttrName = tempNode.getNodeName();
            if (!sAttrName.equals(null)) sAttrName = sAttrName.trim().toUpperCase();
            String sAttrValue = tempNode.getNodeValue();
            if (!sAttrValue.equals(null)) sAttrValue = sAttrValue.trim();

            logger.info("    Attr name : " + sAttrName + "; Value = " + sAttrValue);

            logger.debug("set ContactForm property " + sAttrName + ":" + sAttrValue);
        }

        logger.debug("End");
        return contactForm;
    }

    /**
     *
     * @param document
     * @param parentNode
     */
    public void toXml(Document document, Element parentNode){
        logger.debug("Begin");

        parentNode.setAttribute(ATTRIBUTE_EMAIL, this.get_email());
        parentNode.setAttribute(ATTRIBUTE_SUBJAUXTEXT, this.get_subjAuxText());

        logger.debug("End");
    }
}
