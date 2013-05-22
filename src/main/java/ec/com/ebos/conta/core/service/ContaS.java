package ec.com.ebos.conta.core.service;

import java.io.Serializable;
import java.util.List;

import ec.com.ebos.conta.model.TipoCuenta;
import ec.com.ebos.orm.crud.Pagination;

/**
 * @author <a href="mailto:eduardo.plua@gmail.com">Eduardo Plua Alay</a>
 * @since 2013-05-21
 */
public interface ContaS extends Serializable {
    
	//
    // TipoCuenta
    //
	public List<TipoCuenta> findTipoCuentaList(TipoCuenta tipoCuenta, Pagination pagination);
	
	public TipoCuenta createTipoCuenta();
	
	public TipoCuenta saveTipoCuenta(TipoCuenta tipoCuenta);
	
	public void deleteTipoCuenta(TipoCuenta tipoCuenta);
	
}
