package iml.imfotografia.xml.sitemap.element;

public class Url {
    private Loc loc;
    private Lastmod lastmod;

    /**
     * CONSTRUCTORS
     */
    public Url() {
        loc = new Loc();
        lastmod = new Lastmod();
    }
}
