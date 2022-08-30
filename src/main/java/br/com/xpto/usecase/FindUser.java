package br.com.xpto.usecase;

import br.com.xpto.domain.entities.User;
import br.com.xpto.gateway.UserGateway;
import org.springframework.stereotype.Service;

@Service
public class FindUser {
    private final UserGateway userGateway;

    public FindUser(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public User findById(Long id) {
        return userGateway.findById(id);
    }
}
