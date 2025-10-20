package application.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
public class AppUserDataService implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = usuarioRepo.findByNomeDeUsuario(username);

        if(usuario.isEmpty()) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }

        UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
            .username(usuario.get().getNomeDeUsuario())
            .password(usuario.get().getSenha())
            .roles("USER")
            .build();
        
        return userDetails;
    }
}
