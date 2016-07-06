package iml.imfotografia.xml.feed.collection;

import iml.imfotografia.xml.feed.element.*;

/**
 * Created by inaki.marquina on 06/07/2016.
 */
public class Image {
    public Title title;
    public Url url;
    public Link link;
    public PubDate pubDate;
    public Width width;
    public Height height;

    /**
     * CONSTRUCTOR
     */
    public Image() {
    }

    public Image(Height height, Link link, PubDate pubDate, Title title, Url url, Width width) {
        this.height = height;
        this.link = link;
        this.pubDate = pubDate;
        this.title = title;
        this.url = url;
        this.width = width;
    }
}
