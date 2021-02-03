package br.com.zup.ecommerce.product.newproduct;

import br.com.zup.ecommerce.category.Category;
import br.com.zup.ecommerce.product.Product;
import br.com.zup.ecommerce.product.ProductCharacteristic;
import br.com.zup.ecommerce.shared.annotation.existsindatabase.ExistsInDatabase;
import br.com.zup.ecommerce.user.User;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

    public Product toProduct(EntityManager entityManager, User user) {
        Category category = entityManager.find(Category.class, categoryId);
        return new Product(name, price, availableQuantity, description, category, user);
    }

    public List<ProductCharacteristic> toProductCharacteristicList(Product product) {
        List<ProductCharacteristic> productCharacteristics = characteristics.stream().map(characteristic -> {
            return new ProductCharacteristic(characteristic.getName(), characteristic.getDescription(), product);
        }).collect(Collectors.toList());

        return productCharacteristics;
    }

    public Set<String> getDuplicatedProductCharacteristics() {
        Set<String> uniqueCharacteristics = new HashSet<>();
        Set<String> duplicates = new HashSet<>();
        for (ProductCharacteristicRequest characteristic : characteristics) {
            if(!uniqueCharacteristics.add(characteristic.getName())) duplicates.add(characteristic.getName());
        }

        return duplicates;
    }
}
