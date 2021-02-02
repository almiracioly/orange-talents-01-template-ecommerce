package br.com.zup.ecommerce.shared.annotation.existsindatabase;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ExistsInDatabaseValidator implements ConstraintValidator<ExistsInDatabase, Object> {
    private Class<?> domainClass;
    private String fieldName;

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void initialize(ExistsInDatabase params) {
        domainClass = params.domainClass();
        fieldName = params.fieldName();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return true;
        }

        Query query = entityManager.createQuery("select 1 from " + domainClass.getName() + " where " + fieldName + "=:parameter");
        query.setParameter("parameter", value);
        List<?> list = query.getResultList();

        return !list.isEmpty();
    }
}
