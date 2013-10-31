package ec.com.ebos.master.model;

import java.util.Set;

import ec.com.ebos.conta.model.hibernate.HibernatePeriodoFiscalPais;
import ec.com.ebos.master.model.hibernate.HibernateDivisionGeografica;

public interface DivisionGeografica {

	public String getCodigo();

	public String getDescripcion();

	public Set<HibernateDivisionGeografica> getDivisionGeograficaList();

	public Long getId();

	public int getNivel();

	public DivisionGeografica getPadre();

	public Set<HibernatePeriodoFiscalPais> getPeriodoFiscalPaisList();

	public int getTerminante();

	public void setCodigo(String codigo);

	public void setDescripcion(String descripcion);

	public void setDivisionGeograficaList(
			Set<HibernateDivisionGeografica> divisionGeograficaList);

	public void setId(Long id);

	public void setNivel(int nivel);

	public void setPadre(DivisionGeografica padre);

	public void setPeriodoFiscalPaisList(
			Set<HibernatePeriodoFiscalPais> periodoFiscalPaisList);

	public void setTerminante(int terminante);

	public java.lang.String toString();

	public boolean canEqual(java.lang.Object other);

	public boolean equals(java.lang.Object o);

	public int hashCode();

}