package iml.imfotografia.xml.feed.collection;

import iml.imfotografia.xml.feed.struct.*;

/**
 * Created by inaki.marquina on 06/07/2016.
 */
public class Item {
    public Title title;
    public Link link;
    public Description description;
    public Category category;
    public PubDate pubDate;
    public Guid guid;

    /**
     * CONSTRUCTORS
     */
    public Item() {
        this.category = new Category();
        this.description = new Description();
        this.guid = new Guid();
        this.link = new Link();
        this.pubDate = new PubDate();
        this.title = new Title();
    }

    public void addCategory(Category category){
        this.category = category;
    }

    public void addDescription(Description description){
        this.description = description;
    }

    public void addGuid(Guid guid){
        this.guid = guid;
    }

    public void addLink(Link link){
        this.link = link;
    }

    public void addPubDate(PubDate pubDate){
        this.pubDate = pubDate;
    }

    public void addTitle(Title title){
        this.title = title;
    }
}
