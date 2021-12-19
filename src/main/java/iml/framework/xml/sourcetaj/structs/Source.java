package iml.framework.xml.sourcetaj.structs;

import iml.framework.xml.sourcetaj.base.PathBase;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * Created by inaki.marquina on 10/03/2017.
 */
public class Source extends PathBase {
    final static Logger logger = LogManager.getLogger(Source.class);

    /**
     * CONSTRUCTORS
     */
    public Source(){
        super();
        this.set_nodeName("source");
    }
}
