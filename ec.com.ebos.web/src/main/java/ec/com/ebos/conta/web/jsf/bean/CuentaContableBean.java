package ec.com.ebos.conta.web.jsf.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.springframework.stereotype.Component;

import lombok.Getter;
import ec.com.ebos.conta.model.hibernate.HibernateCuentaContable;
import ec.com.ebos.orm.crud.Pagination;
import ec.com.ebos.util.EntityUtils;

/**
 * @author <a href="mailto:eduardo.plua@gmail.com">Eduardo Plua Alay</a>
 * @since 2013-05-23
 */
@Component
@ManagedBean(name = CuentaContableBean.BEAN_NAME)
@ViewScoped
public class CuentaContableBean extends ContaBean<HibernateCuentaContable> {
    	
	private static final long serialVersionUID = 4109617962842899097L;
	
	@Getter
	public static final String BEAN_NAME = "cuentaContableBean";

	@Override
    public void getInit() {
        // Para busquedas
        entitySearch = new HibernateCuentaContable();
    }

    @Override
    protected void habilitaControles() {
        setHabilitaCrear();
        setHabilitaEditar();
        setHabilitaGuardar();
        setHabilitaEliminar();
        
        if(EntityUtils.isPersistent(activeEntity)){
            
        } else {
        	setHabilitaEliminar(false);
        	//TODO (epa): La plataforma no debe permitir para todos las pantallas mostrar el boton eliminar si la 
        	//entidad activa no esta persistida en la base de datos
        }
    }

    @Override
    protected void initTarget() {
        TARGET_ID = "/modules/conta/cuentaContable/transaction.xhtml";
    }
    
    ///////////////////////// DATA MODEL ////////////////////////

    @Override
    protected List<HibernateCuentaContable> loadDataTableCollection(HibernateCuentaContable cuentaContable, Pagination pagination) {
        //return contaS.findTipoCuentaList(cuentaContable, pagination);
    	return new ArrayList<HibernateCuentaContable>();
    }
        
    //////////////////// ACCIONES ////////////////////
    
    @Override
    public void crear() {
        //activeEntity = contaS.createTipoCuenta();
    }

    @Override
    public void editar() {        
    	//activeEntity = masterS.loadBundle(activeEntity.getId());
    }
    
    @Override
    public void actualizar(){                        
        editar();
    }

    @Override
    public void guardar() {
        //activeEntity = contaS.saveTipoCuenta(activeEntity);                
    }

    @Override
    public void eliminar() {
        //contaS.deleteTipoCuenta(activeEntity);                
    }            
    
    //////////////////////// LISTS ///////////////////////////////
    
}
