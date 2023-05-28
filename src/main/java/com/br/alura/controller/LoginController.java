package com.br.alura.controller;

import com.br.alura.domain.Autor.Usuario;
import com.br.alura.domain.Autor.UsuarioLogin;
import com.br.alura.infra.Security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/login")
@Controller
public class LoginController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;



    @PostMapping()
    public String login(@RequestBody @Valid UsuarioLogin login){
        var user = new UsernamePasswordAuthenticationToken(login.email(), login.senha());

        var authenticate = this.manager.authenticate(user);

        var usuario = (Usuario) authenticate.getPrincipal();

        return tokenService.gerarUsuario(usuario);
    }

}
