package com.br.alura.infra.Security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.br.alura.domain.Autor.Usuario;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    public String gerarUsuario(Usuario usuario) {
       return JWT.create()
               .withIssuer("forum")
               .withSubject(usuario.getEmail())
               .withClaim("id", usuario.getId())
               .withExpiresAt(LocalDateTime.now()
                       .plusMinutes(10)
                       .toInstant(ZoneOffset.of("-03:00")))
               .sign(Algorithm.HMAC256("jogabola"));
    }

    public String getSubject(String token) {
        return JWT.require(Algorithm.HMAC256("jogabola"))
                .withIssuer("forum")
                .build().verify(token).getSubject();
    }
}
