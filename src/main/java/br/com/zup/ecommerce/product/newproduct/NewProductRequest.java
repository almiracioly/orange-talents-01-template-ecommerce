package br.com.zup.ecommerce.product.newproduct;

import br.com.zup.ecommerce.category.Category;
import br.com.zup.ecommerce.product.Product;
import br.com.zup.ecommerce.product.ProductCharacteristic;
import br.com.zup.ecommerce.shared.annotation.existsindatabase.ExistsInDatabase;
import br.com.zup.ecommerce.user.User;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class NewProductRequest {
    @NotBlank
    private String name;
    @NotNull
    @Positive
    private BigDecimal price;

    @NotNull
    @Min(0)
    private Long availableQuantity;

    @Size(min = 3)
    private List<@Valid ProductCharacteristicRequest> characteristics;

    @NotBlank
    @Size(max = 1000)
    private String description;

    @NotNull
    @ExistsInDatabase(domainClass = Category.class, fieldName = "id", message = "Informe uma categoria existente")
    private Long categoryId;

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Long getAvailableQuantity() {
        return availableQuantity;
    }

    public List<ProductCharacteristicRequest> getCharacteristics() {
        return characteristics;
    }

    public String getDescription() {
        return description;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public Product toProduct(User user) {
        return new Product(name, price, availableQuantity, description, user);
    }

    public List<ProductCharacteristic> toProductCharacteristicList(Product product) {
        List<ProductCharacteristic> productCharacteristics = characteristics.stream().map(characteristic -> {
            return new ProductCharacteristic(characteristic.getName(), characteristic.getDescription(), product);
        }).collect(Collectors.toList());

        return productCharacteristics;
    }
}
