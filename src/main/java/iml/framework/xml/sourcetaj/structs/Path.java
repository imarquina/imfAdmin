package iml.framework.xml.sourcetaj.structs;

import iml.framework.xml.sourcetaj.base.ElementBase;
import iml.framework.xml.sourcetaj.interfaces.IElement;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * Created by inaki.marquina on 10/03/2017.
 */
public class Path extends ElementBase implements IElement {
    final static Logger logger = LogManager.getLogger(Path.class);

    /**
     * CONSTRUCTORS
     */
    public Path(){
        super();
        this.set_nodeName("path");
    }

    public Path(String content){
        this();
        this.set_content(content);
    }
}
