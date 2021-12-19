package iml.framework.xml.sourcetaj.structs;

import iml.framework.xml.sourcetaj.base.PathBase;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * Created by inaki.marquina on 10/03/2017.
 */
public class Deploy extends PathBase {
    final static Logger logger = LogManager.getLogger(Deploy.class);

    /**
     * CONSTRUCTORS
     */
    public Deploy(){
        super();
        this.set_nodeName("deploy");
    }
}
