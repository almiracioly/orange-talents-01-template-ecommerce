package br.com.zup.ecommerce.product.uploadimages;

import br.com.zup.ecommerce.config.security.UserDetailsImpl;
import br.com.zup.ecommerce.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Set;

@RestController
public class UploadProductImagesController {

    @Autowired
    private UploadImageService uploadImageService;

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/products/{productId}/images")
    @Transactional
    public ResponseEntity<?> exec(@PathVariable Long productId, UploadProductImagesRequest request, Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        System.out.println(userDetails.getUser().getEmail());
        Product product = entityManager.find(Product.class, productId);
        if (product != null) {
            if(!product.belongsToUser(userDetails.getUser())) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

            Set<String> imageLinks = uploadImageService.store(request.getImages());
            product.attachImages(imageLinks);
            entityManager.merge(product);
            return ResponseEntity.ok(new UploadProductImagesResponse(imageLinks));
        }

        return ResponseEntity.notFound().build();
    }
}
