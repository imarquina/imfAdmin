package resources.elements;

/**
 * Created by imarquina on 29/6/16.
 */
public class Rss {
    private String _version;
    public Channel = new Channel();

    /**
     * CONSTRUCTORS
     */
    public Rss(String _version) {
        this._version = _version;
    }

    /**
     * GETTER / SETTER
     */
    public String get_version() {
        return _version;
    }

    public void set_version(String _version) {
        this._version = _version;
    }
}

public class Channel {
    public Title title = new Title();
    public Link link = new Link();
    public Description description = new Description();
    public Language language = new Language();
    public PubDate pubDate = new PubDate();
    public LastBuildDate lastBuildDate = LastBuildDate();
    public Docs docs = new Docs();
    public ManagingEditor managingEditor = new ManagingEditor();
    public WebMaster webMaster = new WebMaster();
}

class Title {
    private String _content;

    /**
     * CONSTRUCTORS
     */
    public Title() {
    }

    public Title(String _content) {
        this._content = _content;
    }

    /**
     * GETTER / SETTER
     */
    public String get_content() {
        return _content;
    }

    public void set_content(String _content) {
        this._content = _content;
    }
}

class Link {
    private String _content;

    /**
     * CONSTRUCTORS
     */
    public Link() {
    }

    public Link(String _content) {
        this._content = _content;
    }

    /**
     * GETTER / SETTER
     */
    public String get_content() {
        return _content;
    }

    public void set_content(String _content) {
        this._content = _content;
    }
}

class Description {
    private String _content;

    /**
     * CONSTRUCTORS
     */
    public Description() {
    }

    public Description(String _content) {
        this._content = _content;
    }

    /**
     * GETTER / SETTER
     */
    public String get_content() {
        return _content;
    }

    public void set_content(String _content) {
        this._content = _content;
    }
}

class Language {
    private String _content;

    /**
     * CONSTRUCTORS
     */
    public Language() {
    }

    public Language(String _content) {
        this._content = _content;
    }

    /**
     * GETTER / SETTER
     */
    public String get_content() {
        return _content;
    }

    public void set_content(String _content) {
        this._content = _content;
    }
}

class PubDate {
    private String _content;

    /**
     * CONSTRUCTORS
     */
    public PubDate() {
    }

    public PubDate(String _content) {
        this._content = _content;
    }

    /**
     * GETTER / SETTER
     */
    public String get_content() {
        return _content;
    }

    public void set_content(String _content) {
        this._content = _content;
    }
}

class LastBuildDate {
    private String _content;

    /**
     * CONSTRUCTORS
     */
    public LastBuildDate() {
    }

    public LastBuildDate(String _content) {
        this._content = _content;
    }

    /**
     * GETTER / SETTER
     */
    public String get_content() {
        return _content;
    }

    public void set_content(String _content) {
        this._content = _content;
    }
}

class Docs {
    private String _content;

    /**
     * CONSTRUCTORS
     */
    public Docs() {
    }

    public Docs(String _content) {
        this._content = _content;
    }

    /**
     * GETTER / SETTER
     */
    public String get_content() {
        return _content;
    }

    public void set_content(String _content) {
        this._content = _content;
    }
}

class ManagingEditor {
    private String _content;

    /**
     * CONSTRUCTORS
     */
    public ManagingEditor() {
    }

    public ManagingEditor(String _content) {
        this._content = _content;
    }

    /**
     * GETTER / SETTER
     */
    public String get_content() {
        return _content;
    }

    public void set_content(String _content) {
        this._content = _content;
    }
}

class WebMaster {
    private String _content;

    /**
     * CONSTRUCTORS
     */
    public WebMaster() {
    }

    public WebMaster(String _content) {
        this._content = _content;
    }

    /**
     * GETTER / SETTER
     */
    public String get_content() {
        return _content;
    }

    public void set_content(String _content) {
        this._content = _content;
    }
}
