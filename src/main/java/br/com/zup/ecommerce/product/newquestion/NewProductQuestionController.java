package br.com.zup.ecommerce.product.newquestion;

import br.com.zup.ecommerce.config.security.UserDetailsImpl;
import br.com.zup.ecommerce.product.Product;
import br.com.zup.ecommerce.product.ProductQuestion;
import br.com.zup.ecommerce.shared.builder.MailMessageBuilder;
import br.com.zup.ecommerce.shared.service.mail.MailService;
import br.com.zup.ecommerce.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
public class NewProductQuestionController {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    MailService mailService;

    @PostMapping("/products/{productId}/questions")
    @Transactional
    public ResponseEntity<?> exec(@PathVariable Long productId, @RequestBody @Valid NewProductQuestionRequest request,
                                  Authentication authentication, UriComponentsBuilder uriBuilder) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User questionAuthor = userDetails.getUser();

        Product product = entityManager.find(Product.class, productId);
        if (product == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        ProductQuestion productQuestion = request.toModel(questionAuthor, product);
        entityManager.persist(productQuestion);

        URI linkToProduct = uriBuilder.path("products").path("/{productId}").buildAndExpand(productId).toUri();

        // TODO: Melhorar a classe build (possível refatoracão para métodos estáticos)
        mailService.send(new MailMessageBuilder()
                .withTitle("Olá, você tem uma nova pergunta sobre seu produto.")
                .withContent("Pergunta: " + productQuestion.getTitle()
                        + " \nAcesse a página do produto para respondê-la: "
                        + linkToProduct)
                .to(product.getOwner().getEmail()));

        return ResponseEntity.ok().build();
    }
}
