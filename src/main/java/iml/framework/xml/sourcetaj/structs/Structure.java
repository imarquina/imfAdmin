package iml.framework.xml.sourcetaj.structs;

import iml.framework.xml.sourcetaj.base.ElementBase;
import iml.framework.xml.sourcetaj.interfaces.IElement;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * Created by inaki.marquina on 10/03/2017.
 */
public class Structure extends ElementBase implements IElement {
    final static Logger logger = LogManager.getLogger(Structure.class);

    public Folders folders;

    /**
     * CONSTRUCTORS
     */
    public Structure(){
        super();
        this.set_nodeName("structure");
        this.folders = new Folders();
    }
}
