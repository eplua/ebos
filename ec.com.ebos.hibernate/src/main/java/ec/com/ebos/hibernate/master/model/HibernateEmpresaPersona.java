package ec.com.ebos.hibernate.master.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.hibernate.annotations.Target;
import org.hibernate.annotations.Type;

import ec.com.ebos.core.aspect.annotation.Auditable;
import ec.com.ebos.core.master.model.ActivoCustodio;
import ec.com.ebos.core.master.model.EmpresaPersona;
import ec.com.ebos.core.master.model.Master;
import ec.com.ebos.core.master.model.Organizacion;
import ec.com.ebos.core.master.model.Persona;
import ec.com.ebos.core.root.model.Auditoria;
import ec.com.ebos.core.root.model.Entidad;
import ec.com.ebos.core.security.model.Usuario;
import ec.com.ebos.hibernate.root.model.HibernateAuditoria;
import ec.com.ebos.hibernate.root.model.field.Entidad_;
import ec.com.ebos.hibernate.security.model.HibernateUsuario;

/**
 * @author <a href="mailto:eduardo.plua@gmail.com">Eduardo Plua Alay</a>
 * 
 */
@Entity
@Table(name = HibernateEmpresaPersona.TABLE_NAME, schema = Master.SCHEMA)
@Data @EqualsAndHashCode(callSuper=false) 
@Auditable
public class HibernateEmpresaPersona extends HibernateMaster implements EmpresaPersona{

	private static final long serialVersionUID = 6960552970253412538L;

	protected static final String TABLE_NAME = "EMPRESA_PERSONA";
	private static final String SEQUENCE = Master.SCHEMA+".S"+TABLE_NAME;
	private static final String GENERATOR = TABLE_NAME+"_ID_GENERATOR";

	@Id
	@SequenceGenerator(name = GENERATOR, sequenceName = SEQUENCE)
	@GeneratedValue(generator = GENERATOR)
	private Long id;
	
	@Embedded
	@Target(HibernateAuditoria.class)
	private Auditoria auditoria;
		
	@ManyToOne(targetEntity = HibernateOrganizacion.class)
	@JoinColumn(name = "id_empresa", nullable = false)
    private Organizacion empresa;
        
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = HibernatePersona.class)
    @JoinColumn(name = "id_persona", nullable = false)
    private Persona persona;
	
	@Column(name = Entidad_.estado, nullable = false, length = 1)
    @Type(type = Entidad.Estado.TYPE)
    private Entidad.Estado estado;
	
	@OneToMany(mappedBy = "empresaPersona", fetch = FetchType.LAZY, targetEntity = HibernateUsuario.class)
    private Set<Usuario> usuarioList = new HashSet<Usuario>(0);
	
	
	@OneToMany(mappedBy = "empresaPersona", fetch = FetchType.LAZY, targetEntity = HibernateActivoCustodio.class)
    private Set<ActivoCustodio> activoCustodioList = new HashSet<ActivoCustodio>(0);
	
}
