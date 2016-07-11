package iml.imfotografia.xml.sitemap.element;


import iml.imfotografia.xml.feed.struct.LastBuildDate;

import java.util.Date;

public class Url {
    public Loc loc;
    public Lastmod lastmod;

    /**
     * CONSTRUCTORS
     */
    public Url() {
        loc = new Loc();
        lastmod = new Lastmod();
    }

    public Url(String sLoc, Date dLastMod){
        Loc loc = new Loc(sLoc);
        this.addLoc(loc);

        Lastmod lastmod = new Lastmod(dLastMod);
        this.addLasmod(lastmod);
    }

    /**
     *  PUBLIC METHODS
     */
    public void addLoc(Loc loc) {
        this.loc = loc;
    }

    public void addLasmod(Lastmod lastmod) { this.lastmod = lastmod; }
}
