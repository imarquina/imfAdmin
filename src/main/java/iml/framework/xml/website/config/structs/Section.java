package iml.framework.xml.website.config.structs;

import iml.framework.arq.utils.Crypto;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static iml.framework.arq.utils.Number.isNumeric;
import static iml.framework.arq.utils.Text.htmlReplace;
import static iml.framework.arq.utils.Xml.innerXml;

public class Section {
    private String _nodeName = "";
    private String _name;
    private Integer _width;
    private Integer _height;
    private Boolean _byDefault;
    private Date _update;
    private String _content;
    private DateFormat _dateFormatIn = new SimpleDateFormat("yyyyMMdd");
    private DateFormat _dateFormatOut = new SimpleDateFormat("yyyyMMdd");

    final static Logger logger = Logger.getLogger(Section.class);

    /**
     * CONSTANTS
     */
    private static final String ATTRIBUTE_NAME = "name";
    private static final String ATTRIBUTE_WIDTH = "width";
    private static final String ATTRIBUTE_HEIGHT = "height";
    private static final String ATTRIBUTE_BYDEFAULT = "bydefault";
    private static final String ATTRIBUTE_UPDATE = "update";

    /**
     * CONSTRUCTORES
     */
    public Section() {
        this._nodeName = "section";
        this._name = "";
        this._width = 0;
        this._height = 0;
        this._byDefault = false;
        this._update = new Date();
        this._content = "";
    }

    public Section(String name, Integer width, Integer height, Boolean byDefault, Date update) {
        this();
        this.set_name(name);
        this.set_width(width);
        this.set_height(height);
        this.set_byDefault(byDefault);
        this.set_update(update);
    }

    /**
     * GETTER / SETTER
     */
    public String get_id() {
        return Crypto.getMD5(htmlReplace(this._name));
    }

    public String get_name() {
        return this._name;
    }

    public void set_name(String name) {
        this._name = name;
    }

    public Integer get_width() {
        return this._width;
    }

    public void set_width(String width) {
        if (isNumeric(width))
            this._width = Integer.parseInt(width);
        else
            this._width = 0;
    }

    public void set_width(Integer width) {
        this._width = width;
    }

    public Integer get_height() {
        return this._height;
    }

    public void set_height(String height) {
        if (isNumeric(height))
            this._height = Integer.parseInt(height);
        else
            this._height = 0;
    }

    public void set_height(Integer height) {
        this._height = height;
    }

    public Boolean get_byDefault() {
        return this._byDefault;
    }

    public void set_byDefault(String byDefault) {
        this._byDefault = Boolean.valueOf(byDefault);
    }

    public void set_byDefault(Boolean byDefault) {
        this._byDefault = byDefault;
    }

    public Date get_update() {
        return this._update;
    }

    public String get_updateString() { return  _dateFormatOut.format(this._update); }

    public void set_update(String update) throws ParseException {
        this._update = _dateFormatIn.parse(update);
    }

    public void set_update(Date update) {
        this._update = update;
    }

    public String get_content() {
        return this._content;
    }

    public void set_content(String content) {
        this._content = content;
    }

    public String get_nodeName() {
        return this._nodeName;
    }

    /**
     *
     * @param node
     * @return
     * @throws ParseException
     */
    public Section fromXml(Node node) throws ParseException {
        logger.debug("Begin");
        Section section = new Section();

        // get attributes names and values
        NamedNodeMap nodeMap = node.getAttributes();
        for (int i = 0; i < nodeMap.getLength(); i++) {
            Node tempNode = nodeMap.item(i);

            String sAttrName = tempNode.getNodeName();
            if (!sAttrName.equals(null))
                sAttrName = sAttrName.trim().toUpperCase();

            String sAttrValue = tempNode.getNodeValue();
            if (!sAttrValue.equals(null))
                sAttrValue = sAttrValue.trim();

            logger.info("    Attr name : " + sAttrName + "; Value = " + sAttrValue);

            if (sAttrName.equalsIgnoreCase(ATTRIBUTE_NAME)) {
                section.set_name(sAttrValue);
            } else if (sAttrName.equalsIgnoreCase(ATTRIBUTE_WIDTH)) {
                section.set_width(sAttrValue);
            } else if (sAttrName.equalsIgnoreCase(ATTRIBUTE_HEIGHT)) {
                section.set_height(sAttrValue);
            } else if (sAttrName.equalsIgnoreCase(ATTRIBUTE_BYDEFAULT)) {
                section.set_byDefault(sAttrValue);
            } else if (sAttrName.equalsIgnoreCase(ATTRIBUTE_UPDATE)) {
                section.set_update(sAttrValue);
            } else {
                logger.info("unknow Section property " + sAttrName + ":" + sAttrValue);
            }
            logger.debug("set Section property " + sAttrName + ":" + sAttrValue);
        }

        section.set_content(innerXml(node));

        logger.debug("End");
        return section;
    }

    /**
     *
     * @param document
     * @param parentNode
     */
    public void toXml(Document document, Element parentNode){
        logger.debug("Begin");

        parentNode.setAttribute(ATTRIBUTE_NAME, this.get_name());
        parentNode.setAttribute(ATTRIBUTE_WIDTH, this.get_width().toString());
        parentNode.setAttribute(ATTRIBUTE_HEIGHT, this.get_height().toString());
        parentNode.setAttribute(ATTRIBUTE_BYDEFAULT, this.get_byDefault().toString());
        parentNode.setAttribute(ATTRIBUTE_UPDATE, this.get_updateString());

        parentNode.appendChild(document.createCDATASection(this.get_content()));

        logger.debug("End");
    }
}
