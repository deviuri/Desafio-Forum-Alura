package com.br.alura.infra;

import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NonUniqueResultException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarError404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarError400(MethodArgumentNotValidException e){

        var erros = e.getFieldErrors();

        return ResponseEntity.badRequest().body(erros.stream().map(DadosValidacao::new).toList());
    }

    @ExceptionHandler(NonUniqueResultException.class)
    public ResponseEntity tratarErros500(NonUniqueResultException e){

        var error = e.getMessage();

        return ResponseEntity.badRequest().body(error);
    }

    private record DadosValidacao(String campo, String mensagem){
        public DadosValidacao(FieldError erro){
            this(erro.getField(), erro.getDefaultMessage());
        }
    }
}
