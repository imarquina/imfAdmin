package iml.framework.arq.utils.folders;

/**
 * Created by inaki.marquina on 07/03/2017.
 */
public enum eServer {
    TOM ("00"),
    WEB ("01");

    private final String value;
    eServer(String value) {
        this.value = value;
    }
    public String value() { return value; }
}
