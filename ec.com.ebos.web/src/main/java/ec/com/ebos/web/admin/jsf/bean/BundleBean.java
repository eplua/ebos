package ec.com.ebos.web.admin.jsf.bean;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ec.com.ebos.core.admin.model.Bundle;
import ec.com.ebos.core.context.BeanScopes;
import ec.com.ebos.core.orm.crud.Pagination;
import ec.com.ebos.core.util.EntityUtils;

/**
 * @author <a href="mailto:eduardo.plua@gmail.com">Eduardo Plua Alay</a>
 * @since 2013-03-24
 */
@Component(BundleBean.BEAN_NAME)
@Scope(BeanScopes.SESSION)
public class BundleBean extends AdministracionBean<Bundle> {
    
	private static final long serialVersionUID = -8387498705417931654L;
	
	public static final String BEAN_NAME = "bundleBean";

	@Override
    public void getInit() {
        entitySearch = administracionS.getInstanceBundle();
    }

    @Override
    protected void habilitaControles() {
        setHabilitaCrear();
        setHabilitaEditar();
        setHabilitaGuardar();
        setHabilitaEliminar();
        
        if(EntityUtils.isPersistent(activeEntity)){
            
        } else { 
        	
        }
    }

    @Override
    protected void initTarget() {
        TARGET_ID = "/modules/admin/bundle/finder.xhtml";
    }
    
    ///////////////////////// DATA MODEL ////////////////////////

    @Override
    protected List<Bundle> loadDataTableCollection(Bundle bundle, Pagination pagination) {
        return administracionS.findBundleList(bundle, pagination);
    }
        
    //////////////////// ACCIONES ////////////////////
    
    @Override
    public void crear() {
        activeEntity = administracionS.createBundle();
    }

    @Override
    public void editar() {        
    	//activeEntity = masterS.loadBundle(activeEntity.getId());
    }

    @Override
    public void guardar() {
//        administracionS.evictBundle(activeEntity.getCodigo(), activeEntity.getLocalidad());
        activeEntity = administracionS.saveBundle(activeEntity);
    }

    @Override
    public void eliminar() {
    	administracionS.deleteBundle(activeEntity);                
    }            
    
    //////////////////////// LISTS ///////////////////////////////
    
    @Getter
    protected final List<Bundle.Localidad> localidadList = new ArrayList<Bundle.Localidad>(Bundle.Localidad.LIST);
    
}
