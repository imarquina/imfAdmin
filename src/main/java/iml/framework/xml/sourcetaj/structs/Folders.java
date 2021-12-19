package iml.framework.xml.sourcetaj.structs;

import iml.framework.xml.sourcetaj.base.ElementBase;
import iml.framework.xml.sourcetaj.interfaces.IElement;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * Created by inaki.marquina on 10/03/2017.
 */
public class Folders extends ElementBase implements IElement {
    final static Logger logger = LogManager.getLogger(Folders.class);

    public Elements elements;

    /**
     * CONSTRUCTORS
     */
    public Folders(){
        super();
        this.set_nodeName("folders");
        this.elements = new Elements();
    }
}
