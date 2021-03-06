package ec.com.ebos.hibernate.root.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

import lombok.Getter;
import lombok.Setter;
import ec.com.ebos.core.root.model.Auditoria;
import ec.com.ebos.core.security.model.Usuario;
import ec.com.ebos.hibernate.security.model.HibernateUsuario;

/**
 * Clase de auditoria para las Entity
 * 
 * @author <a href="mailto:eduardo.plua@gmail.com">Eduardo Plua Alay</a>
 * @since 2013-02-10
 */
@Embeddable
public class HibernateAuditoria implements Auditoria, Serializable{

	private static final long serialVersionUID = -2195848600759541457L;

	@Getter @Setter
	@ManyToOne(fetch=FetchType.LAZY, targetEntity = HibernateUsuario.class)
    @JoinColumn(name = "id_creador", nullable = false, updatable=false)
	private Usuario creador;
	
	@Getter @Setter
	@ManyToOne(fetch=FetchType.LAZY, targetEntity = HibernateUsuario.class)
    @JoinColumn(name = "id_modificador")
	private Usuario modificador;

	@Getter @Setter
	@Column(name = "creacion", nullable = false, updatable=false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date creado;
	
	@Getter @Setter
	@Column(name = "modificacion")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date modificado;

	@Override
	public Auditoria getAuditoria() {
		return null;
	}

	@Override
	public void setAuditoria(Auditoria auditoria) {
		
	}

}
