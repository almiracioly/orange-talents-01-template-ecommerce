package br.com.zup.ecommerce.config.security;

import br.com.zup.ecommerce.user.User;
import br.com.zup.ecommerce.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> foundUser = userRepository.findByEmail(email);
        if(!foundUser.isPresent()) throw new UsernameNotFoundException("Credenciais inválidas");

        User user = foundUser.get();
        return new UserDetailsImpl(user);
    }
}
