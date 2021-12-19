package iml.framework.xml.sourcetaj.structs;

import iml.framework.xml.sourcetaj.base.PathBase;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * Created by inaki.marquina on 10/03/2017.
 */
public class Exclude  extends PathBase {
    final static Logger logger = LogManager.getLogger(Exclude.class);

    /**
     * CONSTRUCTORS
     */
    public Exclude(){
        super();
        this.set_nodeName("exclude");
    }
}
