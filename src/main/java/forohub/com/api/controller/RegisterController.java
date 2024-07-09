package forohub.com.api.controller;


import forohub.com.api.domain.topic.Topic;
import forohub.com.api.domain.topic.TopicRegister;
import forohub.com.api.domain.topic.TopicResponse;
import forohub.com.api.domain.users.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/register")
@Tag(name = "Registro", description = "Endpoints para la gestion de registros")
public class RegisterController {

    @Autowired
    private UserService userService;

    @PostMapping
    @Transactional
    @Operation(summary = "Registra usuario")
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserRegister userRegister,
                                                    UriComponentsBuilder uriBuilder) {
        UserResponse userResponse = userService.createUser(userRegister.username(), userRegister.password());
        URI url = uriBuilder.path("/user/{id}").buildAndExpand(userResponse.id()).toUri();
        return ResponseEntity.created(url).body(userResponse);
    }

    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "bearer-key")
    @Transactional
    @Operation(summary = "Elimina un usuario")
    public ResponseEntity deleteTopic(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
