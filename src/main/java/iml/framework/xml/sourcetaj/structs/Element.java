package iml.framework.xml.sourcetaj.structs;

import iml.framework.xml.sourcetaj.base.ElementBase;
import iml.framework.xml.sourcetaj.interfaces.IElement;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * Created by inaki.marquina on 10/03/2017.
 */
public class Element extends ElementBase implements IElement {
    public Name name;
    public Source source;
    public Deploy deploy;

    final static Logger logger = LogManager.getLogger(Element.class);

    /**
     * CONSTRUCTORS
     */
    public Element() {
        super();

        this.set_nodeName("element");
        this.name = new Name();
        this.source = new Source();
        this.deploy = new Deploy();
    }
}
