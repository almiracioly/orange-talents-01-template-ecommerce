package br.com.zup.ecommerce.product.detail;

import br.com.zup.ecommerce.product.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@RestController
public class ProductDetailController {

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("/products/{productId}")
    @Transactional
    public ResponseEntity<?> exec(@PathVariable Long productId){
        Product product = entityManager.find(Product.class, productId);
        if (product == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        DetailedProductResponse detailedProduct = new DetailedProductResponse(product);
        return ResponseEntity.ok(detailedProduct);
    }

}
