package ec.com.ebos.web.security.jsf.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import lombok.Getter;
import lombok.Setter;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ec.com.ebos.core.admin.model.Opcion;
import ec.com.ebos.core.admin.service.AdministracionS;
import ec.com.ebos.core.context.BeanScopes;
import ec.com.ebos.core.orm.crud.Pagination;
import ec.com.ebos.core.root.model.Entidad;
import ec.com.ebos.core.security.model.Rol;
import ec.com.ebos.core.security.model.RolOpcion;
import ec.com.ebos.core.util.EntityUtils;

/**
 * @author Eduardo Plua Alay
 */

@Component(RolBean.BEAN_NAME)
@Scope(BeanScopes.SESSION)
//@ManagedBean(name = RolBean.BEAN_NAME)
//@SessionScoped
//@ViewScoped
public class RolBean extends SecurityBean<Rol> {
    
	private static final long serialVersionUID = 5900425430487867980L;
	
	public static final String BEAN_NAME = "rolBean";
	
	@Getter @Setter
    @ManagedProperty(value = AdministracionS.EL_BEAN_NAME)
    protected AdministracionS administracionS;

    @Override
    public void getInit() {
        entitySearch = securityS.getInstanceRol();
        entitySearch.setEstado(Entidad.Estado.ACTIVO);
    }

    @Override
    protected void habilitaControles() {
        setHabilitaCrear();
        setHabilitaEditar(); //TODO (epa):Implementar Seguridad
        setHabilitaGuardar();
        setHabilitaEliminar();
        
        if(EntityUtils.isPersistent(activeEntity)){
            
        }
    }

    @Override
    protected void initTarget() {
        TARGET_ID = "/modules/security/rol/finder.xhtml";
    }
    
    ///////////////////////// DATA MODEL ////////////////////////

    @Override
    protected List<Rol> loadDataTableCollection(Rol rol, Pagination pagination) {
        return securityS.findRolList(rol, pagination);
    }
        
    //////////////////// ACCIONES ////////////////////
    
    @Override
    public void crear() {
        activeEntity = securityS.createRol();
        opcionList.clear();
        rolOpcionList.clear();
    }

    @Override
    public void editar() {        
        opcionList.clear();
        rolOpcionList.clear();
    }
    
    @Override
    public void actualizar(){                        
        rolOpcionList.clear();    
    }

    @Override
    public void guardar() {
        activeEntity = securityS.saveRol(activeEntity);
    }

    @Override
    public void eliminar() {
        securityS.deleteRol(activeEntity);    
    }            
    
    ///////////////////////// DATALIST /////////////////////////
    
    @Setter
    private List<RolOpcion> rolOpcionList = new ArrayList<RolOpcion>();
    
    @Setter
    private List<Opcion> opcionList = new ArrayList<Opcion>();
    
    @Getter @Setter
    private RolOpcion[] selectedRolOpcionList;
    
    @Getter @Setter
    private Opcion selectedOpcion;    
    
    public void agregarRolOpcion(){        
        securityS.generateRolOpcion(activeEntity, selectedOpcion);
        rolOpcionList.clear();
    }
    
    public void guardarRolOpcionList(){                
        securityS.saveRolOpcionList(Arrays.asList(selectedRolOpcionList));
        selectedRolOpcionList = null;
        rolOpcionList.clear();
    }
    
    public void eliminarRolOpcionList(){ 
        List<RolOpcion> list = Arrays.asList(selectedRolOpcionList);
        securityS.deleteRolOpcionList(list);        
        rolOpcionList.clear();
    }
    
    //GETTERS AND SETTERS

    public List<RolOpcion> getRolOpcionList() {
        if(rolOpcionList.isEmpty()){
            rolOpcionList = securityS.getRolOpcionList(activeEntity);
        }
        return rolOpcionList;
    }

    public List<Opcion> getOpcionList() {
        if(opcionList.isEmpty()){
            opcionList = administracionS.findOpcionList(null, new Pagination()); // TODO (epa): Crear metodo sin pagination
        }        
        return opcionList;
    }

}
