package br.com.xpto.gateway;

import br.com.xpto.domain.entities.User;
import br.com.xpto.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserGateway {
    private final UserRepository userRepository;

    public UserGateway(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(User user) {
        return userRepository.save(user);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User update(User user) {
        return userRepository.save(user);
    }
}
