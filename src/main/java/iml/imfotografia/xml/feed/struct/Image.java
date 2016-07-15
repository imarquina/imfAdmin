package iml.imfotografia.xml.feed.struct;

import iml.imfotografia.utils.Property;
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

    private Property properties;

    /**
     * CONSTRUCTOR
     */
    public Image() throws ParseException {
        properties = new Property("config.properties");

        this._nodeName = "image";

        Height height = new Height(properties.readProperty("iml.feed.image.logo.height"));
        this.addHeight(height);

        Width width = new Width(properties.readProperty("iml.feed.image.logo.width"));
        this.addWith(width);

        Link link = new Link(properties.readProperty("iml.url.root"));
        this.addLink(link);

        PubDate pubDate = new PubDate("20120512");
        this.addPubDate(pubDate);

        Title title = new Title(properties.readProperty("iml.name"));
        this.addTitle(title);

        Url url = new Url(properties.readProperty("iml.url.root") +
                properties.readProperty("iml.feed.image.logo.url"));
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
        Element imageNode = document.createElement(this.get_nodeName());

        this.title.toXml(document, imageNode);
        this.url.toXml(document, imageNode);
        this.link.toXml(document, imageNode);
        this.pubDate.toXml(document, imageNode);
        this.width.toXml(document, imageNode);
        this.height.toXml(document, imageNode);

        parentNode.appendChild(imageNode);
    }
}
