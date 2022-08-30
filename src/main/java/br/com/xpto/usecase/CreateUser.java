package br.com.xpto.usecase;

import br.com.xpto.domain.entities.User;
import br.com.xpto.gateway.UserGateway;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CreateUser {
    private final UserGateway userGateway;

    public CreateUser(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public User create(User user) {
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(user.getCreatedAt());

        return userGateway.create(user);
    }
}
