package iml.imfotografia.xml.sitemap.element;


import java.util.Date;

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

    public Url(String loc, Date lastMod){

    }
}
