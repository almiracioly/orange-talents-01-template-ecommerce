package br.com.zup.ecommerce.user.newuser;

import br.com.zup.ecommerce.user.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class NewUserController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/users")
    @Transactional
    public ResponseEntity<?> exec(@RequestBody @Valid NewUserRequest request){
        User user = request.toModel();
        entityManager.persist(user);

        return ResponseEntity.ok().build();
    }
}
