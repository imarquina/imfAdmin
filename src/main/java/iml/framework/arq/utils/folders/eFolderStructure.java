package iml.framework.arq.utils.folders;

/**
 * Created by inaki.marquina on 03/03/2017.
 */
public enum eFolderStructure {
    SOURCE ("00"),
    TAJ ("01");

    private final String value;
    eFolderStructure(String value) {
        this.value = value;
    }
    public String value() { return value; }
}

