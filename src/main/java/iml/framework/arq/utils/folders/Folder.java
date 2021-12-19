package iml.framework.arq.utils.folders;

import iml.framework.xml.sourcetaj.Property;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.io.File;

/**
 * Created by inaki.marquina on 03/03/2017.
 */
public class Folder {
    final static Logger logger = LogManager.getLogger(Folder.class);

    /** PRIVATE MEMBERS **/
    private String _pathTom;
    private String _pathWeb;
    private String[] _folderExcluded;
    private String[] _folderIncluded;

    private eFolderStructure _sructure;
    private eFolderCondition _condition;

    /**
     *
     * @param server
     * @return
     */
    private boolean isPathExcluded(String path, eServer server){
        String[] folderExcluded = this.get_folderExcluded(server);

        for (String folder : folderExcluded){
            if (!folder.equalsIgnoreCase("") && path.concat("\\").contains(folder)) {
                return true;
            }
        }

        return false;
    }

    /**
     *
     * @param server
     * @return
     */
    private boolean isPathIncluded(String path, eServer server){
        String[] folderIncluded = this.get_folderIncluded(server);
        String rootPath = "";

        if (get_sructure() == eFolderStructure.TAJ){
            if (server == eServer.TOM) {
                rootPath = Property.readProperty("iml.folder.dir.taj.tom");
            }else if (server == eServer.WEB) {
                rootPath = Property.readProperty("iml.folder.dir.taj.web");
            }
        }else if(get_sructure() == eFolderStructure.SOURCE){
            if (server == eServer.TOM) {
                rootPath = Property.readProperty("iml.folder.dir.src.tom");
            }else if (server == eServer.WEB) {
                rootPath = Property.readProperty("iml.folder.dir.src.web");
            }
        }

        if (rootPath.equalsIgnoreCase(path))
            return true;

        for (String folder : folderIncluded){
            if (!folder.equalsIgnoreCase("") && path.concat("\\").contains(folder)) {
                return true;
            }
        }

        return false;
    }

    /**
     *
     * @param server
     * @return
     */
    private boolean isPathValid(String path, eServer server){
        if (get_condition() == eFolderCondition.INCLUDED){
            if (isPathIncluded(path, server))
                return true;
            else
                return false;
        }else if (get_condition() == eFolderCondition.EXCLUDED){
            if (isPathExcluded(path, server))
                return true;
            else
                return false;
        }else if (get_condition() == eFolderCondition.BOTH){
            if (isPathIncluded(path, server) && !isPathExcluded(path, server))
                return true;
            else
                return false;
        }
        return false;
    }

    /**
     * Método privado que recibe el path en argumento para permitir
     * @param path
     * @param server
     * @param inizialize
     */
    private void browse(String path, eServer server, boolean inizialize){
        logger.debug("Begin");

        if (inizialize){
            this.set_folderExcluded(null);
            this.set_folderIncluded(null);
        }

        if (isPathValid(path, server)) {
            File listFile = new File(path);
            File listElements[] = listFile.listFiles();

            if (listElements != null) {
                for (int i = 0; i < listElements.length; i++) {
                    if (listElements[i].isDirectory() && isPathValid(listElements[i].getPath(), server)) {
                        logger.debug("directorio actual: " + listElements[i]);

                        if (!listElements[i].getName().startsWith(Property.readProperty("iml.folder.exclude.name"))) {
                            logger.info("directorio: " + listElements[i]);

                            browse(listElements[i].getPath(), server, false);
                        } else {
                            logger.debug("directorio descartado: " + listElements[i]);
                        }

                        logger.debug(i + " direcotrio: " + listElements[i]);
                    }else{
                        logger.debug("directorio excluido: " + listElements[i].getPath());
                    }
                }
            } else {
                logger.debug("directorio nulo…");
            }
        }else{
            logger.debug("directorio excluido: " + path);
        }

        logger.debug("End");
    }

    /** CONSTRUCTORS **/
    public Folder(){

    }

    /**
     * Constructor que incluye ruta, estructura de directorios a tratar
     * y condición de inclusión/exclusión de rutas
     * @param structure
     * @param condition
     */
    public Folder(eFolderStructure structure, eFolderCondition condition){
        this.set_sructure(structure);
        this.set_condition(condition);

        if (get_sructure() == eFolderStructure.SOURCE){
            this.set_pathTom(Property.readProperty("iml.folder.dir.src.tom"));
            this.set_pathWeb(Property.readProperty("iml.folder.dir.src.web"));
        }else if (get_sructure() == eFolderStructure.TAJ){
            this.set_pathTom(Property.readProperty("iml.folder.dir.taj.tom"));
            this.set_pathWeb(Property.readProperty("iml.folder.dir.taj.web"));
        }
    }

    /** PUBLIC MEMBERS **/
    /**
     * Método público que recoge el path de la instancia de clase
     */
    public void browse(){
        this.browse(this.get_pathTom(), eServer.TOM, true);
        this.browse(this.get_pathWeb(), eServer.WEB, true);
    }

    /** GETTER - SETTER **/
    public String get_pathTom() {
        return _pathTom;
    }

    public void set_pathTom(String _pathTom) {
        this._pathTom = _pathTom;
    }

    public String get_pathWeb() {
        return _pathWeb;
    }

    public void set_pathWeb(String _pathWeb) {
        this._pathWeb = _pathWeb;
    }

    public String[] get_folderExcluded(eServer server) {
        if (_folderExcluded == null) {
            if (get_sructure() == eFolderStructure.SOURCE) {
                if (server == eServer.TOM) {
                    set_folderExcluded(Property.readProperty("iml.folder.exclude.src.tom.path").split(","));
                } else if (server == eServer.WEB) {
                    set_folderExcluded(Property.readProperty("iml.folder.exclude.src.web.path").split(","));
                }
            }else if (get_sructure() == eFolderStructure.TAJ) {
                if (server == eServer.TOM) {
                    set_folderExcluded(Property.readProperty("iml.folder.exclude.taj.tom.path").split(","));
                } else if (server == eServer.WEB) {
                    set_folderExcluded(Property.readProperty("iml.folder.exclude.taj.web.path").split(","));
                }
            }
        }

        return _folderExcluded;
    }

    public void set_folderExcluded(String[] _folderExcluded) {
        this._folderExcluded = _folderExcluded;
    }

    public String[] get_folderIncluded(eServer server) {
        if (_folderIncluded == null) {
            if (get_sructure() == eFolderStructure.SOURCE) {
                if (server == eServer.TOM) {
                    set_folderIncluded(Property.readProperty("iml.folder.include.src.tom.path").split(","));
                } else if (server == eServer.WEB) {
                    set_folderIncluded(Property.readProperty("iml.folder.include.src.web.path").split(","));
                }
            }else if (get_sructure() == eFolderStructure.TAJ) {
                if (server == eServer.TOM) {
                    set_folderIncluded(Property.readProperty("iml.folder.include.taj.tom.path").split(","));
                } else if (server == eServer.WEB) {
                    set_folderIncluded(Property.readProperty("iml.folder.include.taj.web.path").split(","));
                }
            }
        }

        return _folderIncluded;
    }

    public void set_folderIncluded(String[] _folderIncluded) {
        this._folderIncluded = _folderIncluded;
    }

    public eFolderStructure get_sructure() {
        return _sructure;
    }

    private void set_sructure(eFolderStructure _sructure) {
        this._sructure = _sructure;
    }

    public eFolderCondition get_condition() {
        return _condition;
    }

    private void set_condition(eFolderCondition _condition) {
        this._condition = _condition;
    }
}
