package iml.framework.xml.sourcetaj.structs;

import iml.framework.xml.sourcetaj.base.ElementBase;
import iml.framework.xml.sourcetaj.interfaces.IElement;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by inaki.marquina on 10/03/2017.
 */
public class Elements extends ElementBase implements IElement {
    private Integer _iKey;

    final static Logger logger = LogManager.getLogger(Elements.class);

    public Map<String, Element> element;

    /**
     * CONSTRUCTORS
     */
    public Elements(){
        super();
        _iKey = 0;
        this.set_nodeName("elements");
        this.element = new LinkedHashMap<String, Element>();
    }

    /**
     * GETTER SETTER
     */
    public void addElement (Element element) {
        this.element.put((_iKey++).toString(), element);
    }
}
