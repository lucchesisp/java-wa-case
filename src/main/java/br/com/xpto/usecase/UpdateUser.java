package br.com.xpto.usecase;

import br.com.xpto.domain.entities.User;
import br.com.xpto.gateway.UserGateway;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UpdateUser {

    private final UserGateway userGateway;

    public UpdateUser(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public User update(Long id, User user) throws Exception {
        User updateUser = userGateway.findById(id);

        if (updateUser == null) {
           throw new Exception("User not found");
        }

        user.setId(updateUser.getId());
        user.setCreatedAt(updateUser.getCreatedAt());
        user.setUpdatedAt(LocalDateTime.now());

        BeanUtils.copyProperties(user, updateUser);

        return userGateway.update(updateUser);
    }

}
