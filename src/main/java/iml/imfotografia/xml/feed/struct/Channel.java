package iml.imfotografia.xml.feed.struct;

import iml.imfotografia.xml.config.structs.Config;

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

    /**
     * CONSTRUCTORS
     */
    public Channel() {
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

        Link link = new Link("http://www.imarquina.es/");
        this.addLink(link);

        Description description = new Description(xmlConfig.get_infoText());
        this.addDescription(description);

        Language language = new Language("es-ES");
        this.addLanguage(language);

        PubDate pubDate = new PubDate("20141028");
        this.addPubDate(pubDate);

        LastBuildDate lastBuildDate = new LastBuildDate("20181231");
        this.addLastBuildDate(lastBuildDate);

        Docs docs = new Docs("http://blogs.law.harvard.edu/tech/rss");
        this.addDocs(docs);

        ManagingEditor managingEditor = new ManagingEditor("imarquina@gmail.com");
        this.addManagingEditor(managingEditor);

        WebMaster webMaster = new WebMaster("imarquina@gmail.com");
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
}
