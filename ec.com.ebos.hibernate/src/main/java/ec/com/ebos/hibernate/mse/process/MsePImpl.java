package ec.com.ebos.hibernate.mse.process;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import ec.com.ebos.core.conta.exception.ContaException;
import ec.com.ebos.core.mse.model.Grupo;
import ec.com.ebos.core.mse.model.Monaguillo;
import ec.com.ebos.core.mse.model.MonaguilloGrupo;
import ec.com.ebos.core.mse.process.MseP;
import ec.com.ebos.core.orm.crud.GenericCriteria;
import ec.com.ebos.core.orm.crud.Pagination;
import ec.com.ebos.core.root.model.Entidad.Estado;
import ec.com.ebos.core.util.EntityUtils;
import ec.com.ebos.core.util.NumberUtils;
import ec.com.ebos.core.util.StringUtils;
import ec.com.ebos.hibernate.master.model.field.Persona_;
import ec.com.ebos.hibernate.mse.model.HibernateGrupo;
import ec.com.ebos.hibernate.mse.model.HibernateMonaguillo;
import ec.com.ebos.hibernate.mse.model.HibernateMonaguilloGrupo;
import ec.com.ebos.hibernate.mse.model.field.Grupo_;
import ec.com.ebos.hibernate.mse.model.field.MonaguilloGrupo_;
import ec.com.ebos.hibernate.mse.model.field.Monaguillo_;
import ec.com.ebos.hibernate.root.process.RootPImpl;

/**
 * @author <a href="mailto:vipconsultoresaso@gmail.com">VIP Consultores</a>
 * 
 */
@Repository("mseP")
public class MsePImpl extends RootPImpl<Object, ContaException> implements MseP {

	private static final long serialVersionUID = 1631338076433131489L;

	//
    // Grupo
    //
    @Override
    public List<Grupo> findGrupoList(Grupo grupo, Pagination pagination) {
        GenericCriteria<Grupo> criteria = GenericCriteria.forClass(Grupo.class);
        criteria.addAliasedJoins(Grupo_.creador);
        criteria.addAliasedLeftJoins(Grupo_.modificador);
        criteria.addEqualsIfNotZero(Grupo_.id, grupo.getId());
       
        if(criteria.isChanged()){
        	return findByCriteria(criteria, pagination);
        }
        
        criteria.addLikeTokens(Grupo_.nombre, grupo.getNombre());
        
        return findByCriteria(criteria, pagination);
    }

    @Override
    public Grupo createGrupo() {
        Grupo grupo = new HibernateGrupo();
        grupo.setEstado(Estado.INACTIVO);
        return grupo;
    }

    @Override
    public Grupo saveGrupo(Grupo grupo) {
    	if(!EntityUtils.isPersistent(grupo)){
    		grupo.setEstado(Estado.ACTIVO);
    	}
        grupo = saveOrMerge(grupo);
        putSuccess("grupo.success.save", grupo.getId());
        return grupo;
    }

    @Override
    public void deleteGrupo(Grupo grupo) {
        Long id = grupo.getId();
        delete(grupo);
        putSuccess("grupo.success.delete",id);
    }
    
    //
    // Monaguillo
    //
    @Override
    public List<Monaguillo> findMonaguilloList(Monaguillo monaguillo, Pagination pagination) {
        GenericCriteria<Monaguillo> criteria = GenericCriteria.forClass(Monaguillo.class);
        criteria.addAliasedJoins(Monaguillo_.persona, Monaguillo_.creador);
        criteria.addAliasedLeftJoins(Monaguillo_.modificador);
        
        criteria.addEqualsIfNotZero(Monaguillo_.id, monaguillo.getId());
        if(criteria.isChanged()){
        	return findByCriteria(criteria, pagination);
        }
        
        criteria.addLikeTokens(Monaguillo_.persona+"."+Persona_.nombres,monaguillo.getPersona().getNombres());
        criteria.addLikeTokens(Monaguillo_.persona+"."+Persona_.apellidos, monaguillo.getPersona().getApellidos());
        criteria.addLikeTokens(Monaguillo_.centroEstudio, monaguillo.getCentroEstudio());
        criteria.addLikeTokens(Monaguillo_.representantes, monaguillo.getRepresentantes());
        
        //return findByCriteria(criteria, pagination);
        return new ArrayList<Monaguillo>();
    }
    
    public List<Monaguillo> findMonaguilloList(String query){
    	if(StringUtils.isBlank(query)){
			return new ArrayList<Monaguillo>();
		}
	
		GenericCriteria<Monaguillo> criteria = GenericCriteria.forClass(Monaguillo.class);
		criteria.addAliasedJoins(Monaguillo_.persona);
		
		if(NumberUtils.tryParseLong(query)){
			criteria.addEqualsIfNotZero(Monaguillo_.id, NumberUtils.parseLong(query));
			if(criteria.isChanged()){
				return findByCriteria(criteria);
			}
		}
		
		criteria.addMultiLikeTokens(query, Monaguillo_.persona+"."+Persona_.nombres, Monaguillo_.persona+"."+Persona_.apellidos);

		return findByCriteria(criteria);
    }
    
    @Override
    public List<MonaguilloGrupo> getMonaguilloGrupoList(Grupo grupo){
    	GenericCriteria<MonaguilloGrupo> criteria = GenericCriteria.forClass(MonaguilloGrupo.class);
        criteria.addAliasedJoins(MonaguilloGrupo_.monaguillo, MonaguilloGrupo_.monaguillo+"."+MonaguilloGrupo_.persona,MonaguilloGrupo_.creador);
        criteria.addAliasedLeftJoins(MonaguilloGrupo_.modificador);
        criteria.addEquals(MonaguilloGrupo_.grupo, grupo);        
        return findByCriteria(criteria);
    }
    
       @Override
    public Monaguillo createMonaguillo() {
        Monaguillo monaguillo = new HibernateMonaguillo();
        monaguillo.setEstado(Estado.INACTIVO);
        return monaguillo;
    }

    @Override
    public Monaguillo saveMonaguillo(Monaguillo monaguillo) {
        
        if(!EntityUtils.isPersistent(monaguillo)){
        	monaguillo.setEstado(Estado.ACTIVO);
        }
        		        		
		monaguillo = saveOrMerge(monaguillo);
        putSuccess("monaguillo.success.save", monaguillo.getId());
        return monaguillo;
    }

    @Override
    public void deleteMonaguillo(Monaguillo monaguillo) {
        Long id = monaguillo.getId();
        delete(monaguillo);
        putSuccess("monaguillo.success.delete",id);
    }
    
    @Override
    public MonaguilloGrupo createMonaguilloGrupo(){
    	MonaguilloGrupo monaguilloGrupo = new HibernateMonaguilloGrupo();
        monaguilloGrupo.setEstado(Estado.INACTIVO);
        return monaguilloGrupo;
    }

    @Override
	public void saveMonaguilloGrupo(MonaguilloGrupo monaguilloGrupo){
    	 monaguilloGrupo = saveOrMerge(monaguilloGrupo);
         putSuccess("monaguilloGrupo.success.save", monaguilloGrupo.getId());
    }

    @Override
	public void deleteMonaguilloGrupo(MonaguilloGrupo monaguilloGrupo){
    	Long id = monaguilloGrupo.getId();
        delete(monaguilloGrupo);
        putSuccess("monaguilloGrupo.success.delete",id);
    }
}
