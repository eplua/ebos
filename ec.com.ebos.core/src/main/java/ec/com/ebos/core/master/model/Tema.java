package ec.com.ebos.core.master.model;

import ec.com.ebos.core.root.model.Entidad;

public interface Tema extends Master {
	
	public Entidad.Estado getEstado();

	public Long getId();

	public String getImagen();

	public String getNombre();

	public void setEstado(Entidad.Estado estado);

	public void setId(Long id);

	public void setImagen(String imagen);

	public void setNombre(String nombre);
	
}