package iml.imfotografia.xml;

/**
 * Created by imarquina on 17/7/16.
 */
final public class Propertyx {
    public final static String IML_PROPSFILE = "config.xml.properties";

    private static iml.imfotografia.arq.utils.Property _prop;

    public static String readProperty(String key){
        if (_prop == null){
            _prop = new iml.imfotografia.arq.utils.Property(IML_PROPSFILE);
        }
        return _prop.readProperty(key);
    }
}
