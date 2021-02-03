package br.com.zup.ecommerce.product.newproduct;

import br.com.zup.ecommerce.config.security.UserDetailsImpl;
import br.com.zup.ecommerce.product.Product;
import br.com.zup.ecommerce.product.ProductCharacteristic;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
public class NewProductController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/products")
    @Transactional
    public ResponseEntity<?> exec(@RequestBody @Valid NewProductRequest request, Authentication authentication) {
        UserDetailsImpl loggedUser = (UserDetailsImpl) authentication.getPrincipal();
        Product product = request.toProduct(entityManager, loggedUser.getUser());
        entityManager.persist(product);

        List<ProductCharacteristic> characteristics = request.toProductCharacteristicList(product);
        characteristics.forEach(characteristic -> entityManager.persist(characteristic));

        return ResponseEntity.ok().build();
    }
}
