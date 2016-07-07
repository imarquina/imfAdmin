package iml.imfotografia.xml.feed.struct;

import iml.imfotografia.xml.feed.struct.*;

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
        this.height = new Height();
        this.link = new Link();
        this.pubDate = new PubDate();
        this.title = new Title();
        this.url = new Url();
        this.width = new Width();
    }

    public void addTitle(Title title){
        this.title = new Title();
    }

    public void addUrl(Url url) {
        this.url = url;
    }

    public void  addLink(Link link) {
        this.link = link;
    }

    public void PubDate(PubDate pubDate){
        this.pubDate = pubDate;
    }

    public void With(Width with){
        this.width = with;
    }

    public void Height(Height height){
        this.height = height;
    }
}
