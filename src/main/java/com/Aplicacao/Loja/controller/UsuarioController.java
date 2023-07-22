package com.Aplicacao.Loja.controller;

import com.Aplicacao.Loja.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public ResponseEntity buscarUsuarios(){
        var usuarios = usuarioRepository.findAll();

        return ResponseEntity.ok().body(usuarios);
    }
}
