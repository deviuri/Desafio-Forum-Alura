package com.br.alura.controller;

import com.br.alura.domain.Autor.UsuarioRepository;
import com.br.alura.domain.Resposta.*;
import com.br.alura.domain.Topico.TopicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/resposta")
public class RespostaController {

    @Autowired
    private RespostaRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhamentoResposta> cadastrar(@RequestBody @Valid CadastroResposta cadastro, UriComponentsBuilder builder){
        var usuario = usuarioRepository.getReferenceById(cadastro.usuario().getId());
        var topico = topicoRepository.getReferenceById(cadastro.topico().getId());

        var resposta = new Resposta(cadastro);
        resposta.setTopico(topico);
        resposta.setUsuario(usuario);

        resposta = repository.save(resposta);

        URI uri = builder.path("/resposta/{id}").buildAndExpand(resposta.getId()).toUri();

        return ResponseEntity.created(uri).body(new DetalhamentoResposta(resposta));
    }

    @GetMapping
    @Transactional(readOnly = true)
    public ResponseEntity<Page<DetalhamentoResposta>> paginar(Pageable paginacao){
        Page<DetalhamentoResposta> resposta = repository.findAllBy(paginacao).map(DetalhamentoResposta::new);

        return ResponseEntity.ok(resposta);
    }

    @GetMapping("/{id}")
    @Transactional(readOnly = true)
    public ResponseEntity<DetalhamentoResposta> buscarPorId(@PathVariable Long id){
        Resposta resposta = repository.getReferenceById(id);

        return ResponseEntity.ok(new DetalhamentoResposta(resposta));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DetalhamentoResposta> atualizarResposta(@PathVariable Long id, @RequestBody @Valid AtualizarResposta atualizarResposta){
        var resposta = repository.getReferenceById(id);
        resposta.setMensagem(atualizarResposta.mensagem());
        resposta = repository.save(resposta);

        return ResponseEntity.ok(new DetalhamentoResposta(resposta));
    }
}
