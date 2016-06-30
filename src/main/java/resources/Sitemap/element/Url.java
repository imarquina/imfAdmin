package resources.Sitemap.element;

import resources.Sitemap.element.Lastmod;
import resources.Sitemap.element.Loc;

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
