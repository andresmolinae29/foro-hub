package forohub.com.api.controller;

import forohub.com.api.domain.topic.Topic;
import forohub.com.api.domain.topic.TopicList;
import forohub.com.api.domain.users.User;
import forohub.com.api.domain.users.UserRepository;
import forohub.com.api.domain.users.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Usuarios", description = "Endpoints para la gestion de usuarios")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{id}")
    @SecurityRequirement(name = "bearer-key")
    @Operation(summary = "obtiene un registro de un usuario por Id")
    public ResponseEntity<UserResponse> getTopicById(@PathVariable Long id) {
        User user = userRepository.getReferenceById(id);
        return ResponseEntity.ok(UserResponse.fromUser(user));
    }
}
