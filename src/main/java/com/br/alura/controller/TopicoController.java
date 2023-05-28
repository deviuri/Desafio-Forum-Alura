package com.br.alura.controller;

import com.br.alura.domain.Autor.Usuario;
import com.br.alura.domain.Curso.Curso;
import com.br.alura.domain.Topico.DetalhamentoTopico;
import com.br.alura.domain.Topico.Topico;
import com.br.alura.domain.Topico.TopicoCadastro;
import com.br.alura.domain.Topico.AtualizaçãoTopico;
import com.br.alura.domain.Autor.UsuarioRepository;
import com.br.alura.domain.Curso.CursoRepository;
import com.br.alura.domain.Topico.TopicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import java.util.stream.Collectors;


@RestController
@RequestMapping("/forum")
@Controller
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CursoRepository cursoRepository;

    private void verificarExistenciaTopico(Topico topico) {

        var topicoTitulo = topicoRepository.findByTitulo(topico.getTitulo());
        var topicoMensagem = topicoRepository.findByMensagem(topico.getMensagem());

        if (topicoTitulo.equals(topico) || topicoMensagem.equals(topico)) {
            throw new RuntimeException("Já existe!!");
        }
    }

    @GetMapping("/{id}")
    @Transactional(readOnly = true)
    public ResponseEntity pegarTopicoPorId(@PathVariable Long id){
        var topico = topicoRepository.getReferenceById(id);

        return ResponseEntity.ok(new DetalhamentoTopico(topico));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhamentoTopico> cadastrar(@RequestBody @Valid TopicoCadastro cadastro, UriComponentsBuilder uriBuilder) {

        var usuario = new Usuario(cadastro.usuario());
        usuarioRepository.save(usuario);

        var curso = new Curso(cadastro.curso());
        cursoRepository.save(curso);

        var topico = new Topico(cadastro);
        topico.setCurso(curso);
        topico.setUsuario(usuario);
        topicoRepository.save(topico);

        var uri = uriBuilder.path("/forum/{id}").buildAndExpand(topico.getId()).toUri();

        verificarExistenciaTopico(topico);

        return ResponseEntity.created(uri).body(new DetalhamentoTopico(topico));
    }

    @GetMapping
    @Transactional(readOnly = true)
    public ResponseEntity<Page<DetalhamentoTopico>> listar(@PageableDefault(size = 10, sort = {"id"}) Pageable paginacao) {

        var  page = topicoRepository.findAllByAtivoTrue(paginacao).map(DetalhamentoTopico::new);

        return ResponseEntity.ok(page);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizar(@PathVariable Long id, @RequestBody @Valid AtualizaçãoTopico dados){

        var topico = topicoRepository.getReferenceById(id);

        topico.editarTopico(dados);

        return ResponseEntity.ok(new DetalhamentoTopico(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletarTopico(@PathVariable Long id){
        var topico = topicoRepository.getReferenceById(id);

        topico.desativar();

        return ResponseEntity.noContent().build();
    }
    @GetMapping("/curso/{curso}")
    @Transactional(readOnly = true)
    public ResponseEntity<List<DetalhamentoTopico>> buscarPorNomeDoCurso(@PathVariable String curso, Pageable pageable) {

        Page<Topico> topicos = topicoRepository.findAllByCurso_Nome(pageable, curso);

        List<DetalhamentoTopico> detalhamentos = topicos.stream().map(DetalhamentoTopico::new).collect(Collectors.toList());

        return ResponseEntity.ok(detalhamentos);
    }
}
