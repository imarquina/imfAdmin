package resources.Data.element;

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
}
