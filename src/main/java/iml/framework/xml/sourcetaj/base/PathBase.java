package iml.framework.xml.sourcetaj.base;

import iml.framework.xml.sourcetaj.interfaces.IPath;
import iml.framework.xml.sourcetaj.structs.Exclude;
import iml.framework.xml.sourcetaj.structs.Path;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * Created by inaki.marquina on 10/03/2017.
 */
public class PathBase implements IPath {
    private String _nodeName = "";
    private Path _path;
    private Exclude _exclude;

    final static Logger logger = LogManager.getLogger(PathBase.class);

    /**
     * CONSTRUCTORS
     */
    public PathBase(){
        this._path = new Path();
        this._exclude = new Exclude();
    }

    /**
     * GETTER SETTER
     */
    public String get_nodeName() {
        return _nodeName;
    }

    protected void set_nodeName(String _nodeName) {
        this._nodeName = _nodeName;
    }

    public Path get_path() {
        return this._path;
    }

    public void set_path(Path path) {
        this._path = path;
    }

    public Exclude get_exclude() {
        return this._exclude;
    }

    public void set_exclude(Exclude exclude) {
        this._exclude = exclude;
    }
}
