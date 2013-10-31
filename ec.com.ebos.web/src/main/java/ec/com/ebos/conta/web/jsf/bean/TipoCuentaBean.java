package ec.com.ebos.conta.web.jsf.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.springframework.stereotype.Component;

import lombok.Getter;
import ec.com.ebos.conta.model.hibernate.HibernateCuentaContable;
import ec.com.ebos.conta.model.hibernate.HibernateTipoCuenta;
import ec.com.ebos.orm.crud.Pagination;
import ec.com.ebos.util.EntityUtils;

/**
 * @author <a href="mailto:eduardo.plua@gmail.com">Eduardo Plua Alay</a>
 * @since 2013-05-21
 */
@Component
@ManagedBean(name = TipoCuentaBean.BEAN_NAME)
@ViewScoped
public class TipoCuentaBean extends ContaBean<HibernateTipoCuenta> {
    	
	private static final long serialVersionUID = 4109617962842899097L;
	
	@Getter
	public static final String BEAN_NAME = "tipoCuentaBean";

	@Override
    public void getInit() {
        // Para busquedas
        entitySearch = new HibernateTipoCuenta();
        entitySearch.setTipo(null);
        entitySearch.setNaturaleza(null);
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
        TARGET_ID = "/modules/conta/tipoCuenta/finder.xhtml";
    }
    
    ///////////////////////// DATA MODEL ////////////////////////

    @Override
    protected List<HibernateTipoCuenta> loadDataTableCollection(HibernateTipoCuenta tipoCuenta, Pagination pagination) {
        return contaS.findTipoCuentaList(tipoCuenta, pagination);
    }
        
    //////////////////// ACCIONES ////////////////////
    
    @Override
    public void crear() {
        activeEntity = contaS.createTipoCuenta();
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
        activeEntity = contaS.saveTipoCuenta(activeEntity);                
    }

    @Override
    public void eliminar() {
        contaS.deleteTipoCuenta(activeEntity);                
    }            
    
    //////////////////////// LISTS ///////////////////////////////
    
    @Getter
    protected final List<HibernateTipoCuenta.Tipo> tipoList = new ArrayList<HibernateTipoCuenta.Tipo>(HibernateTipoCuenta.Tipo.LIST);
    
    @Getter
    protected final List<HibernateCuentaContable.Naturaleza> naturalezaList = new ArrayList<HibernateCuentaContable.Naturaleza>(HibernateCuentaContable.Naturaleza.LIST);
    
}
