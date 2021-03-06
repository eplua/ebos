package ec.com.ebos.core.master.process;

import java.util.List;

import ec.com.ebos.core.master.model.Persona;
import ec.com.ebos.core.master.model.Propiedad;
import ec.com.ebos.core.orm.crud.Pagination;

/**
 *
 * @author <a href="mailto:eduardo.plua@gmail.com">Eduardo Plua Alay</a>
 * 
 */
public interface MasterP {

	//
	// Propiedad
	//

	public List<Propiedad> findPropiedadList(Propiedad propiedad, Pagination pagination);

	public Propiedad buildPropiedad();

	public Propiedad savePropiedad(Propiedad propiedad);

	public void deletePropiedad(Propiedad propiedad);

	
	//
	// Persona
	//
	
	public List<Persona> findPersonaList(Persona persona, Pagination pagination);
	
	public List<Persona> findPersonaList(String query);

	public Persona createPersona();

	public Persona savePersona(Persona persona);

	public void deletePersona(Persona persona);

}
