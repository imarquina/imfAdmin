package iml.imfotografia.xml.feed.struct;

import java.util.LinkedHashMap;

public class Channel {
    public Title title;
    public Link link;
    public Description description;
    public Language language;
    public PubDate pubDate;
    public LastBuildDate lastBuildDate;
    public Docs docs;
    public ManagingEditor managingEditor;
    public WebMaster webMaster;
    public LinkedHashMap<String, Object> elements;

    public Channel() {
        this.title = new Title();
        this.link = new Link();
        this.description = new Description();
        this.language = new Language();
        this.pubDate = new PubDate();
        this.lastBuildDate = new LastBuildDate();
        this.docs = new Docs();
        this.managingEditor = new ManagingEditor();
        this.webMaster = new WebMaster();

        this.elements = new LinkedHashMap<String, Object>();
    }

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
}
