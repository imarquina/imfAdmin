package iml.imfotografia.xml.config.structs;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by inaki.marquina on 11/08/2016.
 */
public class ExtractCollection {
    private Date lastElementUpdate;
    public Map<String, Object> elements;

    /**
     * CONSTRUCTORS
     */
    public ExtractCollection(){
        lastElementUpdate = null;
        elements = new LinkedHashMap<String, Object>();
    }

    /**
     * GETTER / SETTER
     */
    public Date getLastElementUpdate() {
        return lastElementUpdate;
    }

    public void setLastElementUpdate(Date lastElementUpdate) {
        if (this.lastElementUpdate == null)
            this.lastElementUpdate = lastElementUpdate;
        else if (this.lastElementUpdate.before(lastElementUpdate))
            this.lastElementUpdate = lastElementUpdate;
    }
}
