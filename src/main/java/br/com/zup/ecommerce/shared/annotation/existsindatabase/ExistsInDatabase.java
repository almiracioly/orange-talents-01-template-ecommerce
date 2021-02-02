package br.com.zup.ecommerce.shared.annotation.existsindatabase;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {ExistsInDatabaseValidator.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistsInDatabase {
    String message() default "{br.com.zup.ecommerce.shared.annotation.existsindatabase}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default  {};

    Class<?> domainClass();

    String fieldName();
}
