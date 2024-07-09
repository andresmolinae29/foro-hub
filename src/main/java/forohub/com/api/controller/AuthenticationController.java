package forohub.com.api.controller;

import forohub.com.api.domain.users.User;
import forohub.com.api.domain.users.UserAuthentication;
import forohub.com.api.infra.security.DatosJWTTOKEN;
import forohub.com.api.infra.security.TokenService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@Tag(name = "Autenticacion", description = "obtiene el token para el usuario asignado que da acceso al resto de endpoint")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<DatosJWTTOKEN> autenticarUsuario(@RequestBody @Valid UserAuthentication userAuthentication) {
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(userAuthentication.username(),
                userAuthentication.password());
        var authenticatedUser = authenticationManager.authenticate(authenticationToken);
        var JWTToken = tokenService.genToken((User) authenticatedUser.getPrincipal());
        return ResponseEntity.ok(new DatosJWTTOKEN(JWTToken));
    }

}
