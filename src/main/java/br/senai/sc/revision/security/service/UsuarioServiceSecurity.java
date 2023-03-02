package br.senai.sc.revision.security.service;

import br.senai.sc.revision.model.entity.Usuario;
import br.senai.sc.revision.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UsuarioServiceSecurity implements UserDetailsService {

    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(username);
        if (usuario.isPresent()){
            return usuario.get();
        }
        throw new UsernameNotFoundException("Usuário não encontrado.");
    }


}
