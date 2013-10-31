package ec.com.ebos.mse.core.process;

import java.util.List;

import ec.com.ebos.mse.model.Grupo;
import ec.com.ebos.mse.model.MonaguilloGrupo;
import ec.com.ebos.mse.model.hibernate.HibernateGrupo;
import ec.com.ebos.mse.model.hibernate.HibernateMonaguillo;
import ec.com.ebos.mse.model.hibernate.HibernateMonaguilloGrupo;
import ec.com.ebos.orm.crud.Pagination;

/**
 *
 * @author <a href="mailto:eduardo.plua@gmail.com">Eduardo Plua Alay</a>
 * 
 */
public interface MseP {

	//
	// Grupo
	//
	public List<HibernateGrupo> findGrupoList(HibernateGrupo grupo, Pagination pagination);
	
	public HibernateGrupo createGrupo();
	
	public HibernateGrupo saveGrupo(HibernateGrupo grupo);
	
	public void deleteGrupo(HibernateGrupo grupo);
	
	//
	// Monagillo
	//
	public List<HibernateMonaguillo> findMonaguilloList(HibernateMonaguillo monaguillo, Pagination pagination);
	
	public List<HibernateMonaguillo> findMonaguilloList(String query);
	
	public List<HibernateMonaguilloGrupo> getMonaguilloGrupoList(Grupo grupo);
	
	public HibernateMonaguillo createMonaguillo();
	
	public HibernateMonaguillo saveMonaguillo(HibernateMonaguillo monaguillo);
	
	public void deleteMonaguillo(HibernateMonaguillo monaguillo);

	public MonaguilloGrupo createMonaguilloGrupo();

	public void saveMonaguilloGrupo(HibernateMonaguilloGrupo monaguilloGrupo);

	public void deleteMonaguilloGrupo(HibernateMonaguilloGrupo monaguilloGrupo);


}
