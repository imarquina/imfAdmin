package iml.imfotografia;

import iml.imfotografia.utils.Property;

/**
 * Created by imarquina on 17/7/16.
 */
final public class PropConfig {
    public final static String IML_PROPSFILE = "config.properties";

    private static Property _prop;

    public static String readProperty(String key){
        if (_prop == null){
            _prop = new Property(IML_PROPSFILE);
        }
        return _prop.readProperty(key);
    }
}
