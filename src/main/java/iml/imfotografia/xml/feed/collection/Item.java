package iml.imfotografia.xml.feed.collection;

import iml.imfotografia.xml.feed.element.*;

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
    }

    public Item(Category category, Description description, Guid guid, Link link, PubDate pubDate, Title title) {
        this.category = category;
        this.description = description;
        this.guid = guid;
        this.link = link;
        this.pubDate = pubDate;
        this.title = title;
    }
}
