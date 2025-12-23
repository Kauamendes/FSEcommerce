package br.com.fs.ecommerce.foursalesecommerce.annotations;

import br.com.fs.ecommerce.foursalesecommerce.support.TsidIdentifierGenerator;
import org.hibernate.annotations.IdGeneratorType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@IdGeneratorType(TsidIdentifierGenerator.class)
@Retention(RUNTIME)
@Target({FIELD, METHOD})
public @interface Tsid {
}
