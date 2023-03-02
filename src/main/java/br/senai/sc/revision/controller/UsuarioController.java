package br.senai.sc.revision.controller;

import br.senai.sc.revision.model.dto.UsuarioDTO;
import br.senai.sc.revision.model.entity.Usuario;
import br.senai.sc.revision.service.UsuarioService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/usuario")
@Controller
public class UsuarioController {
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> buscarTodos() {
        return ResponseEntity.ok(usuarioService.buscarTodos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizar(@PathVariable(value = "id") Long id, @RequestBody UsuarioDTO usuarioDTO) {
        try{
            Usuario usuario = usuarioService.buscarUm(id);
            BeanUtils.copyProperties(usuarioDTO, usuario);
            usuarioService.salvar(usuario);
            return ResponseEntity.ok("Usuário atualizado com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Object> salvar(@RequestBody UsuarioDTO usuarioDTO) {
        if(usuarioService.verificarEmail(usuarioDTO.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email já cadastrado.");
        }
        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(usuarioDTO, usuario);
        usuarioService.salvar(usuario);
        return ResponseEntity.ok("Usuário cadastrado com sucesso.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletar(@PathVariable(value = "id") Long id) {
        if (usuarioService.verificarId(id)) {
            usuarioService.deletar(id);
            return ResponseEntity.ok("Usuário deletado com sucesso.");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário não encontrado.");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarUm(@PathVariable(value = "id") Long id) {
       try{
           return ResponseEntity.status(HttpStatus.OK).body(usuarioService.buscarUm(id));
       } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
       }
    }
}
