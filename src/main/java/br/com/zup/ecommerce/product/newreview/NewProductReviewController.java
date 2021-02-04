package br.com.zup.ecommerce.product.newreview;

import br.com.zup.ecommerce.config.security.UserDetailsImpl;
import br.com.zup.ecommerce.product.Product;
import br.com.zup.ecommerce.product.ProductReview;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class NewProductReviewController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/products/{productId}/reviews")
    @Transactional
    public ResponseEntity<?> exec(@PathVariable Long productId, @RequestBody @Valid NewProductReviewRequest request, Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Product reviewedProduct = entityManager.find(Product.class, productId);

        if (reviewedProduct == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        if(reviewedProduct.belongsToUser(userDetails.getUser())) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);

        ProductReview productReview = request.toModel(reviewedProduct, userDetails.getUser());
        entityManager.persist(productReview);
        return ResponseEntity.ok().build();
    }

}
