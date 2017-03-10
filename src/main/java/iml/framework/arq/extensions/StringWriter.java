package iml.framework.arq.extensions;

/**
 * Created by inaki.marquina on 05/07/2016.
 */
public class StringWriter extends java.io.StringWriter {
    public String toStringNormalized() {
        String sReturn = super.toString();

        sReturn = sReturn.replace("\r\n", "");

        sReturn = sReturn.replace("\r", "");
        sReturn = sReturn.replace("\n", "");

        sReturn = sReturn.replace("\t", "");
        return sReturn;
    }
}
