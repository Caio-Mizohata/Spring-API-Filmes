package application.auth;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;


@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    public String login(@RequestBody Usuario usuario) {
        UsernamePasswordAuthenticationToken tk = new UsernamePasswordAuthenticationToken(
            usuario.getNomeDeUsuario(), usuario.getSenha());
        authenticationManager.authenticate(tk);

        return tokenService.generateToken(usuario);
    }
}
