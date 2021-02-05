package br.com.zup.ecommerce.product.detail;

import br.com.zup.ecommerce.product.ProductCharacteristic;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ProductCharacteristicResponse {
    @NotNull
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String description;

    public ProductCharacteristicResponse(ProductCharacteristic characteristic) {
        this.id = characteristic.getId();
        this.name = characteristic.getName();
        this.description = characteristic.getDescription();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
