package iml.framework.arq.utils.folders;

/**
 * Created by inaki.marquina on 03/03/2017.
 */
public enum eFolderCondition {
    INCLUDED ("00"),
    EXCLUDED ("01"),
    BOTH ("02");

    private final String value;
    eFolderCondition(String value) {
        this.value = value;
    }
    public String value() { return value; }
}
