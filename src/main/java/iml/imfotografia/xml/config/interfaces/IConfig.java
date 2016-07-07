package iml.imfotografia.xml.config.interfaces;

import iml.imfotografia.xml.config.structs.ContactForm;
import iml.imfotografia.xml.config.structs.Slogan;
import iml.imfotografia.xml.config.structs.Title;
import iml.imfotografia.xml.config.structs.Tracks;
import iml.imfotografia.xml.config.xborrar_XmlConfig;
import org.apache.log4j.Logger;

import java.util.LinkedHashMap;

/**
 * Created by imarquina on 6/7/16.
 */
public interface IConfig {
    String _title = "";
    String _infoText = "";
    String _keywords = "";

    /**
     * GETTER / SETTER
     */
    String get_infoText();
    void set_infoText(String infoText);
    String get_keywords();
    void set_keywords(String keywords);
    String get_title();
    void set_title(String title);
}
