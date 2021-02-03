package br.com.zup.ecommerce.product.newproduct;

import javax.validation.constraints.NotBlank;

public class ProductCharacteristicRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String description;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
