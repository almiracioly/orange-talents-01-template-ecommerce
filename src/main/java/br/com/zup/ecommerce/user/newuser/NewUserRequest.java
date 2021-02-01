package br.com.zup.ecommerce.user.newuser;

import br.com.zup.ecommerce.user.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class NewUserRequest {
    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 6)
    private String password;

    public NewUserRequest(@NotBlank @Email String email, @NotBlank @Size(min = 6) String password) {
        this.email = email;
        this.password = password;
    }

    public User toModel() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(password);
        return new User(email, encodedPassword);
    }
}
