package ec.com.ebos.admin.model;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import org.hibernate.annotations.Type;

import ec.com.ebos.admin.model.field.Bundle_;
import ec.com.ebos.util.Constantes;
import ec.com.ebos.util.EntityUtils;
import ec.com.ebos.util.type.StringValuedEnum;
import ec.com.ebos.util.type.StringValuedEnumReflect;
import ec.com.ebos.util.type.StringValuedEnumType;

/**
 * @author <a href="mailto:eduardo.plua@gmail.com">Eduardo Plua Alay</a>
 */
@Entity
@Table(name = Bundle.TABLE_NAME, schema = Administracion.SCHEMA,
	uniqueConstraints = @UniqueConstraint(columnNames={Bundle_.codigo, Bundle_.localidad}))
@Data @EqualsAndHashCode(callSuper=false)
@org.hibernate.annotations.Entity(dynamicUpdate = true)
public class Bundle extends Administracion<Bundle>{

	private static final long serialVersionUID = -2896367216397132540L;
	
	protected static final String TABLE_NAME = "BUNDLE";
	private static final String SEQUENCE = Administracion.SCHEMA+".S"+TABLE_NAME;
	private static final String GENERATOR = TABLE_NAME+"_ID_GENERATOR";

	@Id
	@SequenceGenerator(name = GENERATOR, sequenceName = SEQUENCE)
	@GeneratedValue(generator = GENERATOR)
	private Long id;
		
	@Column(name = "codigo", nullable = false, length = 50)
	private String codigo;

	@Column(name = "localidad", nullable = false, length = 5)
	@Type(type = Localidad.TYPE)
	private Bundle.Localidad localidad;

	@Column(name = "valor", nullable = false, length = 500)
	private String valor;
	
	public String getKeyCache(){
		return codigo+localidad.getValue();
	}
	
	/**
     * <strong>Localidades(es[_EC]) que soporta el sistema</strong> <br> <table border="1">
     * <tr><th valign="top"> Localidad </th>
     * <tr><td> es_EC: Espaniol Ecuador </td>
     * <tr><td> en_US: Ingles USA </td>
     * </table>
     *
     * @author Eduardo Plua Alay
     *
     */
    public enum Localidad implements StringValuedEnum<Localidad> {

        es_EC("es_EC"),
        en_US("en_US");

        public static class Type extends StringValuedEnumType<Localidad> {
        }
        public static final String TYPE = Constantes.DOMAIN_NAME+".admin.model.Bundle$Localidad$Type";
        
        @Getter
        private String value;
        private String labelKey;

        private Localidad(String value) {
            this.value = value;
            this.labelKey = StringValuedEnumReflect.getLabelKeyFromEnum(this);
        }
        public static final Map<String, Localidad> LABELED_MAP =
                EntityUtils.buildLabeledEnumMap(Localidad.values());
        /**
         * Lists for iterations
         */
        public static final List<Localidad> LIST = Arrays.asList(Localidad.values());

        @Override
        public String getLabel() {        	
        	return labelKey;
        }

        @Override
        public String getDescription() {
            return getLabel();
        }
    }

}
