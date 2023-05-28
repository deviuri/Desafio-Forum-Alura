package com.br.alura.controller;

import com.br.alura.domain.Autor.CadastroUsuario;
import com.br.alura.domain.Autor.DetalhamentoUsuario;
import com.br.alura.domain.Autor.Usuario;
import com.br.alura.domain.Autor.UsuarioRepository;
import jakarta.persistence.NonUniqueResultException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private  void verificar(Usuario usuario){
        if (usuarioRepository.findByEmail(usuario.getEmail()).equals(usuario.getEmail())){
            throw new NonUniqueResultException();
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarUsuario(@RequestBody @Valid CadastroUsuario cadastroUsuario, UriComponentsBuilder uriBuilder){

        var usuario = new Usuario(cadastroUsuario);
        usuarioRepository.save(usuario);
        verificar(usuario);

        var uri = uriBuilder.path("/usuario/{id}").buildAndExpand(usuario.getId()).toUri();

        return ResponseEntity.created(uri).body(new DetalhamentoUsuario(usuario));

    }

    @GetMapping
    public ResponseEntity<Page<DetalhamentoUsuario>> listarUsuario(Pageable paginação){

        var usuario = usuarioRepository.findAllBy(paginação).map(DetalhamentoUsuario::new);

        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarUsuario(@PathVariable Long id){
        var usuario = usuarioRepository.getReferenceById(id);

        usuario.desativar();

        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity pegarUsuarioPorId(@PathVariable Long id) {

        var usuario = usuarioRepository.getReferenceById(id);

        return ResponseEntity.ok(new DetalhamentoUsuario(usuario));
    }
}
