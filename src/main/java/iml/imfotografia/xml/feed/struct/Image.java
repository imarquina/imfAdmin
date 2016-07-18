package iml.imfotografia.xml.feed.struct;

import iml.imfotografia.PropConfig;
import iml.imfotografia.utils.Property;
import iml.imfotografia.xml.feed.XmlFeed;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.text.ParseException;

/**
 * Created by inaki.marquina on 06/07/2016.
 */
public class Image {
    private String _nodeName;

    public Title title;
    public Url url;
    public Link link;
    public PubDate pubDate;
    public Width width;
    public Height height;

    final static Logger logger = Logger.getLogger(Image.class);

    /**
     * CONSTRUCTOR
     */
    public Image() throws ParseException {
        this._nodeName = "image";

        Height height = new Height(PropConfig.readProperty("iml.feed.image.logo.height"));
        this.addHeight(height);

        Width width = new Width(PropConfig.readProperty("iml.feed.image.logo.width"));
        this.addWith(width);

        Link link = new Link(PropConfig.readProperty("iml.url.root"));
        this.addLink(link);

        PubDate pubDate = new PubDate("20120512");
        this.addPubDate(pubDate);

        Title title = new Title(PropConfig.readProperty("iml.name"));
        this.addTitle(title);

        Url url = new Url(PropConfig.readProperty("iml.url.root") +
                PropConfig.readProperty("iml.feed.image.logo.url"));
        this.addUrl(url);
    }

    /**
     * GETTER / SETTER
     */
    public String get_nodeName() {
        return _nodeName;
    }


    public void addTitle(Title title){
        this.title = title;
    }

    public void addUrl(Url url) {
        this.url = url;
    }

    public void  addLink(Link link) {
        this.link = link;
    }

    public void addPubDate(PubDate pubDate){
        this.pubDate = pubDate;
    }

    public void addWith(Width with){
        this.width = with;
    }

    public void addHeight(Height height){
        this.height = height;
    }

    /**
     *
     * @param document
     * @param parentNode
     */
    public void toXml(Document document, Element parentNode){
        logger.debug("Begin");

        Element imageNode = document.createElement(this.get_nodeName());

        this.title.toXml(document, imageNode);
        this.url.toXml(document, imageNode);
        this.link.toXml(document, imageNode);
        this.pubDate.toXml(document, imageNode);
        this.width.toXml(document, imageNode);
        this.height.toXml(document, imageNode);

        parentNode.appendChild(imageNode);

        logger.debug("End");
    }
}
