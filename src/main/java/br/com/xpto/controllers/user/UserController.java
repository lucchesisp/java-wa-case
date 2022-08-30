package br.com.xpto.controllers.user;

import br.com.xpto.controllers.dto.UserDto;
import br.com.xpto.domain.entities.User;
import br.com.xpto.usecase.CreateUser;
import br.com.xpto.usecase.FindUser;
import br.com.xpto.usecase.UpdateUser;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/user")
public class UserController {
    private final FindUser findUser;
    private final CreateUser createUser;
    private final UpdateUser updateUser;

    public UserController(FindUser findUser, CreateUser createUser, UpdateUser updateUser) {
        this.findUser = findUser;
        this.createUser = createUser;
        this.updateUser = updateUser;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        try {
            User user = findUser.findById(id);

            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(user, userDto);

            return ResponseEntity.ok(userDto);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserDto userDto, UriComponentsBuilder uriComponentsBuilder) {

        try {
            User user = new User();
            BeanUtils.copyProperties(userDto, user);

            User savedUser = createUser.create(user);

            UserDto savedUserDto = new UserDto();
            BeanUtils.copyProperties(savedUser, savedUserDto);

            URI uri = uriComponentsBuilder.path("/user/{id}").buildAndExpand(savedUser.getId()).toUri();
            return ResponseEntity.created(uri).body(savedUserDto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody @Valid UserDto userDto) {
        try {
            User user = new User();
            BeanUtils.copyProperties(userDto, user);

            User savedUser = this.updateUser.update(id, user);

            UserDto savedUserDto = new UserDto();
            BeanUtils.copyProperties(savedUser, savedUserDto);

            return ResponseEntity.ok(savedUserDto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
