package br.com.zup.ecommerce.product.newproduct;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Set;

public class UndiplicatedProductCharacteristicsValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return NewProductRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object genericObject, Errors errors) {
        if (errors.hasErrors()) return;

        NewProductRequest request = (NewProductRequest) genericObject;
        Set<String> duplicatedCharacteristics = request.getDuplicatedProductCharacteristics();
        if (!duplicatedCharacteristics.isEmpty()) {
            String duplicatedCharacteristicsInSingleLine = duplicatedCharacteristics
                    .stream()
                    .reduce("", (s, s2) -> formatDuplicatedStringsByComma(duplicatedCharacteristics, s, s2));

            errors.rejectValue("characteristics", null, "As seguintes características estão duplicadas: " + duplicatedCharacteristicsInSingleLine);
        }
    }

    private String formatDuplicatedStringsByComma(Set<String> duplicatedCharacteristics, String s, String s2) {
        Object[] objects = duplicatedCharacteristics.toArray();
        String lastObject = (String) objects[objects.length - 1];
        if (lastObject.equals(s2)) {
            return s.concat(s2);
        } else {
            return s.concat(s2).concat(", ");
        }
    }
}
