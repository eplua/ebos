package ec.com.ebos.hibernate.admin.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ec.com.ebos.core.admin.model.Administracion;
import ec.com.ebos.core.admin.model.Documento;
import ec.com.ebos.core.aspect.annotation.Auditable;
import ec.com.ebos.core.conta.model.Asiento;
import ec.com.ebos.core.conta.model.AsientoDetalle;
import ec.com.ebos.core.conta.model.DocumentoDistribucion;
import ec.com.ebos.hibernate.conta.model.HibernateAsiento;
import ec.com.ebos.hibernate.conta.model.HibernateAsientoDetalle;
import ec.com.ebos.hibernate.conta.model.HibernateDocumentoDistribucion;
import ec.com.ebos.hibernate.conta.model.field.AsientoDetalle_;
import ec.com.ebos.hibernate.conta.model.field.Asiento_;
import ec.com.ebos.hibernate.conta.model.field.DocumentoDistribucion_;

/**
 * Documento
 * 
 * @author <a href="mailto:eduardo.plua@gmail.com">Eduardo Plua Alay</a>
 * @since 2013/04/28
 */
@Entity
@Table(name = HibernateDocumento.TABLE_NAME, schema = Administracion.SCHEMA)
@Data @EqualsAndHashCode(callSuper=false) 
@Auditable
public class HibernateDocumento extends HibernateAdministracion implements Documento {

	private static final long serialVersionUID = -6748190361672935897L;

	protected static final String TABLE_NAME = "DOCUMENTO";
	private static final String SEQUENCE = Administracion.SCHEMA+".S"+TABLE_NAME;
	private static final String GENERATOR = TABLE_NAME+"_ID_GENERATOR";

	@Id
	@SequenceGenerator(name = GENERATOR, sequenceName = SEQUENCE)
	@GeneratedValue(generator = GENERATOR)
    private Long id;
	
	@OneToMany(mappedBy = Asiento_.documento, fetch = FetchType.LAZY, targetEntity = HibernateAsiento.class)
    private Set<Asiento> asientoList = new HashSet<Asiento>(0);
	
	@OneToMany(mappedBy = AsientoDetalle_.documento, fetch = FetchType.LAZY, targetEntity = HibernateAsientoDetalle.class)
    private Set<AsientoDetalle> asientoDetalleList = new HashSet<AsientoDetalle>(0);
    
	@OneToMany(mappedBy = DocumentoDistribucion_.documento, fetch = FetchType.LAZY, targetEntity = HibernateDocumentoDistribucion.class)
    private Set<DocumentoDistribucion> documentoDistribucionList = new HashSet<DocumentoDistribucion>(0);
	
	@Column
	private Date emitido;

	@Column
	private Date vencimiento;

	@Column
	private Date enviado;
	
	@Column
	private Date autorizado;
	
	@Column
	private Date procesado;

	@Column
	private Date contabilizado;

}
