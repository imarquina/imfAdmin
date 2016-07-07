package iml.imfotografia.xml.feed.struct;

import iml.imfotografia.utils.Crypto;
import iml.imfotografia.xml.config.structs.Gallery;
import iml.imfotografia.xml.element.interfaces.IElement;

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
    public Item(){
        super();
    }

    public Item(IElement element, Gallery gallery) {
        Title title = new Title(element.get_caption());
        this.addTitle(title);

        Link link = new Link(Crypto.getMD5(gallery.get_name()));
        this.addLink(link);

        Description description = new Description(element.get_infoText());
        this.addDescription(description);

        Category category = new Category(gallery.get_name());
        this.addCategory(category);

        PubDate pubDate = new PubDate(element.get_dPublic());
        this.addPubDate(pubDate);

        Guid guid = new Guid("");
        this.addGuid(guid);
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
