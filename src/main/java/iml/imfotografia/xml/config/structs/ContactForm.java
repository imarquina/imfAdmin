package iml.imfotografia.xml.config.structs;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ContactForm {
    private String _nodeName = "";
    private String _email;
    private String _subjAuxText;

    final static Logger logger = Logger.getLogger(ContactForm.class);

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
     * @param document
     * @param parentNode
     */
    public void toXml(Document document, Element parentNode){
        logger.debug("Begin");

        parentNode.setAttribute("email", this.get_email());
        parentNode.setAttribute("subjauxtext", this.get_subjAuxText());

        logger.debug("End");
    }
}
