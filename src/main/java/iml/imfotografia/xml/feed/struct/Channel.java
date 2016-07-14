package iml.imfotografia.xml.feed.struct;

import iml.imfotografia.utils.Property;
import iml.imfotografia.xml.config.structs.Config;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.text.ParseException;
import java.util.LinkedHashMap;

public class Channel {
    private Integer _iKey;
    public Title title;
    public Link link;
    public Description description;
    public Language language;
    public PubDate pubDate;
    public LastBuildDate lastBuildDate;
    public Docs docs;
    public ManagingEditor managingEditor;
    public WebMaster webMaster;
    public LinkedHashMap<Integer, Object> elements;

    private Property properties;

    /**
     * CONSTRUCTORS
     */
    public Channel() {
        properties = new Property("config.properties");

        _iKey = 0;

        this.title = new Title();
        this.link = new Link();
        this.description = new Description();
        this.language = new Language();
        this.pubDate = new PubDate();
        this.lastBuildDate = new LastBuildDate();
        this.docs = new Docs();
        this.managingEditor = new ManagingEditor();
        this.webMaster = new WebMaster();

        this.elements = new LinkedHashMap<Integer, Object>();
    }

    public Channel(Config xmlConfig) throws ParseException {
        this();

        Title title = new Title(xmlConfig.get_title());
        this.addTitle(title);

        Link link = new Link(properties.readProperty("iml.url.root"));
        this.addLink(link);

        Description description = new Description(xmlConfig.get_infoText());
        this.addDescription(description);

        Language language = new Language(properties.readProperty("iml.feed.channel.language"));
        this.addLanguage(language);

        PubDate pubDate = new PubDate(properties.readProperty("iml.feed.channel.pubDate"));
        this.addPubDate(pubDate);

        LastBuildDate lastBuildDate = new LastBuildDate("20181231");
        this.addLastBuildDate(lastBuildDate);

        Docs docs = new Docs(properties.readProperty("iml.feed.channel.docs"));
        this.addDocs(docs);

        ManagingEditor managingEditor = new ManagingEditor(properties.readProperty("iml.email"));
        this.addManagingEditor(managingEditor);

        WebMaster webMaster = new WebMaster(properties.readProperty("iml.email"));
        this.addWebMaster(webMaster);
    }

    /**
     *  PUBLIC METHODS
     */
    public void addTitle(Title title) {
        this.title = title;
    }

    public void addLink(Link link) {
        this.link = link;
    }

    public void addDescription(Description description) {
        this.description = description;
    }

    public void addLanguage(Language language) {
        this.language = language;
    }

    public void addPubDate(PubDate pubDate){
        this.pubDate = pubDate;
    }

    public void addLastBuildDate(LastBuildDate lastBuildDate) {
        this.lastBuildDate = lastBuildDate;
    }

    public void addDocs(Docs docs){
        this.docs = docs;
    }

    public void addManagingEditor(ManagingEditor managingEditor) {
        this.managingEditor = managingEditor;
    }

    public void addWebMaster(WebMaster webMaster) {
        this.webMaster = webMaster;
    }

    public void addImage (Image image) {
        this.elements.put(_iKey++, image);
    }

    public void addItem (Item item) {
        this.elements.put(_iKey++, item);
    }

    public void toXml(Document document, Element parentNode){
        Element chanelNode = document.createElement("channel");

        this.title.toXml(document, chanelNode);
        this.link.toXml(document, chanelNode);
        this.description.toXml(document, chanelNode);
        this.language.toXml(document, chanelNode);
        this.pubDate.toXml(document, chanelNode);
        this.lastBuildDate.toXml(document, chanelNode);
        this.docs.toXml(document, chanelNode);
        this.managingEditor.toXml(document, chanelNode);
        this.webMaster.toXml(document, chanelNode);

        Image image = (Image)this.elements.get(0);
        image.toXml(document, chanelNode);

        //bucle para los item desde 1 hasta size() - 1

        parentNode.appendChild(chanelNode);
    }
}
