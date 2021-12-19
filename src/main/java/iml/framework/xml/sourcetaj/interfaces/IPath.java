package iml.framework.xml.sourcetaj.interfaces;

import iml.framework.xml.sourcetaj.structs.Exclude;
import iml.framework.xml.sourcetaj.structs.Path;

/**
 * Created by inaki.marquina on 10/03/2017.
 */
public interface IPath {
    Path _path = new Path();
    Exclude _exclude = new Exclude();

    public Path get_path();
    public void set_path(Path path);
    public Exclude get_exclude();
    public void set_exclude(Exclude exclude);
}
