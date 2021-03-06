package ec.com.ebos.hibernate.logis.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ec.com.ebos.core.aspect.annotation.Auditable;
import ec.com.ebos.core.logis.model.Logistica;
import ec.com.ebos.core.logis.model.ProductoBase;


/**
 * @author <a href="mailto:vipconsultoresaso@gmail.com">VIP Consultores</a>
 * @update 2013/04/29 <a href="mailto:eduardo.plua@gmail.com">Eduardo Plua Alay</a>
 */
@Entity
@Table(name = HibernateProductoBase.TABLE_NAME, schema = Logistica.SCHEMA)
@Data @EqualsAndHashCode(callSuper=false)
@Auditable
public class HibernateProductoBase extends HibernateLogistica implements ProductoBase {

	private static final long serialVersionUID = 7832930254497357033L;
	
	protected static final String TABLE_NAME = "PRODUCTO_BASE";
	private static final String SEQUENCE = Logistica.SCHEMA+".S"+TABLE_NAME;
	private static final String GENERATOR = TABLE_NAME+"_ID_GENERATOR";

	@Id
	@SequenceGenerator(name = GENERATOR, sequenceName = SEQUENCE)
	@GeneratedValue(generator = GENERATOR)
	private Long id;
	
}
