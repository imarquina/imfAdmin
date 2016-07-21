package iml.imfotografia.xml.config.structs;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by imarquina on 2/7/16.
 */
public class Tracks {
    private Integer _iKey;
    private String _nodeName = "";
    private String _name;
    public Map<Integer, Track> track;

    final static Logger logger = Logger.getLogger(Tracks.class);

    /**
     * CONSTRUCTORS
     */
    public Tracks() {
        this._iKey = 0;
        this._nodeName = "tracks";
        this._name = "";
        this.track = new LinkedHashMap<Integer, Track>();
    }

    public Tracks(String name) {
        this();
        this.set_name(name);
    }

    /**
     * GETTER / SETTER
     */
    public String get_name() {
        return this._name;
    }

    public void set_name(String name) {
        this._name = name;
    }

    public String get_nodeName() {
        return this._nodeName;
    }

    /**
     *s
     * @return
     */
    public void addTrack(Track track){
        this.track.put(_iKey++, track);
    }

    /**
     *
     * @param document
     * @param parentNode
     */
    public void toXml(Document document, Element parentNode){
        logger.debug("Begin");

        parentNode.setAttribute("audio", this.get_name());

        for (Map.Entry<Integer, Track> entry1 : this.track.entrySet()) {
            Integer key = entry1.getKey();
            Track value = entry1.getValue();

            Element trackNode = document.createElement(value.get_nodeName());
            trackNode.setAttribute("src", value.get_src());
            trackNode.setAttribute("artist", value.get_artist());
            trackNode.setAttribute("name", value.get_name());

            parentNode.appendChild(trackNode);
        }

        logger.debug("End");
    }
}
