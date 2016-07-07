package iml.imfotografia.xml.feed.struct;

import java.text.ParseException;

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
    public Image() throws ParseException {
        Height height = new Height("60");
        this.addHeight(height);

        Link link = new Link("http://www.imarquina.es");
        this.addLink(link);

        PubDate pubDate = new PubDate("20120512");
        this.addPubDate(pubDate);

        Title title = new Title("IMFotografia");
        this.addTitle(title);

        Url url = new Url("http://www.imarquina.es/resources/logo_xs.png");
        this.addUrl(url);

        Width width = new Width("175");
        this.addWith(width);
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

    public void addPubDate(PubDate pubDate){
        this.pubDate = pubDate;
    }

    public void addWith(Width with){
        this.width = with;
    }

    public void addHeight(Height height){
        this.height = height;
    }
}
