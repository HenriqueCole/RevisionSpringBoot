package br.senai.sc.revision.service;

import br.senai.sc.revision.model.entity.Usuario;
import br.senai.sc.revision.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UsuarioService {
    private UsuarioRepository usuarioRepository;

    public Usuario salvar(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public void deletar(Long id){
        usuarioRepository.delete(buscarUm(id));
    }

    public Usuario buscarUm(Long id){
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isPresent()){
            return usuarioOptional.get();
        }
        throw new RuntimeException("Usuário não encontrado.");
    }

    public List<Usuario> buscarTodos(){
        return usuarioRepository.findAll();
    }

    public Boolean verificarId(Long id){
        return usuarioRepository.existsById(id);
    }

    public Boolean verificarEmail(String email){
        return usuarioRepository.existsByEmail(email);
    }

    public Usuario buscarPorEmail(String email){
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(email);
        if (usuarioOptional.isPresent()){
            return usuarioOptional.get();
        }
        throw new RuntimeException("Usuário não encontrado.");
    }
}
