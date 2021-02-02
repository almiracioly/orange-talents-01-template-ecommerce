package br.com.zup.ecommerce.category.newcategory;

import br.com.zup.ecommerce.category.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class NewCategoryController {

    @PersistenceContext
    EntityManager entityManager;

    @PostMapping("/categories")
    @Transactional
    public ResponseEntity<?> exec(@RequestBody @Valid NewCategoryRequest request){
        Category category = request.toModel(entityManager);
        entityManager.persist(category);

        return ResponseEntity.ok().build();
    }
}
