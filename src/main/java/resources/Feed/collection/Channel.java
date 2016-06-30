package resources.Feed.collection;

import resources.Feed.element.*;

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

    public Channel() {
        title = new Title();
        link = new Link();
        description = new Description();
        language = new Language();
        pubDate = new PubDate();
        lastBuildDate = new LastBuildDate();
        docs = new Docs();
        managingEditor = new ManagingEditor();
        webMaster = new WebMaster();
    }
}
