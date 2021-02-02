package br.com.zup.ecommerce.authentication;

import br.com.zup.ecommerce.config.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/auth")
    public ResponseEntity<?> authenticate(@RequestBody @Valid AuthenticationRequest request) {
        UsernamePasswordAuthenticationToken loginData = request.convert();

        try {
            Authentication authentication = authenticationManager.authenticate(loginData);
            String token = tokenService.generateToken(authentication);

            return ResponseEntity.ok(new BearerTokenResponse(token));
        } catch (AuthenticationException exception) {
            return ResponseEntity.badRequest().build();
        }
    }
}
