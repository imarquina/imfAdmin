package iml.framework.xml.website;

/**
 * Created by imarquina on 17/7/16.
 */
final public class Property {
    public final static String IML_PROPSFILE = "config.website.properties";

    private static iml.framework.arq.utils.Property _prop;

    public static String readProperty(String key){
        if (_prop == null){
            _prop = new iml.framework.arq.utils.Property(IML_PROPSFILE);
        }
        return _prop.readProperty(key);
    }
}
