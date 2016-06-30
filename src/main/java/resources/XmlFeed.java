package resources;

/**
 * Created by imarquina on 29/6/16.
 */
public class XmlFeed {
    public Rss rss = new Rss();
}

class Rss {
    String _version;
    Channel chanel = new Channel();

    /**
     * CONSTRUCTORS
     */
    Rss() {

    }

    Rss(String _version) {
        this._version = _version;
    }

    /**
     * GETTER / SETTER
     */
    String get_version() {
        return _version;
    }

    void set_version(String _version) {
        this._version = _version;
    }
}

class Channel {
    Title title = new Title();
    Link link = new Link();
    Description description = new Description();
    Language language = new Language();
    PubDate pubDate = new PubDate();
    LastBuildDate lastBuildDate = new LastBuildDate();
    Docs docs = new Docs();
    ManagingEditor managingEditor = new ManagingEditor();
    WebMaster webMaster = new WebMaster();
}

class Title {
    String _content;

    /**
     * CONSTRUCTORS
     */
    Title() {
    }

    Title(String _content) {
        this._content = _content;
    }

    /**
     * GETTER / SETTER
     */
    String get_content() {
        return _content;
    }

    void set_content(String _content) {
        this._content = _content;
    }
}

class Link {
    String _content;

    /**
     * CONSTRUCTORS
     */
    Link() {
    }

    Link(String _content) {
        this._content = _content;
    }

    /**
     * GETTER / SETTER
     */
    String get_content() {
        return _content;
    }

    void set_content(String _content) {
        this._content = _content;
    }
}

class Description {
    private String _content;

    /**
     * CONSTRUCTORS
     */
    Description() {
    }

    Description(String _content) {
        this._content = _content;
    }

    /**
     * GETTER / SETTER
     */
    String get_content() {
        return _content;
    }

    void set_content(String _content) {
        this._content = _content;
    }
}

class Language {
    String _content;

    /**
     * CONSTRUCTORS
     */
    Language() {
    }

    Language(String _content) {
        this._content = _content;
    }

    /**
     * GETTER / SETTER
     */
    String get_content() {
        return _content;
    }

    void set_content(String _content) {
        this._content = _content;
    }
}

class PubDate {
    String _content;

    /**
     * CONSTRUCTORS
     */
    PubDate() {
    }

    PubDate(String _content) {
        this._content = _content;
    }

    /**
     * GETTER / SETTER
     */
    String get_content() {
        return _content;
    }

    void set_content(String _content) {
        this._content = _content;
    }
}

class LastBuildDate {
    String _content;

    /**
     * CONSTRUCTORS
     */
    LastBuildDate() {
    }

    LastBuildDate(String _content) {
        this._content = _content;
    }

    /**
     * GETTER / SETTER
     */
    String get_content() {
        return _content;
    }

    void set_content(String _content) {
        this._content = _content;
    }
}

class Docs {
    String _content;

    /**
     * CONSTRUCTORS
     */
    Docs() {
    }

    Docs(String _content) {
        this._content = _content;
    }

    /**
     * GETTER / SETTER
     */
    String get_content() {
        return _content;
    }

    void set_content(String _content) {
        this._content = _content;
    }
}

class ManagingEditor {
    private String _content;

    /**
     * CONSTRUCTORS
     */
    ManagingEditor() {
    }

    ManagingEditor(String _content) {
        this._content = _content;
    }

    /**
     * GETTER / SETTER
     */
    String get_content() {
        return _content;
    }

    void set_content(String _content) {
        this._content = _content;
    }
}

class WebMaster {
    String _content;

    /**
     * CONSTRUCTORS
     */
    WebMaster() {
    }

    WebMaster(String _content) {
        this._content = _content;
    }

    /**
     * GETTER / SETTER
     */
    String get_content() {
        return _content;
    }

    void set_content(String _content) {
        this._content = _content;
    }
}
