package com.Aplicacao.Loja.controller;

import com.Aplicacao.Loja.DTO.authentication.AuthenticationDTO;
import com.Aplicacao.Loja.DTO.authentication.CadastroDTO;
import com.Aplicacao.Loja.DTO.authentication.LoginResponseDTO;
import com.Aplicacao.Loja.domain.usuario.Usuario;
import com.Aplicacao.Loja.repository.UsuarioRepository;
import com.Aplicacao.Loja.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO authenticationDTO){
        var usernamePassowrd = new UsernamePasswordAuthenticationToken(authenticationDTO.email(), authenticationDTO.senha());
        var auth = this.authenticationManager.authenticate(usernamePassowrd);

        var token = tokenService.generateToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }
    @PostMapping("/cadastro")
    public ResponseEntity cadastrar(@RequestBody @Valid CadastroDTO cadastroDTO){
        if(this.usuarioRepository.findByEmail(cadastroDTO.email()) != null) return ResponseEntity.badRequest().build();

        String senhaEncriptada = new BCryptPasswordEncoder().encode(cadastroDTO.senha());
        Usuario novoUsuario = new Usuario(cadastroDTO.email(), senhaEncriptada, cadastroDTO.role());

        this.usuarioRepository.save(novoUsuario);

        return ResponseEntity.ok().build();
    }
}
