package iml.imfotografia.xml.feed.struct;

import iml.imfotografia.PropConfig;
import iml.imfotografia.utils.Crypto;
import iml.imfotografia.utils.Property;
import iml.imfotografia.xml.config.structs.Gallery;
import iml.imfotografia.xml.element.interfaces.IElement;
import iml.imfotografia.xml.feed.XmlFeed;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import static iml.imfotografia.utils.Text.htmlReplace;

/**
 * Created by inaki.marquina on 06/07/2016.
 */
public class Item {
    private String _nodeName;

    public Title title;
    public Link link;
    public Description description;
    public Category category;
    public PubDate pubDate;
    public Guid guid;

    final static Logger logger = Logger.getLogger(Item.class);

    /**
     * CONSTRUCTORS
     */
    public Item(){
        super();
    }

    public Item(IElement element, Gallery gallery, Integer iImage) {
        this._nodeName = "item";

        Title title = new Title(element.get_caption());
        this.addTitle(title);

        Link link = new Link(PropConfig.readProperty("iml.url.root") +
                PropConfig.readProperty("iml.feed.item.link") +
                Crypto.getMD5(htmlReplace(gallery.get_name())) + "&photo=" + iImage);
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

    /**
     * GETTER / SETTER
     */
    public String get_nodeName() {
        return _nodeName;
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

    /**
     *
     * @param document
     * @param parentNode
     */
    public void toXml(Document document, Element parentNode){
        logger.debug("Begin");

        Element itemNode = document.createElement(this.get_nodeName());

        this.title.toXml(document, itemNode);
        this.link.toXml(document, itemNode);
        this.description.toXml(document, itemNode);
        this.category.toXml(document, itemNode);
        this.pubDate.toXml(document, itemNode);
        this.guid.toXml(document, itemNode);

        parentNode.appendChild(itemNode);

        logger.debug("End");
    }
}
