package resources.elements;

/**
 * Created by inaki.marquina on 29/06/2016.
 */
public class ContactForm {
    private String _email;
    private String _subjAuxText;

    /**
     * CONSTRUCTORES
     */
    public ContactForm() {
    }

    public ContactForm(String _email, String _subjAuxText) {
        this.set_email(_email);
        this.set_subjAuxText(_subjAuxText);
    }

    /**
     * GETTER / SETTER
     */
    public String get_email() {
        return _email;
    }

    public void set_email(String _email) {
        this._email = _email;
    }

    public String get_subjAuxText() {
        return _subjAuxText;
    }

    public void set_subjAuxText(String _subjAuxText) {
        this._subjAuxText = _subjAuxText;
    }
}
