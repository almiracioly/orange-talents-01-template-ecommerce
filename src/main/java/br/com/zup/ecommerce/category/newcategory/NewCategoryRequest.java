package br.com.zup.ecommerce.category.newcategory;

import br.com.zup.ecommerce.category.Category;
import br.com.zup.ecommerce.shared.annotation.existsindatabase.ExistsInDatabase;
import br.com.zup.ecommerce.shared.annotation.uniquevalue.UniqueValue;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class NewCategoryRequest {
    @NotBlank
    @UniqueValue(domainClass = Category.class, fieldName = "name", message = "Esta categoria já existe")
    private String name;

    @Positive
    @ExistsInDatabase(domainClass = Category.class, fieldName = "id", message = "Informe uma categoria existente")
    private Long parentCategoryId;

    public Category toModel(EntityManager entityManager) {
        if (parentCategoryId != null) {
            Category parentCategory = entityManager.find(Category.class, parentCategoryId);
            return new Category(name, parentCategory);
        } else {
            return new Category(name);
        }
    }

    public String getName() {
        return name;
    }

    public Long getParentCategoryId() {
        return parentCategoryId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParentCategoryId(Long parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }
}
