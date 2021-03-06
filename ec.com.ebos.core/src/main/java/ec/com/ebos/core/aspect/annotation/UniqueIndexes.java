package ec.com.ebos.core.aspect.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import ec.com.ebos.core.root.model.Entidad;

/**
 * Anotacion para especificar una lista de indices unique para {@link Entidad}
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueIndexes {

    /**
     * Indices
     */
    UniqueIndex[] value() default {};
    
}